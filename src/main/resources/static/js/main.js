
$('document').ready(function() {
    $('#ctable .btn').on('click', function(event) {
        event.preventDefault();
        $('#credUpModal').modal();
    });
});