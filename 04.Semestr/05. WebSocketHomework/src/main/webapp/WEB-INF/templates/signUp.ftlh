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
<h1>Sign Up</h1>
<form action="/sign-up"  method="post">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" placeholder="Name">
        <label for="email">Email</label>
        <input type="text" id="email" name="email" placeholder="Email">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Password" >
    <input type="submit" id="send" value="Sign Up" >
</form>
</body>
</html>