package producer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping(value = "/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        String date = ZonedDateTime.now(ZoneOffset.UTC)
                .format(DateTimeFormatter.ISO_LOCAL_DATE);
        return new Greeting(
                counter.incrementAndGet(),
                String.format(TEMPLATE, name),
                TEMPLATE,
                date);
    }
}
