$(function () {
    $("#startDate").datetimepicker({
        timepicker: false,
        format: "Y-m-d"
    });

    $("#endDate").datetimepicker({
        timepicker: false,
        format: "Y-m-d"
    });

    $("#startTime").datetimepicker({
        datepicker: false,
        format: "H:i"
    });

    $("#endTime").datetimepicker({
        datepicker: false,
        format: "H:i"
    });

    $("#dateTime").datetimepicker({
        validateOnBlur : true,
        format: "Y-m-d H:i"
    });
    
    
});