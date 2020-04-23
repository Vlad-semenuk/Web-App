<!DOCTYPE html>
<html lang="en">
<%@ taglib tagdir="/WEB-INF/tags" prefix="info" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jspf/header.jspf" %>
<%@include file="/WEB-INF/jspf/taglib.jspf" %>
<%@include file="/WEB-INF/jspf/bootstrap.jspf" %>
<%@include file="/WEB-INF/jspf/set_locale.jspf" %>

<head>
    <meta charset="UTF-8">
    <title>Web app</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
    <link href="../style/style.css" rel="stylesheet">
</head>
<body>

<hr/>

<div class="container">

     <form action="/createOrder" class="col-5"  id="log" method="post">
        <label><fmt:message key="page.lang.address"/></label>
        <input type="text" class="form-control" name="user_address" placeholder="Enter address for delivery">
        <label><fmt:message key="page.lang.card"/></label>
        <input type="text" class="form-control" name="user_card" placeholder="Enter your card number">
       <h4>Total price for all product : ${sessionScope.cart.getTotalPrice()}</h4>
       <button type="submit" id="log-submit" class="btn btn-outline-dark" align="center"><fmt:message key="page.lang.buy"/></button>
     </form>
</div>


</body>
</html>