<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id=checkout>
<form>
<h1>shipping</h1>
firstname:<input type="text" id="firstname">
lastname:<input type="text" id="lastname">
Address:<input type="text" id="address">
ZipCode:<input type="text" id="Zipcode">
<h1>Bank Details</h1>
cardnumber:<input type="text"id="cardnumber">
securitycode:<input type="text" id="securitycode">
<input type ="submit" value="submit" onclick="checkoutfunction()"/>
</form>
</div>

<script type="text/javascript">
function checkoutfunction()
{
	
	var URL = "http://localhost:8080/CloudServices/rest/users/signin";
	alert("signin button clicked"+formToJSON());
	
	$.ajax({
			type: "POST",
			url: URL,
			contentType: "application/json",
			dataType: 'json',
			data : formToJSON(),
				//success: function () { //success(data); }
			success: function(data, textStatus, jqXHR){
					alert("you are logged in");
				},
			error: function(textStatus, jqXHR,errorThrown){
				alert(textStatus+" "+jqXHR);
			}

		});
	
	
	
	
	}



</script>


</body>
</html>



