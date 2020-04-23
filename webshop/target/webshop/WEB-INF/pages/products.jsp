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
    <script src="../js/cart-operation.js"></script>
</head>
<body>
<hr/>

<div class="container">
  <div class="row">
  <div class="col-3">
  <label> Search by categories </label>
   <form action="/products"  name="filter" method="get" >
     <div class="form-group">
           <c:forEach items="${sessionScope.categories}" var="category">
              <input type="checkbox" name="category-checkbox" value="${category.id}"
                 <c:if test="${fn:contains(sessionScope.filter.categories, category.id ) }">checked</c:if>
               >  ${category.name}
               </br>
            </c:forEach>
         </select>
     </div>
     </div>
      <div class="col-3">
      <label> Search by makers </label>
     <div class="form-group">
               <c:forEach items="${sessionScope.makers}" var="maker">
                 <input type="checkbox" name="maker-checkbox" value="${maker.id}"
                   <c:if test="${fn:contains(sessionScope.filter.manufacturers, maker.id ) }">checked</c:if>
                  > ${maker.name}
                </br>
               </c:forEach>
      </div>
      </div>
       <div class="col-3">
       <label> Search products </label>
      <div class="from-group">
           <input class="form-control" type="text" name="name-filter" placeholder="By product name">
      </div>
      </br>
        <div class="from-group">
                <input  class="form-control" type="number" name="min-price" placeholder="Enter min price" min="0">
         </div>

          </br>
         <div class="from-group">
                 <input class="form-control" type="number" name="max-price" placeholder="Enter product max price" min="0">
         </div>
         </div>
          <div class="col-3">

             <label> Sort products </label>
            <div class="from-group">
                <select  class="form-control" name="order-by">
                    <option value="default">Default</option>
                    <option value="product_name">By name A-Z</option>
                    <option value="product_name DESC">By name Z-A</option>
                    <option value="product_price">By price 1-9</option>
                    <option value="product_price DESC">By price 9-1</option>
                </select>
            </div>

                 <label> Number products on page</label>
                          <div class="from-group">
                             <select  class="form-control" name="product-limit">
                                  <option value="4">4 products on page</option>
                                  <option value="8">8 products on page</option>
                                  <option value="12">12 products on page</option>
                                  <option value="24">24 products on page</option>
                            </select>
                     </div>
              </br>
            <button type="submit"  class="btn btn-outline-dark"><fmt:message key="page.lang.submit"/></button>
         </div>

    </form>
   </div>

<div class="row row-cols-1 row-cols-md-4 row-cols-mb-4">
<c:choose>

    <c:when test="${empty sessionScope.products}">
          <h2>Products not found.</h2>
      </c:when>
            <c:otherwise>

                      <c:forEach items="${sessionScope.products}" var="product">

                      <div class="col-mb-4 products">
                          <div class="col-sm-12">
                              <div class="product">

                                  <div class="product-img" >
                                      <a href="/cart"><img src="images/${product.image}" alt=""></a>
                                  </div>

                                  <p class="product-title">
                                      <a href="#">${product.name}</a></br>
                                    <input placeholder="Add to cart" data-parameter="${product.id}" type="submit"  value="<fmt:message key="page.lang.add"/>" class="button addToCart" />
                                  </p>
                                  <p class="product-desc"><fmt:message key="page.lang.category"/> : ${product.category.name} </br> <fmt:message key="page.lang.maker"/> : ${product.manufacturer.name}  </p>
                                  <p class="product-price"><fmt:message key="page.lang.price"/> ${product.price}$</p>

                              </div>
                          </div>
                        </div>

                      </c:forEach>
                   </div>
             </c:otherwise>
        </c:choose>
        <c:if test="${sessionScope.productCount > sessionScope.filter.productLimit}">
           <nav aria-label="Page navigation example">
             <label> <fmt:message key="page.lang.page"/> <label>
                <ul class="pagination justify-content-center">
                  <c:forEach var="i" begin="1" end="${sessionScope.productCount /sessionScope.filter.productLimit + (sessionScope.productCount % sessionScope.filter.productLimit == 0 ? 0 : 1)}">
                    <li class="page-item"><a class="page-link" href="${contextPath}?page=${i -1}"><c:out value="${i}"/></a></li>
                   </c:forEach>
                </ul>
           </nav>
        </c:if>
    </div>

  </body>
</html>