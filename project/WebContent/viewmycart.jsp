<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
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

<body onload = LogInfun()>
<script type="text/javascript">

function deletefromcart(obj)
{
	alert("deletefromcart called");
	var URL ="http://localhost:8080/CloudServices/rest/users/deletetfromcart";
	
	$.ajax
	({
		
		type:"POST",
		url:URL,
		contenttype:'application/json',
		dataType:'json',
		data:obj,
		success: function(data, textStatus, jqXHR){
			alert("removed from cart");
			
			
		},
	error: function(textStatus, jqXHR,errorThrown){
		alert(textStatus+" "+jqXHR);
	}

});
}
	
function checkout()
{
	//window.location(checkout.jsp);
	window.parent.content.location.href="checkout.jsp";
}	



function LogInfun()
{
	
	alert("called");
	var URL ="http://localhost:8080/CloudServices/rest/users/viewfromcart";
	
	$.ajax
	({
		
		type:"GET",
		url:URL,
		contenttype:'application/json',
		dataType:'json',
		//data:formToJSON(),
		success:function(data,textstatus,jqXHR)
		{
			//console.log(id);
			alert(data);
			var obj =data;
		    var tbl =$("<table><th>ProductID</th><th>Product Name</th><th>Description</th><th>Price</th><th>Quanatity</th></table>").attr("id","mytable");
		    $.("#tab").append(tbl);

		    for(var i=0;i<obj.length;i++)
		    {

		    //console.log(obj);
		    console.log(obj[i]["Name"]);

		    var tr="<tr>";
		    var td1="<td>"+obj[i]["Name"]+"</td>";
		    var td2="<td>"+obj[i]["Id"]+"</td>";
		    var td3="<td>"+obj[i]["Price"]+"</td>";
		    var td4="<td>"+obj[i]["Quantity"]+"</td>";
		    //var td6= "<td>"+"<button onclick=\"AddToCart\"("+obj[i]["ProductName"]+")>Add To Cart</button></td>";
           var td6 ="<td>"+'<input type = "button" value ="remove fromcart" onclick="deletefromcart(obj)"/>'+"</td>";	
           $("#mytable").append(tr+td1+td2+td3+td4+td6); 

		    } //close for 
		    alert("you are logged");
		    //window.location = 'Main.jsp'
		    },//close for success
		    error : function(textStatus, jqXHR, errorThrown) 
		    {
		    alert(textStatus + " " + jqXHR);
		   	}//close fr error
		
		
	});//close ajax
	
}  //close func
	
</script>
<div class="navbar" align="center">
<form class="well span12" onsubmit="return false;">
<div class="navbar">
<div class="navbar-inner">
<div class="container">
<ul class="nav">
<li class="active"><a href=" /CloudServices/index.html">Products!!</a></li>
<li class="active span6"></li>
</ul>
<div class ="span2" align="right"><br/><a href="Cart.jsp">My Cart</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=" /CloudServices/index.jsp">SignOut</a>&nbsp;&nbsp;&nbsp;</div>

</div>
</div>
</div>
<div id="div1"></div>
</form>
</div>

</body>

</html>