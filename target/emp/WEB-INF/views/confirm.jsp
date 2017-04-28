<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Confirmation Page</title>
<style>
#button {
     line-height: 12px;
     padding:5px;
     width: 100px;
     font-size: 8pt;
     font-family: tahoma;
     margin-top: 1px;
     margin-right: 0px;
     position:absolute;
     top:0;
     right:0;
 }
 
 #menu {
     line-height: 12px;
     padding:5px;
     width: 100px;
     font-size: 8pt;
     font-family: tahoma;
     margin-top: 1px;
     margin-right: 120px;
     position:absolute;
     top:0;
     right:0;
 }
 
 #row{
 	background-color: #C1C1C1;
 	}
</style>
</head>
<body style="background-color: #EEE9BF">
<table border="1">
  	<tr style="background-color: #BDFCC9">
        <th width="120">Order Id</th>
		<th width="120">Product Code</th>
		<th width="120">Order Date</th>
	</tr>
    <tr>
       <td id="row">${orderid}</td>
       <td id="row">${productId}</td>
       <td id="row">${orderDate}</td>
 	<td style="background-color: #5CACEE"><a href="confirm/${orders.orderid}">confirm</a></td>  
   </tr>

   </table>
    
    <form method="get" action="thankyou">
    	<button id="thankyou" type="submit">Completed Order</button>
    </form>
 
    
   <form method="get" action="homepage">
    	<button id="homepage" type="submit">Products Page</button>
    </form>
 
  <form method="get" action="logout">
    	<button id="logout" type="submit">LOGOUT</button>
    </form>
 
</body>
</html>