package hello;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import hello.jsontemplates.Beaches;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PutItemController {

    private Logger logger = LoggerFactory.getLogger(PutItemController.class);

    @PostMapping(value="/putitem", produces = {"application/json"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Beaches putItemIntoDB(@RequestBody Beaches beaches) {

        logger.info("the beaches going in are");
        logger.info(beaches.getBeach().toString());

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8002", "us-west-2"))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        Table table = dynamoDB.getTable("Beaches");

        try {
            for (String site : beaches.getBeach()) {
                Item item = new Item().withPrimaryKey("beach", site);
                logger.info("Updating the item...");
                table.putItem(item);
                logger.info("UpdateItem succeeded:\n");
            }
        }
        catch (Exception e) {
            logger.error("Unable to update item: " + beaches);
            logger.error(e.getMessage());
        }

        return new Beaches(new ArrayList<>());
    }
}
