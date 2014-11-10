<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">



function LogInfun(){

	var URL = "http://localhost:8080/CloudServices/rest/users/viewallproduct";
	//alert("signin button clicked"+formToJSON());
	
	$.ajax({
			type: "GET",
			url: URL,
			contentType: "application/json",
			dataType: "json",
			//data : formToJSON(),
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("you are logged in");
					var obj = data;
				    var tbl =$("<table/>").attr("id","mytable");
				    $("#tab").append(tbl);
				    for(var i=0;i<obj.length;i++)
				    {
				    	var tr="<tr>";
					    var td1="<td>"+obj[i]["Name"]+"</td>";
					    var td2="<td>"+obj[i]["Id"]+"</td>";
					    var td3="<td>"+obj[i]["Price"]+"</td>";
					    var td4="<td>"+obj[i]["Quantity"]+"</td>";
					    //var td6= "<td>"+"<button onclick=\"AddToCart\"("+obj[i]["ProductName"]+")>Add To Cart</button></td>";
			           var td6 ="<td>"+'<input type = button value = "Add To Cart" onclick="AddToCart(obj)"/>'+"</td>";	
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
<div id="tab" >
</div>
</form>

</body>

</html>