let formFilter = $("#filter");
const mealsAjaxUrl = "ajax/profile/meals/";

function filter() {
    $.ajax({
        type: "GET",
        url: mealsAjaxUrl + "filter",
        data: formFilter.serialize()
    }).done(updateTable)
}
function cancelFilter(){
    formFilter[0].reset();
    $.get(mealsAjaxUrl, updateTable)
}
// $(document).ready(function () {
$(function () {
    makeEditable({
        ajaxUrl: mealsAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        }),
        updateTable: filter
    });
});