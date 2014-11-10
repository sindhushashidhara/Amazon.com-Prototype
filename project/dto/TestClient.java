/*package edu.sjsu.cmpe282.dto;
import edu.sjsu.cmpe282.domain.Product;


import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import com.sun.jersey.api.json.JSONConfiguration;
public class TestClient {
	 
	
	
	 
	  public static void main(String[] args) {
		try {
	 
			//Client client = Client.create();
			Product product = new Product("Dell", "Vostro 3500", "400","36");
			ClientConfig clientConfig = new DefaultClientConfig();
			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
			Client client = Client.create(clientConfig);

	 
			WebResource webResource = client
			   .resource("http://localhost:8080/CloudServices/rest/products/cart");
			
			//String input = "{\"productID\":\"Dell\",\"productDescription\":\"Vostro 3500\",\"price\":\"400\",\"quantity\":\"36\"}";
	        System.out.println("....");
			ClientResponse response = webResource.accept("application/json").type("application/json")
					.post(ClientResponse.class, product);
			 
			System.out.println("ClientResponse " + response);
	 
			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}
			System.out.println(".....");
			String output = response.getEntity(String.class);
	          
			System.out.println("Output from Server .... \n");
			
			System.out.println(output);
	 
		  } catch (Exception e) {
	 
			e.printStackTrace();
	 
		  }
	 
		}
}
*/