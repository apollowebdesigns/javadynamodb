package hello;


import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteTableController {
    
    private Logger logger = LoggerFactory.getLogger(DeleteTableController.class);

    public static final String DYNAMODB = "http://localhost:8002";

    @RequestMapping("/delete/table")
    public void createTable() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(DYNAMODB, "us-west-2"))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        String tablename = "Beaches";

        Table table = dynamoDB.getTable(tablename);

        try {
            logger.info("Attempting to delete table; please wait...");
            table.delete();
            table.waitForDelete();
            System.out.print("Success.");

        }
        catch (Exception e) {
            logger.error("Unable to delete table: ");
            logger.error(e.getMessage());
        }
    }

}
