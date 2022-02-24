package no.njm

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootApplication
class Application : CommandLineRunner {

    private val log = logger()

    @Bean
    fun restTemplate(restTemplateBuilder: RestTemplateBuilder): RestTemplate {
        return restTemplateBuilder.build()
    }

    override fun run(vararg args: String?) {
        log.info("Application is running.")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
