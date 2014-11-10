
package edu.sjsu.cmpe282.api.resources;

import java.util.ArrayList;


import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.DELETE;

import org.codehaus.jettison.json.JSONArray;

import edu.sjsu.cmpe282.domain.Product;
import edu.sjsu.cmpe282.domain.User;
import edu.sjsu.cmpe282.dto.ProductDao;


import com.amazonaws.util.json.JSONObject;
import com.amazonaws.services.dynamodbv2.model.ScanResult;



@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ProductResource {
 
	   	ProductDao productdao = new ProductDao();
		
		@GET
		@Path("/viewProduct")
		@Produces("application/json")
		//@Produces("application/xml")
		public ScanResult fetchProducts() {
			
			ScanResult result;
			result = productdao.displayProducts();
			return result;
			
			
		}
		
		@GET
		@Path("/viewCart")
		@Produces("application/json")
		//@Produces("application/xml")
		public ScanResult fetchCart() {
			
			ScanResult result;
			result = productdao.displayCart();
			return result;
			
			
		}

		@POST
		@Path("/cart")
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addToCart(Product product) throws ClassNotFoundException {
			System.out.print("In cart");
			System.out.print(".......");
			System.out.print(".......");
			System.out.print(".......");
			System.out.print(".......");
			//Product product = new Product("Dell", "Vostro 3500", "400","36");
			//System.out.print("Product added to shoppin cart table: "+product.getproductID());
			
			String result = productdao.addCart(product);
			//String result = product.toString();
			System.out.print("Result: "+result);
			return Response.status(200).entity(result).build();
			
			//Response.status(201).entity("User Created : \n"+ user.getFirstName()).build();
		}
	
		 
		@POST
		@Path("/delete")
		@Consumes(MediaType.APPLICATION_JSON)
		  public void deleteItemFromCart(Product product) throws ClassNotFoundException {
			// input is productID sent from frontend
			System.out.print("REST.... Delete item from cart");
			String result = productdao.deleteProduct(product);
			System.out.print("Delete Product Result: "+result);
			//return Response.status(200).entity(result).build();
		  }
   
		@GET
		@Path("/confirm")
		@Produces("application/json")
		public Response confirmShopping() throws ClassNotFoundException {
			System.out.print("REST... Confirm products for checkout");
			//Product product = new Product("Dell", "Vostro 3500", "400","36");
			//System.out.print("Product added to shoppin cart table: "+product.getproductID());
			ProductDao productdao = new ProductDao(); 
			int result = productdao.onConfirm();
			//String result = product.toString();
			System.out.print("Result: "+result);
			return Response.status(200).entity(result).build();
			
			//Response.status(201).entity("User Created : \n"+ user.getFirstName()).build();
		}
		
		
		 @GET
	       @Path("/viewallproduct")
	       @Produces(MediaType.APPLICATION_JSON)
	       public JSONArray viewallproduct()
	       {
			 
			 System.out.println("into produt resource rest");
	               ProductDao d = new ProductDao();
	               System.out.println("abc");
	               ArrayList<Product> productlist = d.viewallproducts();
	               JSONArray jsonArray = new JSONArray();
	               for(int i =0;i< productlist.size();i++)
	               {
	                       jsonArray.put(productlist.get(i).getJSONObject());
	               }
	               
	               System.out.println(jsonArray);
	               return jsonArray;
	       //        a = userdao.viewselectedproduct(prod);
	       //        System.out.println(a);
	               //return a;
	       } 
	 
}
