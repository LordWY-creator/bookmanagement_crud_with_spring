<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Your Cart</title>
    <!-- Add necessary CSS/Bootstrap styling -->
</head>
<body>
    <h2>Your Cart</h2>

    <c:if test="${not empty cartList}">
        <table class="table">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total Price</th>
                    <th>Remove</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${cartList}">
                    <tr>
                        <td>${item.title}</td>
                        <td>${item.author}</td>
                        <td>${item.price}</td>
                        <td>${item.quantity}</td>
                        <td>${item.price * item.quantity}</td>
                        <td><a href="removeFromCart?id=${item.cart_id}" class="btn btn-danger">Remove</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
    <c:if test="${empty cartList}">
        <p>Your cart is empty.</p>
    </c:if>
    <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>
    

    <a href="order" class="btn btn-success">Place Order</a>
</body>
</html>
