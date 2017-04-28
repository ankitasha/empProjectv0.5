<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List of Products</title>
<style type="text/css">

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
    	<th width="120">Product Code</th>
		<th width="120">Product Name</th>
	</tr>
 <c:forEach var="productlist" items="${productlist}" varStatus="status">
    <tr>
       <td id="row">${productlist.code}</td>
       <td id="row">${productlist.name}</td>
 	<td style="background-color: #5CACEE"><a href="buy?code=${productlist.code}">BUY</a></td>
   </tr>
</c:forEach>
</table>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <form method="get" action="orderhistory">
    	<button id="orderhistory" type="submit">Order History</button>
    </form>
     <br>
    <br>
    <br>
    <br>
  	<form method="get" action="login">
    	<button id="login" type="submit">LOGIN</button>
    </form>
    <br>
    <br>
     <form method="get" action="signup">
    	<button id="signup" type="submit">SIGNUP</button>
    </form>
    <br><br><br><br>
    <form method="get" action="logout">
    	<button id="logout" type="submit">LOGOUT</button>
    </form>
      <div><%=session.getAttribute("user_session") %></div>   
</body>
</html>