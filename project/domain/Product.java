package edu.sjsu.cmpe282.domain;
import javax.xml.bind.annotation.*;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


@XmlRootElement
public class Product {
	
	private String productID;
	private String productDescription;
	private int price;
	private int quantity;
	//private String productCatalogID;
		
	public Product() {
		super();
	}
	
		
	public Product(String productID, String productDescription, int price, int quantity) {
		super();
		//this.productCatalogID = productCatalogID;
		this.productID = productID;
		this.productDescription = productDescription;
		this.price = price;
		this.quantity = quantity;
	}
    /*
	public String getproductCatalogID() {
		return productCatalogID;
	}
	public void setproductCatalogID(String productCatalogID) {
		this.productCatalogID = productCatalogID;
	}
    */
	public String getproductID() {
		return productID;
	}
	public void setproductID(String productID) {
		this.productID = productID;
	}
	public String getproductDescription() {
		return productDescription;
	}
	public void setproductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public int getprice() {
		return price;
	}
	public void setprice(int price) {
		this.price = price;
	}
	public int getquantity() {
		return quantity;
	}
	public void setquantity(int quantity) {
		this.quantity = quantity;
	}
    
	/*@Override
	public String toString() {
		return new StringBuffer(" Product : ").append(this.productID)
				.append(" Product Description : ").append(this.productDescription)
				.append(" Price : ").append(this.price).append(" Quantity : ")
				.append(this.quantity).toString();
	}
*/
       public JSONObject getJSONObject() {
               // TODO Auto-generated method stub
               
               JSONObject obj = new JSONObject();
               try
               {
                       
                        obj.put("productID", productID);
                   obj.put("productDescription", productDescription);
                   obj.put("price", price);
                   obj.put("quantity", quantity);
                   
               } catch (JSONException e) {
                   System.out.println("DefaultListItem.toString JSONException: "+e.getMessage());
               }
               return obj;
               
       }
               
               
       


}
