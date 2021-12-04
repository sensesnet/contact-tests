package consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service
public class GreetingService {

    @Value("${producer.url}")
    private String producerUrl;

    public String greetingContent() {
        return given()
                .baseUri(producerUrl)
                .get("/greeting")
                .path("content");
    }
}
