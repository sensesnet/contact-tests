package consumer;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerPort;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(
        ids = {"com.github.epam-valerii:producer-contact-tests:+:stubs:8080"},
        stubsMode = StubRunnerProperties.StubsMode.CLASSPATH)
public class ContractTest {
    @LocalServerPort
    private int consumerPort;
    @StubRunnerPort("producer-contact-tests")
    private int producerPort;
    private String consumerUrl;
    private String producerUrl;

    @BeforeAll
    void setup() throws InterruptedException {
        consumerUrl = "http://localhost:" + consumerPort;
        producerUrl = "http://localhost:" + producerPort;
        System.out.println("producerUrl: " + producerUrl);
//        Thread.sleep(100000);
    }

    @Test
    void shouldReturnUpperCaseContent() {
        given()
                .baseUri(consumerUrl)
                .log().method()
                .param("field", "content")
                .when()
                .get("/content")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .body(equalTo("HELLO, WORLD!"));
    }
}
