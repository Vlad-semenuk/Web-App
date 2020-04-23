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
<body>
<hr/>

<div class="container">
 <h3 align="center">Cart</h3>

<c:choose>
    <c:when test="${empty sessionScope.cart}">
          <h2 align="center">Cart empty</h2>
      </c:when>
        <c:otherwise>
<table class="table">
  <thead>
    <tr>
      <th scope="col"><fmt:message key="page.lang.image"/></th>
      <th scope="col"><fmt:message key="page.lang.name"/></th>
      <th scope="col"><fmt:message key="page.lang.price"/></th>
      <th scope="col"><fmt:message key="page.lang.count"/></th>
      <th scope="col"><fmt:message key="page.lang.remove"/></th>
    </tr>
  </thead>
  <tbody>
  <c:forEach var="item" items="${sessionScope.cart.getAllItems()}">
    <tr>
      <th scope="row"><img src="images/${item.key.image}" style="width: 80px; height: 80px;"></th>
      <td>${item.key.name}</td>
      <td>${item.key.price}</td>
      <td>
          <form action="/cartOperations" method="post">
            ${item.value}:
            <input type="hidden" name="id" value="${item.key.id}">
            <input type="number" name="quantity" min="1" max="100">
            <button type="submit" name="operation" value="edit" class="btn btn-outline-dark"><fmt:message key="page.lang.edit"/></button>
          </form>
        </td>
      <td>
      <form action="/cartOperations" method="post">
         <input type="hidden" name="id" value="${item.key.id}">
         <button type="submit" name="operation" value="remove" class="btn btn-outline-dark"><fmt:message key="page.lang.remove"/></button>
        </form>
        </td>
    </tr>

    </c:forEach>

  </tbody>
</table>

          <form action="/cartOperations" method="post" >
                    <input type="hidden" name="id" value="0">

                    <c:if test="${not empty sessionScope.user}">
                           Total price : ${sessionScope.totalPrice}
                           <a href="/createOrder"  class="btn btn-outline-dark"><fmt:message key="page.lang.make.order"/></a></button>
                    </c:if>
                    <c:if test="${empty sessionScope.user }">
                           Total price : ${sessionScope.totalPrice}
                           <a href="/register"  class="btn btn-outline-dark"><fmt:message key="page.lang.register"/></a>
                    </c:if>
                    <button type="submit" name="operation" value="clear" class="btn btn-outline-dark"><fmt:message key="page.lang.clear"/></button>
          </form>
  </c:otherwise>
</c:choose>

 </div>
</body>
</html>