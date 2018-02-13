package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {

        // Get data from dynamodb
        // TODO reformat object class and rebuild with specific class
        RestTemplate restTemplate = new RestTemplate();
        Object quote = restTemplate.getForObject("http://localhost:8002/api/random", Object.class);

        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}