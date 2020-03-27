<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up</title>
</head>
<body>
<h1>Registration</h1>
<div>
    <form action="/signUp" method="post">
        <b>Insert name</b>
        <input name="name" placeholder="Name">
        <p>
            <b>Insert mail</b>
            <input name="email" placeholder="Email">
        <p>
            <b>Insert password</b>
            <input type="password" name="password" placeholder="password">
        <p>
            <input type="submit" value="sign Up">
    </form>
</div>
</body>
</html>