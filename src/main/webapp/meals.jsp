<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
    <title>Meal list</title>
    <%@include file="template/head.jsp" %>
    <body style="background-color: #4e555b">
<%@include file="template/navbar.jsp" %>
<div class="container mt-5">
    <form name="filter" method="get">
    <div class="form-row">
        <div class="col-auto">
            <input class="form-control" type="time" name="startTime" value="${param.startTime}" placeholder="start time" />
            <input class="form-control" type="time" name="endTime" value="${param.endTime}" placeholder="end time" />
        </div>
        <div class="col-auto">
            <input class="form-control" type="date" name="startDate" value="${param.startDate}" placeholder="start date" />
            <input class="form-control" type="date" name="endDate" value="${param.endDate}" placeholder="end date" />
        </div>
        <div class="col-auto">
        <button class="btn btn-light" type="submit">фильтр</button>
        </div>
    </div>
    </form>
    <div class="sect">
        <table class="table mt-3">
            <thead>
            <tr>
                <th scope="col">Date</th>
                <th scope="col">Description</th>
                <th scope="col">Calories</th>
            </tr>
            </thead>
            <tbody>
            <jsp:useBean id="meals" scope="request" type="java.util.List"/>
            <jstl:forEach items="${meals}" var="meal">
                <jsp:useBean id="meal" type="ru.javawebinar.topjava.to.MealWithExceed"/>
                <%@include file="mealForm.jsp"%>
                <tr class="box-shadow-hover <jstl:out value="${meal.exceed ? 'text-danger' : 'text-success'}"/>"
                    data-toggle="modal"
                    data-target="#exampleModal${meal.id}">
                    <td><javatime:format value="${meal.dateTime}" pattern="dd-MM-yyyy HH:mm"/></td>
                    <td>${meal.description}</td>
                    <td>${meal.calories}</td>
                    <td>
                        <button class="btn btn-outline-danger"
                                data-id="${meal.id}"
                                form="delete${meal.id}">&times;</button>
                    </td>
                </tr>
                <form action="meals" id="delete${meal.id}" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="${meal.id}">
                </form>
            </jstl:forEach>
            </tbody>
        </table>
        <button class="btn btn-info" data-toggle="modal"  data-target="#exampleModal">Добавить</button>
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
             aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Добавление в таблицу:</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="meals" id="create" method="post">
                            <input type="hidden" name="action" value="create"/>
                            <div class="form-group">
                                <input class="form-control" type="text" name="description" placeholder="Описание" required/>
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="number" name="calories"
                                       placeholder="Колличество каллорий" required />
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="datetime-local" name="dateTime" />
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                        </button>
                        <button type="submit" form="create" class="btn btn-success">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>