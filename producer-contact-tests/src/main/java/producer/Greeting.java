package producer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Greeting {
    private long id;
    private String content;
    private String template;
    private String date;
}
