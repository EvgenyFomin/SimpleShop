<html lang="en">
<head>
    <meta charset="UTF-8">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

    <script>
        $(function () {
            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");
            $(document).ajaxSend(function (e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });
    </script>
</head>
<body>
<button type="button" onclick="doAjax()">Submit</button>
<script>
    function doAjax() {
        $.ajax({
            url: "/testAjax",
            type: "POST",
            data: ({data1: "someData"}),
            success: function (data) {
                alert("Hello " + data);
            }
        });
    }
</script>
</body>
</html>