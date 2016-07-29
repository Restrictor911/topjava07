function makeEditable() {
    $('#add').click(function () {
        $('#id').val(0);
        $('#editRow').modal();
    });

    $('.delete').click(function () {
        deleteRow($(this).parent().parent().attr("id"));
    });

    $('#detailsForm').submit(function () {
        save();
        return false;
    });

    $('#filterForm').submit(function () {
        filter();
        return false;
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            updateTable();
            successNoty('Deleted');
        }
    });
}

function enable(checkbox) {
    var enabled = checkbox.is(":checked");
    $.post({
        url: ajaxUrl + checkbox.parent().parent().attr("id"),
        data: { enabled: enabled },
        success: function () {
            successNoty(enabled ? "Enabled" : "Disabled");
        }
    });
}

function updateTable() {
    $.get(ajaxUrl, function (data) {
        datatableApi.fnClearTable();
        $.each(data, function (key, item) {
            datatableApi.fnAddData(item);
        });
        datatableApi.fnDraw();
    });
}

function save() {
    var form = $('#detailsForm');
    debugger;
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            updateTable();
            successNoty('Saved');
        }
    });
}

function filter() {
    var form = $('#filterForm');
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        data: form.serialize(),
        success: function (data) {
            datatableApi.fnClearTable();
            $.each(data, function (key, item) {
                datatableApi.fnAddData(item);
            });
            datatableApi.fnDraw();
            successNoty('Filtered');
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: text,
        type: 'success',
        layout: 'bottomRight',
        timeout: true
    });
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: 'Failed: ' + jqXHR.statusText + "<br>",
        type: 'error',
        layout: 'bottomRight'
    });
}
