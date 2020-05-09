<#include "base.ftlh"/>
<#macro head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>Subscriptions</title>
    <style>
        a.profile-dropdown-toggle {
            color: white;
        }

        .user-profile-row {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .user-profile-col {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .user-profile-photo {
            max-width: 300px;
        }

        .card-post {
            padding: 16px;
        }

        .card-like-button-wrap {
            display: flex;
            justify-content: flex-end;
        }

        .card-like-button {
            margin-left: 12px;
            padding: 0;
            width: 30px;
            background: transparent;
            border: 0;
        }

        .card-like-icon {
            max-width: 24px;
            height: auto;
        }

        .card-text {
            color: black;
            font-size: 14px;
        }

        .card a:hover {
            text-decoration: none;
        }
    </style>
</#macro>
<#macro content>
    <div class="container container-post">
        <div class="row">
            <div class="col">
                <h2 class="mb-4">Subscriptions</h2>
            </div>
        </div>
        <#list subs as sub>
            <div class="row justify-content-center">
                <div class="col">
                    <div class="card card-post">
                        <div class="media media-author">
                            <img src="${sub.getPhotoPath()}"
                                 class="mr-3 img-fluid media-author-photo" alt="UserPhoto" width="300" height="300">
                        </div>
                        <p></p>
                        <div class="media-body media-author-body">
                            <h6 class="mt-0">${sub.getName() + " " + sub.getSurname()}</h6>
                        </div>
                        <p></p>
                        <form action="/deleteSub" method="post">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <input type="hidden" value="${sub.getId()}" name="subId">
                            <button type="submit" class="btn btn-outline-dark mb-2">Unsubscribe</button>
                        </form>
                    </div>
                </div>
            </div>
        </#list>
    </div>
</#macro>
<@main/>
