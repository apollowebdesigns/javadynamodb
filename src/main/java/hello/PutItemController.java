package hello;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import hello.jsontemplates.Beaches;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Controller
public class PutItemController {

    @PostMapping(value="/putitem", produces = {"application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Beaches putItemIntoDB(@RequestBody Beaches inputBeaches) {

        System.out.println("the beaches going in are");
        System.out.println(inputBeaches);

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8002", "us-west-2"))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Beaches");

        List<String> beaches = new ArrayList<>();

        beaches.add("Exmouth");
        beaches.add("Dawlish");

        String beach = "Exmouth";

        try {
            for (String site : beaches) {
                Item item = new Item().withPrimaryKey("beach", site);
                System.out.println("Updating the item...");
                table.putItem(item);
                System.out.println("UpdateItem succeeded:\n");
            }
        }
        catch (Exception e) {
            System.err.println("Unable to update item: " + beach);
            System.err.println(e.getMessage());
        }

        return new Beaches(new ArrayList<>());
    }
}
