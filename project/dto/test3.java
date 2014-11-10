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

public class test3 {
	
	public static void main(String[] args) {
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);
		WebResource service = client.resource(getBaseURI());

		JSONObject jsonuser = new JSONObject();
		try
		{
			jsonuser.put("productID", "Dell");
			jsonuser.put("productDescription", "Vostro 3500");
			jsonuser.put("price", "400");
			jsonuser.put("quantity", "24");

		} catch(Exception e){}

		
		
		System.out.println(service.path("rest").path("products/cart").accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN).post(ClientResponse.class, jsonuser).getEntity(String.class));
		
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/CloudServices").build();
	}

}
*/