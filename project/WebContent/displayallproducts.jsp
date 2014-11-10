<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.min.js"></script>
	
<head>
<title>HTML Frames Example - Content</title>
<style type="text/css">
	table,td,th
	{
	border:2px double black;
	border-style:double;
	border-color:#000000;
	}
	table
	{
	width:100%;
	}
	th
	{
	height:30px;
	vertical-align:bottom;
	padding:15px;
	}
	td
	{
	height:30px;
	vertical-align:bottom;
	padding:15px;
	
	}
	</style>
</head>

<body bgcolor='#ABADB3' onload ='LogInfun();'>

<script type="text/javascript">



function AddToCart(Product) {
	console.log(Product);
	//console.log(ProductId);
	//alert(Product);
	var URL = "http://localhost:8080/CloudServices/rest/products/addtocart";
	$.ajax({
		type: "POST",
		url: URL,
		contentType: "text/plain",
		dataType: 'text',
		data : Product,
			//success: function () { //success(data); }
		success: function(data, textStatus, jqXHR){
			
				alert(data);
				
				//window.location = 'Main.jsp'
			},
		error: function(textStatus, jqXHR,errorThrown){
			alert(textStatus+" "+jqXHR);
		}

	});

}


function LogInfun(){

	var URL = "http://localhost:8080/CloudServices/rest/users/viewallproduct";
	//alert("signin button clicked"+formToJSON());
	
	$.ajax({
			type: "GET",
			url: URL,
			contentType: "application/json",
			dataType: 'json',
			//data : formToJSON(),
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("you are logged in");
					var obj = data;
				    var tbl =$("<table  bgcolor='#E8EAF5'> <th>ProductID</th><th>Product Description</th><th>Price</th><th>quantity</th><table/>").attr("id","mytable");
				    $("#tab").append(tbl);
				    for(var i=0;i<obj.length;i++)
				    {
				    	var tr="<tr>";
					    var td1="<td>"+obj[i]["Name"]+"</td>";
					    var td2="<td>"+obj[i]["Id"]+"</td>";
					    var td3="<td>"+obj[i]["Price"]+"</td>";
					    var td4="<td>"+obj[i]["Quantity"]+"</td>";
					    //var td6= "<td>"+"<button onclick=\"AddToCart\"("+obj[i]["ProductName"]+")>Add To Cart</button></td>";
			           var td6 ="<td>"+'<input type = button value = "Add To Cart" onclick="AddToCart(\'' + obj[i]["ProductId"]+ "," + obj[i]["ProductName"] + "," + obj[i]["Description"] + "," + obj[i]["Price"] +"," + obj[i]["Quantity"] +'\')" />'+"</td>";		
					    $("#mytable").append(tr+td1+td2+td3+td4+td6); 
					
				    }
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
}

</script>
<form>
<h2 background-color= '#4267F8'>Products</h2>
<div class="navbar" align="center">
<form class="well span12" onsubmit="return false;">
<div class="navbar">
<div class="navbar-inner">
<div class="container">
<ul class="nav">
<li class="active span6"></li>
</ul>
<div class ="span2" align="right"><br/><a href="Cart.jsp">My Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=" /CloudServices/index.jsp">SignOut</a>&nbsp;&nbsp;&nbsp;</div>

</div>
</div>
</div>
<div id="div1"></div>
</form>
</div>
<div id="tab" >
</div>
</form>

</body>

</html>