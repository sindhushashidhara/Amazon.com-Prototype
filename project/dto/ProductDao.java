package edu.sjsu.cmpe282.dto;
import edu.sjsu.cmpe282.domain.Product;




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
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.services.dynamodbv2.model.UpdateItemResult;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import java.io.IOException;

import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.amazonaws.auth.policy.Condition;
import com.amazonaws.services.cloudwatch.model.ComparisonOperator;
//import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
//import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;

import com.amazonaws.services.dynamodbv2.model.QueryResult;

import com.amazonaws.util.json.JSONObject;

import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;


public class ProductDao {

	
	static AmazonDynamoDBClient client;
	static String tableName1 = "Product";
	static String tableName2 = "Cart";
	//private String productCatalogID;
	private String productID;
	private String productDescription;
	private int price;
	private int quantity;
	private int flag;
	private static int totalPrice = 0;
	private static AmazonIdentityManagementClient iamServ;
	static String replyTableName = "Reply";
	static String forumTableName = "Forum";
    static String threadTableName = "Thread";
    
    public static final String ACCESSKEY = "AKIAJHIQV534PKAOHQRQ";
	public static final String SECRETKEY = "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";
	
	Connection conn = null;
    Statement stmt = null;
    
	
	
	
	public ProductDao() {
		createClient();
		
		
		try{
		      try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		      conn = DriverManager.getConnection("jdbc:mysql://localhost/Cloud","root","root");
		   }
		   catch (SQLException e) {
					e.printStackTrace();
					
			}
			
	}
	public static void createClient()  {
		
		//AmazonEC2 ec2 = new  AmazonEC2(myCredentials);
		//ec2.setEndpoint("https://eu-west-1.ec2.amazonaws.com");
		//AWSCredentials credentials = new PropertiesCredentials(
				//test.class.getResourceAsStream("AwsCredentials.properties"));
		try{
		BasicAWSCredentials credentials = new BasicAWSCredentials(ACCESSKEY,
				SECRETKEY);
        client = new AmazonDynamoDBClient(credentials);
        
        client.setRegion(Region.getRegion(Regions.US_WEST_1)); 
       //client.setEndpoint("https://eu-west-1.ec2.amazonaws.com");
       System.out.println("AWSCredentials Authenticated");
       
       
		
		}
		catch (AmazonServiceException ase) {
			  
			 System.err.println("Error in authenticating");
			 System.err.println(ase.getMessage());
		}
	}
	
	public static void addProduct() {
		
		  try {
			  
			  Map<String, AttributeValue> item1 = new HashMap<String, AttributeValue>();
			  System.out.println("Add items to HashMap");
			 // item1.put("productCatalogID", new AttributeValue().withS("103"));
			  item1.put("productID", new AttributeValue().withS("HP"));
			  item1.put("productDescription", new AttributeValue().withS("15.6 inch Laptop PC"));
			  item1.put("price", new AttributeValue().withS("260"));
			  item1.put("quantity", new AttributeValue().withS("12"));
			  System.out.println("Adding item to table");
			  //PutItemRequest putItemRequest1 = new PutItemRequest().withTableName(tableName).withItem(item1);
			  PutItemRequest putItemRequest1 = new PutItemRequest(tableName1, item1);
			  System.out.println(putItemRequest1);
			  PutItemResult result = client.putItem(putItemRequest1);
			  item1.clear();
			  
			  System.out.println("Result = " + result);
		  }
		  
		  catch (AmazonServiceException ase) {
			  
			  System.err.println("Error in createing items in " + tableName1);
			  System.err.println(ase.getMessage());
		  }
		
	}  
		  public void getProduct(String productID) {
			  
			 
				
			  System.out.println("In getting products....");	  
			 Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
		        key.put("productID", new AttributeValue().withS(productID));
		        
		        GetItemRequest getItemRequest = new GetItemRequest()
		            .withTableName(tableName1)
		            .withKey(key)
		            .withAttributesToGet(Arrays.asList("productID", "productDescription", "price", "quantity"));
		        
		        System.out.println(getItemRequest);
		        
		        GetItemResult result = client.getItem(getItemRequest);
		        
		        // Check the response.
		        System.out.println("Printing item after retrieving it....");
		        printItem(result.getItem());    
			  
			  
	}
		  //Display Products
		  public  ScanResult displayProducts() {
		    	
		    	ScanRequest scanRequest = new ScanRequest()
		        .withTableName(tableName1)
		        .withAttributesToGet(Arrays.asList("productID", "productDescription", "price", "quantity"));

		    ScanResult result = client.scan(scanRequest);
		    
		    System.out.println("Scan of " + tableName1 + " for all items.");
		    for (Map<String, AttributeValue> item : result.getItems()) {
		        System.out.println(".....");
		        printItem(item);
		        System.out.println("After printItem");
		    }
		    System.out.println("Result" + result);
		    return result;
		    
		    }
		  //Display Cart
		  public  ScanResult displayCart() {
		    	
		    	ScanRequest scanRequest = new ScanRequest()
		        .withTableName(tableName2)
		        .withAttributesToGet(Arrays.asList("productID", "productDescription", "price", "quantity","totalPrice"));

		    ScanResult result = client.scan(scanRequest);
		    
		    System.out.println("Scan of " + tableName2 + " for all items.");
		    for (Map<String, AttributeValue> item : result.getItems()) {
		        System.out.println(".....");
		        printItem(item);
		        System.out.println("After printItem");
		    }
		    System.out.println("Result" + result);
		    return result;
		    
		    }
	
	
		  private static void printItem(Map<String, AttributeValue> attributeList) {
		        for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
		            String attributeName = item.getKey();
		            AttributeValue value = item.getValue();
		            System.out.println(attributeName + " "
		                    //+ (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                   // + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                   // + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                   // + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                   // + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                    + (value.getS() == null ? "" : "S=[" + value.getS() + "] \n"));
		        }
		    }
	
		  
		         public String addCart(Product product) {
				
			  
			    try { 
			    
				  
				  Map<String, AttributeValue> item1 = new HashMap<String, AttributeValue>();
				  System.out.println("Add items to HashMap");
				 // item1.put("productCatalogID", new AttributeValue().withS("103"));
				  item1.put("productID", new AttributeValue().withS(product.getproductID()));
				  item1.put("productDescription", new AttributeValue().withS(product.getproductDescription()));
				  item1.put("price", new AttributeValue().withS(Integer.toString(product.getprice())));
				  item1.put("quantity", new AttributeValue().withS(Integer.toString(product.getquantity())));
				  
				  //item1.put("flag", new AttributeValue().withN("0"));
				  System.out.println("Adding item to table");
				  //PutItemRequest putItemRequest1 = new PutItemRequest().withTableName(tableName).withItem(item1);
				  PutItemRequest putItemRequest1 = new PutItemRequest(tableName2, item1);
				  System.out.println(putItemRequest1);
				  PutItemResult result = client.putItem(putItemRequest1);
				  item1.clear();
				  System.out.println("............");
	            	System.out.println("............");
	            	System.out.println("............");
	            	System.out.println("............");
				  System.out.println("Item added to cart = " + result);
				  System.out.println("............");
	            	System.out.println("............");
	            	System.out.println("............");
	            	System.out.println("............");
				  return "success";
				  
				
			  }
			  
			  catch (AmazonServiceException ase) {
				  
				  System.err.println("Error in creating items in " + tableName2);
				  System.err.println(ase.getMessage());
			  }
			    
			return "success";
			
			  
		}  
		  
		  public String deleteProduct(Product product) {
			  try {
				  
				  System.out.println(" Product Dao");
			  HashMap<String, AttributeValue> key = new HashMap<String, AttributeValue> ();
			  //key.put("productId", new AttributeValue().withS(product.getproductID()));
			  key.put("productID", new AttributeValue().withS(product.getproductID()));
			  DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
			      .withTableName(tableName2)
			      .withKey(key);
			   System.out.println("Delete Item Request"+ deleteItemRequest);     	
			   System.out.println("............");
           	System.out.println("............");
           	System.out.println("............");
           	System.out.println("............");
			  DeleteItemResult deleteItemResult = client.deleteItem(deleteItemRequest);
			  System.out.println("Item deleted from cart = " + deleteItemResult);
			  System.out.println("............");
          	System.out.println("............");
          	System.out.println("............");
          	System.out.println("............");
			  }

			  catch (AmazonServiceException ase) {
				  
				  System.err.println("Error in deleting items from " + tableName2);
				  System.err.println(ase.getMessage());
			  }
			
			return "success";
		  }
		  
		  //Update quantity
		  
		  public int onConfirm() {
			 
			  int result = 0;
			  System.out.println("Fetch cart table values");
			  		  
			  ScanRequest scanRequest2 = new ScanRequest()
			  .withTableName(tableName2)
			  .withAttributesToGet(Arrays.asList("productID", "productDescription", "price", "quantity"));

			  ScanResult result2 = client.scan(scanRequest2);
	    
			  System.out.println("Scan of " + tableName2 + " for all items.");
			  for (Map<String, AttributeValue> item2 : result2.getItems()) {
				  System.out.println(".....");
				  System.out.println("Before updateQuantity");
				  updateQuantity(item2);
				  System.out.println("After updateQuantity");
				  System.out.println("Result - Total Price: " + result);
					 
				  
				  
			  }
	    
			  return totalPrice;
	    
	   
		  }
	    
	    
		  private int updateQuantity(Map<String, AttributeValue> attributeList) {
			  String tempProductID = null;
			 String tempPrice = null;
			 String tempQuantity = null;
			  String productIDToUpdate = null;
			  int quantityToUpdate = 0;
			  System.out.println("Obtain each row from cart table");
		        for (Map.Entry<String, AttributeValue> item : attributeList.entrySet()) {
		            String attributeName = item.getKey();
		            AttributeValue value = item.getValue();
		            System.out.println(attributeName + " "
		                    //+ (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                   // + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                   // + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                   // + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                   // + (value.getS() == null ? "" : "S=[" + value.getS() + "]")
		                    + (value.getS() == null ? "" : "S=[" + value.getS() + "] \n") + (value.getN() == null ? "" : "S=[" + value.getN() + "] \n"));
		            if(attributeName ==  "productID" ) 
		            	tempProductID = item.getKey();
		            	System.out.println("tempProductID: " + tempProductID);
		            if(attributeName == "price") {
		            	//tempPrice = Integer.parseInt(item.getValue());
		            	tempPrice = value.getS();
		            	System.out.println("tempPrice: " + tempPrice);
		            	
		            	totalPrice = totalPrice + Integer.parseInt(tempPrice); 
			            System.out.println("Total quantity: " + totalPrice);
		            }
		            
		            if(attributeName == "quantity") 
			            	//tempPrice = Integer.parseInt(item.getValue());
		            	tempQuantity = value.getS();
			            	System.out.println("tempQuantity: " + tempQuantity);
			            	
			           
		            	System.out.println("............");
		            	System.out.println("............");
		            	System.out.println("............");
		            	System.out.println("............");
		        
		            
		            try {
		  			  stmt = conn.createStatement();
		  			  ResultSet rs;
		  			  String query1 = "Select productID,price from Cloud.products" + " where productID = " + tempProductID;
		  			  rs = stmt.executeQuery(query1);
		  			  while (rs.next()) {
		  			 // Product product = new Product();
		  			productIDToUpdate = rs.getString(1);
		  			System.out.println("productIDToUpdate " +rs.getString(0));
		  			   quantityToUpdate = rs.getInt(2);
		  			System.out.println("priceToUpdate " +rs.getString(2));
		  			
		  			  }
		            }
		            catch (SQLException e) {
		       			// TODO Auto-generated catch block
		       			e.printStackTrace();
		       		}
		  			 
		  			System.out.println("Qty in Product = Qty in Product - Qty in Cart");
		  			quantityToUpdate = quantityToUpdate - Integer.parseInt(tempQuantity); ;
		  			//quantityToUpdate = quantityToUpdate + Integer.parseInt((value.getN()));
		  			System.out.println("Quantity to update in Product table " + quantityToUpdate );
		  			
		  			System.out.println("Update price in Product table");
		  			
		            try {
		       		 stmt = conn.createStatement();
		       		String query2 = "UPDATE products " +
		                    "SET quantity = "+  quantityToUpdate + " WHERE productID = "+ productIDToUpdate;
		            stmt.executeUpdate(query2);
		       		} catch (SQLException e) {
		       			// TODO Auto-generated catch block
		       			e.printStackTrace();
		       		}
		  			    
		            
		        }
		            return totalPrice;
		  }
		
		  public  ArrayList<Product> viewallproducts(){
			  System.out.println("start db");
			  ResultSet rs;
			  ArrayList<Product> productArray = new ArrayList<Product>();
			  try {
			  stmt = conn.createStatement();
			  String query = "Select * from Cloud.products";
			  rs = stmt.executeQuery(query);
			  while (rs.next()) {
			  Product product = new Product();
			  product.setproductID(rs.getString(1));
			     System.out.println(rs.getString(1));
			     product.setprice(rs.getInt(3));		  
			  product.setquantity(rs.getInt(4));
			  productArray.add(product);
			  //System.out.println(rs.getString("ProductName"));
			  }

			  } catch (SQLException e) {
			  // TODO Auto-generated catch block
			  e.printStackTrace();
			  }

			  System.out.println("end db");
			  return productArray;
			  }
			 
			 
			 
		  
		  
		  
}
