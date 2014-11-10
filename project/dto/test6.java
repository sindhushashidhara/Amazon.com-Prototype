package edu.sjsu.cmpe282.dto;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

//import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
 
public class test6 {

	
		 
	  public static void main(String[] args) {
		  ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(getBaseURI());

			
			

			//ClientResponse response = webResource.type(MediaType.APPLICATION_JSON).delete(ClientResponse.class,jsonuser);

			System.out.println(service.path("rest").path("products/viewProduct").accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN).get(ClientResponse.class).getEntity(String.class));
			
		}

		private static URI getBaseURI() {
			return UriBuilder.fromUri("http://localhost:8080/CloudServices").build();
		}
	
}
