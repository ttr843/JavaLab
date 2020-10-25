<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <script>
        function sendFile() {
            // данные для отправки
            let formData = new FormData();
            var mail = document.getElementById("mail");
            // забрал файл из input
            let files = ($('#upload'))[0]['files'];
            // добавляю файл в formData
            [].forEach.call(files, function (upload, i, files) {
                formData.append("upload", upload);
            });
            $.ajax({
                type: "POST",
                url: "/files",
                data: formData, mail,
                processData: false,
                contentType: false
            })
                .done(function (response) {
                    alert(response)
                })
                .fail(function () {
                    alert('Error')
                });
        }
    </script>
    <title>files</title>
</head>
<body>
<div>
    <form method="POST" action="/files" enctype="multipart/form-data">
        <h4>write your email</h4>
        <p></p>
        <input type="text" id="mail" name="mail" placeholder="write email..">
        <p></p>
        <input type="file" name="file"/><br/><br/>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>