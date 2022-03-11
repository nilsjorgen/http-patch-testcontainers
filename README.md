# http-patch-test

Testing `HTTP PATCH` using [Testcontainers](https://www.testcontainers.org/)
and a custom [json-server](https://github.com/typicode/json-server) Docker image.

Since [the standard JDK HTTP library does not support HTTP PATCH](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html#patchForObject-java.lang.String-java.lang.Object-java.lang.Class-java.util.Map-)
by default, Spring must be able to locate a `ClientHttpRequestFactory` that does support `HTTP PATCH`.

Spring autoconfigures it's `RestTemplateBuilder`, and if either `HttpComponentsClientHttpRequestFactory`
or `OkHttp3ClientHttpRequestFactory` are found, they will be used, and they do provided the necessary support
for `HTTP PATCH`.

## Usage

Run `./gradlew clean build` in order to execute the test.

Remove `implementation("com.squareup.okhttp3:okhttp:4.9.3")` from the `build.gradle.kts` to get the test to fail.