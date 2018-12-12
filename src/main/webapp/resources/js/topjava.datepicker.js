$.datetimepicker.setLocale(i18n["locale"]);
const startDate = $('#startDate');
const endDate = $('#endDate');
const startTime = $('#startTime');
const endTime = $('#endTime');

$('#dateTime').datetimepicker({
    format:'Y-m-d H:i',
    formatTime:'H:i',
    formatDate:'Y-m-d'
});
startDate.datetimepicker({
    timepicker: false,
    format: 'Y-m-d',
    onShow: function (ct) {
        this.setOptions({
            maxDate: endDate.val() ? endDate.val() : false
        })
    }
});
endDate.datetimepicker({
    timepicker: false,
    format:'Y-m-d',
    onShow: function (ct) {
        this.setOptions({
            minDate: startDate.val() ? startDate.val() : false
        })
    }
});
startTime.datetimepicker({
    datepicker: false,
    format:'H:i',
    onShow: function (ct) {
        this.setOptions({
            maxTime: endTime.val() ? endDate.val() : false
        })
    }
});
endTime.datetimepicker({
    datepicker: false,
    format:'H:i',
    onShow: function (ct) {
        this.setOptions({
            minTime: startTime.val() ? startTime.val() : false
        })
    }
});
