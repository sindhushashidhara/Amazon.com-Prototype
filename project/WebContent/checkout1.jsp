<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">

totalAmount = 0;

function getCookie(personId)
{
	var c_value = document.cookie;
	var c_start = c_value.indexOf(" " + personId + "=");
	if (c_start == -1)
	{
	c_start = c_value.indexOf(personId + "=");
	}
	if (c_start == -1)
	{
		c_value = null;
	}
	else
	{
		c_start = c_value.indexOf("=", c_start) + 1;
		var c_end = c_value.indexOf(";", c_start);
		if (c_end == -1)
		{
		c_end = c_value.length;
		}
		c_value = unescape(c_value.substring(c_start,c_end));
	}
return c_value;
}

function getTotalAmount(jsonObj){
	
	for(var i =0;i<jsonObj.length;i++){
		totalAmount = totalAmount+jsonObj[i].Amount;
	}
}

window.onload = function(){
	
	$.ajax({
        Type: "GET",
        url: "http://localhost:8081/UniversityRecordSystemClient/getChargesDueServlet",
        data: "personId="+getCookie("personId"),
        success: function (msg) {
        	
        	var jsonObj = JSON.parse(msg);
        	getTotalAmount(jsonObj);
        	var dataArray = [ ['Charge', 'Due Date', 'Term', 'Amount'] ];
			for ( var i = 0; i < jsonObj.length; i++) {
				dataArray.push([ jsonObj[i].charge,
				                 jsonObj[i].dueDate,
				                 parseInt(jsonObj[i].term),
				                 jsonObj[i].Amount ]);
			}
		 	for ( var i = 0; i < jsonObj.length; i++) {
					$('#paymentTable').append('<tr><td id="charge">'+jsonObj[i].charge
							+'</td><td id="dueDate">'+jsonObj[i].dueDate
							+'</td><td id="term">'+jsonObj[i].term
							+'</td><td id="amount">'+jsonObj[i].Amount
							+'</td></tr>');

				}
		 	
		 	$('#totalChargeDue').text(totalAmount);
        },
        error: function (error) {
    		alert('error');
    		$('#error-message').text("Error message");
   		}
	});
};

function makePayment(){
	
	var div = document.getElementById('hidden_div');
	div.style.display = 'none';  
	if (div.style.display == 'none') {
	     div.style.display = '';
	 }
	$('#payment-Amount').text('');
	$('#payment-Amount').text(totalAmount);
}

function confirmPayment(){
	
	var paymentOptionNum = $('#paymentOptions').val();
	var cvvNumber = $('#cvvNum').val();
	var expiryDate = $('#expiryDate').val();
	var creditCardNum = $('#cardNum').val();
	var creditOrDebit = 'D';
	var ck_creditCardNum = /^\d{16}$/;
	var ck_expiryDate = /^([0]{1}?\d{1}|[1]{1}?[0-2]{1})\/[0-9]{2}$/;
	var ck_cvvNumber = /^\d{3}$/;
	if(!ck_creditCardNum.test(creditCardNum)){
		alert("Invalid Credit Card Number Format ..please enter 16 digit card number");
	} 
	
	
	else if(!ck_expiryDate.test(expiryDate)){
		alert("Invalid Expiry Date Format");
	}
	else if(!ck_cvvNumber.test(cvvNumber)){
		alert("Invalid CVV Number");
	}
	else if(paymentOptionNum == 'B'){
    	dataStrign = "personId="+getCookie("personId")+"&amount="+totalAmount+"&creditOrDebit="+creditOrDebit+"&bankAccNum="+paymentOptionNum;
    }
    else{
    	dataStrign = "personId="+getCookie("personId")+"&amount="+totalAmount+"&creditOrDebit="+creditOrDebit+"&creditCardNum="+creditCardNum;
    }
	
	$.ajax({
        Type: "GET",
        url: "http://localhost:8081/UniversityRecordSystemClient/makePaymentServlet",
        data:dataStrign,
        success: function (msg) {
        	alert('payment received successfully!');
        },
        error: function (error) {
    		alert('error');
   		}
	});
	
}

</script>
<head>
<title>make payment</title>
<style type="text/css">
body {
	font-family:verdana,arial,sans-serif;
	font-size:10pt;
	margin:30px;
	background-color:#FFFAF0;
	}
</style>
</head>
<body>
<center><h2>Charges Due</h2>

<br>
<div>
<table id="paymentTable" border=1>
	<tr>
		<td><b>Charges</b></td>
		<td><b>Due Date</b></td>
		<td><b>Term</b></td>		
		<td><b>Amount</b></td>
	</tr>
</table>
<br>
<table>
		
		<tr>
			<td style="padding-top: 30px; padding-left: 20px">Total charge due:</td>
			<td style="padding-top: 30px; padding-left: 20px"><label style="color: black" id="totalChargeDue"></label></td>
		</tr>
</table>
<br>
<br>
<input type="button" id="enroll" value="Pay Now!" onclick="makePayment();">
</div>
<div id="hidden_div" style="display:none">
	<table>
		
		<tr>
			<td style="padding-top: 30px; padding-left: 20px">Payment Options</td>
			<td style="padding-top: 30px; padding-left: 20px">
				<select id="paymentOptions">
					<option value="" disabled="disabled" selected="selected">payment method</option>
					<option value="C">credit card</option>
					<option value="D">debit card</option>
				<!-- 	<option value="B">wire transfer</option> -->
				</select>	
			</td>
		</tr>
		<tr>
			<td>Card Number</td><td><input type="text" id="cardNum" maxlength="16"></td>
		</tr>
		<tr>
			<td>expiration date</td><td><input type="text" id="expiryDate"></td>
		</tr>
		<tr>
			<td>CVV Number</td><td><input type="text" id="cvvNum"></td>
		</tr>
		<tr>
			<td><input type="button" value="confirm payment" id="confirmPayment" onclick="confirmPayment();"></td>
		</tr>
		
	</table>
</div>

</center>
</body>
</html>