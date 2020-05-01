<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>Sign Up</title>
    <style>
        html {
            height: 100%;
        }

        body {
            height: 100%;
            display: flex;
            align-items: center;
        }

        .registration-row {
            align-items: center;
        }

        .registration-img {
            max-width: 300px;
        }

        .btns-wrapper {
            display: flex;
            justify-content: space-between;
        }
        .error{
            color: #ff0000;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="row registration-row">
        <div class="col">
            <img src="/img/masks.svg" alt="Masks" class="registration-img img-fluid">
        </div>
        <div class="col">
            <h2>Registration</h2>
            <form action="/signUp" method="POST" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="name">name</label>
                    <input type="text" id="name" name="name" placeholder="write name..."  class="form-control" >
                </div>
                <div class="form-group">
                    <label for="surname">surname</label>
                    <input type="text"  id="surname" name="surname" placeholder="write surname..." class="form-control" >
                </div>
                <div class="form-group">
                    <label for="email">email</label>
                    <input type="text" id="email" name="email" placeholder="write email..." class="form-control">
                </div>
                <div class="form-group">
                    <label for="password">password</label>
                    <input type="password" id="password" name="password" placeholder="write password..." class="form-control">
                </div>
                <div class="form-group">
                    <label for="password-repeat">repeat password</label>
                    <input type="password" class="form-control" id="password-repeat">
                </div>
                <div class="form-group">
                    <label for="photo">photo</label>
                    <input type="file" class="form-control-file" id="photo" name="file">
                </div>
                <div class="btns-wrapper">
                    <button type="submit" class="btn btn-dark">sign up</button>
                    <a class="text-secondary" href="/login">Already have an account?sign in</a>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
