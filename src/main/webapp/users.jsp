<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<title>Users</title>
<%@include file="template/head.jsp" %>
<body>
<h3><a href="index.html">Home</a></h3>
<c:forEach items="${users}" var="user">
    <jsp:useBean id="user" type="ru.javawebinar.topjava.model.User"/>
    <div>${user.toString()}</div>
</c:forEach>
<div class="container">
    <div class="row justify-content-start mt-4">
        <form class="align-content-lg-center" method="post">
            <div class="form-group row">
                <div class="form-group mx-sm-3 mb-2">
                    <label for="email"> User email : </label>
                    <input type="email" name="userEmail" id="email"
                           value="${userCorrectEmail != null ? userCorrectEmail : ''}"
                           class="form-control <jstl:out value="${userEmailError != null ? 'is-invalid' : ' '}"/>"
                           placeholder="user@email.net" required />
                    <jstl:if test="${userEmailError != null}">
                        <div class="invalid-feedback">
                                ${userEmailError}
                        </div>
                    </jstl:if>
                </div>
            </div>
            <div class="form-group row">
                <div class="form-group mx-sm-3 mb-2">
                    <label for="pass">Password: </label>
                    <input type="password" name="password" id="pass"
                           class="form-control <jstl:out value="${passwordError != null ? 'is-invalid' : ' '}"/>"
                           placeholder="password" required />
                    <jstl:if test="${passwordError != null}">
                        <div class="invalid-feedback">
                                ${passwordError}
                        </div>
                    </jstl:if>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-light" type="submit">Sign In</button>
        </form>
    </div>
</div>
</body>
</html>