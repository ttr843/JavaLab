<!doctype html>
<#import "spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
            <h1><@spring.message 'signUp.page.mainWord'/></h1>
            <@spring.bind "profileForm"/>
            <form action="/signUp" method="POST" >
                <div class="form-group">
                    <label for="name">
                        <@spring.message 'signUp.page.name'/></label>
                    <br>
                    <@spring.formInput "profileForm.name"/>
                    <@spring.showErrors "   <br>"/>
                    <br><br>
                </div>
                <div class="form-group">
                    <label for="surname"><@spring.message 'signUp.page.surname'/></label>
                    <br>
                    <@spring.formInput "profileForm.surname"/>
                    <@spring.showErrors "<br>"/>
                    <br><br>
                </div>
                <div class="form-group">
                    <label for="email"><@spring.message 'signUp.page.email'/></label>
                    <br>
                    <@spring.formInput "profileForm.email"/>
                    <@spring.showErrors "<br>","error"/>
                    <br><br>
                </div>
                <div class="form-group">
                    <label for="password"><@spring.message 'signUp.page.password'/></label>
                    <br>
                    <@spring.formInput "profileForm.password"/>
                    <@spring.showErrors "<br>","error"/>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="btns-wrapper">
                    <button type="submit" class="btn btn-dark"><@spring.message 'signUp.page.buttonSubmit'/></button>
                    <a class="text-secondary" href="/login"><@spring.message 'signUp.page.linkToLogin'/></a>
                </div>
            </form>
            <div>
                <br>
                <h6><@spring.message 'signUp.page.changeLanguage'/></h6>
                <a href="/signUp?lang=ru"><@spring.message 'signUp.page.langRus'/></a>
                <br>
                <a href="/signUp?lang=en"><@spring.message 'signUp.page.langEng'/></a>
            </div>
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
