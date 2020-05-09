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
    <title>Search</title>
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
    <div class="container">
        <#if error??>
            <div class="row user-profile-row">
                <div class="col user-profile-col">
                    <h1>${error}</h1>
                </div>
            </div>
        <#elseif searchedUser??>
            <div class="row user-profile-row">
                <div class="col user-profile-col">
                    <img src="${searchedUser.getPhotoPath()}" alt="avatar"
                         class="img-fluid user-profile-photo mb-3">
                    <form action="/subscribe" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <input type="hidden" value="${searchedUser.getId()}" name="subId">
                        <button type="submit" class="btn btn-outline-dark mb-2">Subscribe</button>
                    </form>
                </div>
            </div>
            <div class="col">
                <div class="form-group">
                    <p>Name</p>
                    <p><b>${searchedUser.getName()}</b></p>
                </div>
                <div class="form-group">
                    <p>Surname</p>
                    <p><b>${searchedUser.getSurname()}</b></p>
                </div>
                <div class="form-group">
                    <p>Email</p>
                    <p><b>${searchedUser.getEmail()}</b></p>
                </div>
                <div class="form-group">
                    <p>Role</p>
                    <p><b>${searchedUser.getRole().toString()}</b></p>
                </div>
            </div>
        <#elseif posts??>
            <div class="row justify-content-center">
                <#list posts as post>
                    <div class="col">
                        <div class="card card-post">
                            <a href="/post/${post.getId()}">
                                <img src="${post.getPhoto()}" alt="Image ${post.getId()}" width="300"
                                     height="350">
                                <div class="card-body">
                                    <p><b>${post.getPlace()}</b></p>
                                    <p class="card-text">${post.getText()}
                                    </p>
                                    <p><b>${post.getDate()}</b></p>
                                </div>
                            </a>
                            <div class="card-like-button-wrap">
                                <button class="card-like-button">
                                    <img src="../img/heart.svg" alt="like" class="card-like-icon">
                                    ${post.getLikeOfPost()}
                                </button>
                            </div>
                        </div>
                    </div>
                    <p></p>
                </#list>
            </div>
        </#if>
    </div>
</#macro>
<@main/>