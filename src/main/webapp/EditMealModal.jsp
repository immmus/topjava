<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<!-- Modal -->
<div class="modal fade" id="exampleModal${meal.id}" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="meals" id="edit${meal.id}" method="post">
                    <input type="hidden" name="action" value="edit"/>
                    <input type="hidden" name="id" value="${meal.id}"/>
                    <div class="form-group">
                        <input class="form-control" type="text" name="description" value="${meal.description}" required/>
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="number" name="calories"
                               value="${meal.calories}" required />
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="datetime-local" name="dateTime"
                               value="${meal.dateTime}"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close
                </button>
                <button type="submit" form="edit${meal.id}" class="btn btn-success">Save changes</button>
            </div>
        </div>
    </div>
</div>
</html>
