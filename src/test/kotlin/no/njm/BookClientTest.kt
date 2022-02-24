package no.njm

import org.amshove.kluent.`should be equal to`
import org.amshove.kluent.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.BindMode
import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class BookClientTest {

    companion object {

        private const val DATA_FILE = "books.json"

        init {
            GenericContainer(DockerImageName.parse("nilsjorgen/json-server:17-alpine"))
                .also {
                    it.withExposedPorts(3000)
                    it.withEnv(mutableMapOf("DATA_FILE" to DATA_FILE))
                    it.withFileSystemBind(
                        "${File("").absolutePath}/data/$DATA_FILE",
                        "/data/$DATA_FILE",
                        BindMode.READ_ONLY
                    )
                }
                .also {
                    it.start()
                    System.setProperty("base.url", "http://localhost:${it.firstMappedPort}")
                }
        }
    }

    @Autowired
    private lateinit var bookClient: BookClient

    @Test
    fun `fetching all books should return all three books`() {
        val books = bookClient.getBooks()

        books.size shouldBe 3
    }

    @Test
    fun `updating a book title should return the updated book`() {
        val updatedBook = bookClient.updateBookTitle(1, "Småfolk")

        updatedBook?.title `should be equal to` "Småfolk"
    }
}
