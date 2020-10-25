<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="http://localhost:8080/login" method="post">
    <div>
        <label for="login"><b>login</b></label>
        <input type="text" class="form-control" id="login" name="login">
        <label for="password"><b>Password</b></label>
        <input type="password" class="form-control" id="password" name="password">
        <button type="submit" class="btn btn-warning"><b>Sign in</b></button>
    </div>
</form>

</body>
</html>