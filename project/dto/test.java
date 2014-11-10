/*package edu.sjsu.cmpe282.dto;
//import edu.sjsu.cmpe282.domain.Product;

import com.amazonaws.AmazonServiceException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeAction;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.DeleteItemResult;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.PutItemResult;
import com.amazonaws.services.dynamodbv2.model.ReturnValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
public class test {

	//static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	static AmazonDynamoDBClient client;
	static String tableName = "product";
	private String productCatalogID;
	private String productID;
	private String productDescription;
	private String price;
	private String quantity;
	private static AmazonIdentityManagementClient iamServ;
	static String replyTableName = "Reply";
	static String forumTableName = "Forum";
    static String threadTableName = "Thread";
    
    public static final String ACCESSKEY = "AKIAJHIQV534PKAOHQRQ";
	public static final String SECRETKEY = "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";
	
	public static void main(String[] args) throws IOException {
		
		createClient();
		try {
			
			//addProduct(tableName);
			getProduct(tableName);
		}
     catch (AmazonServiceException ase) {
        System.err.println("Data load script failed: " + ase);
        ase.printStackTrace();
    }
	
	}
	
	private static void createClient() throws IOException {
		
		//AmazonEC2 ec2 = new  AmazonEC2(myCredentials);
		//ec2.setEndpoint("https://eu-west-1.ec2.amazonaws.com");
		//AWSCredentials credentials = new PropertiesCredentials(
				//test.class.getResourceAsStream("AwsCredentials.properties"));
		BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESSKEY,
				SECRETKEY);
        client = new AmazonDynamoDBClient(credentials);
        
        client.setRegion(Region.getRegion(Regions.US_WEST_1)); 
       client.setEndpoint("https://eu-west-1.ec2.amazonaws.com");
	}
	
	private static void addProduct(String tableName) {
		
		  try {
			  
			  Map<String, AttributeValue> item1 = new HashMap<String, AttributeValue>();
			  item1.put("productCatalogID", new AttributeValue().withS("101"));
			  item1.put("productID", new AttributeValue().withS("HP"));
			  item1.put("productDescription", new AttributeValue().withS("15.6 inch Laptop PC"));
			  item1.put("price", new AttributeValue().withS("354.96"));
			  item1.put("quantity", new AttributeValue().withS("12"));
			  
			  PutItemRequest putItemRequest1 = new PutItemRequest().withTableName(tableName).withItem(item1);
			 // PutItemRequest putItemRequest1 = new PutItemRequest(tableName, item1);
			  PutItemResult result = client.putItem(putItemRequest1);
			  
			  System.out.println("Result = " + result);
		  }
		  
		  catch (AmazonServiceException ase) {
			  
			  System.err.println("Failed to create items in " + tableName);
		  }
		
	}  
		  private static void getProduct(String tableName) {
				
			  try {
				  
				  HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue>();
				  key.put("productCatalogId", new AttributeValue().withN("101"));

				  GetItemRequest getItemRequest = new GetItemRequest()
				      .withTableName(tableName)
				      .withKey(key);

				  GetItemResult result = client.getItem(getItemRequest);
				  
				  Map<String, AttributeValue> map = result.getItem();
				  System.out.println("Result = " + map);
			  }
			  catch (AmazonServiceException ase) {
				  
				  System.err.println("Failed to create items in " + tableName);
			  }
	}
	
	
			  
	
	
}
*/
