<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
</head>
<body>
<h1>Sign In</h1>
<form action="/sign-in" method="post">
    <label for="email">Email</label>
    <input type="text" id="email" name="email" placeholder="Email">
    <label for="password">Password</label>
    <input type="password" id="password" name="password" placeholder="Password">
    <input type="submit" id="send" value="Sign In">
</form>
</body>
</html>