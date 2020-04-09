<#include "base.ftlh"/>
<#macro head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
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
    <title>Post ${post.getId()}</title>
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
    <style>
        a.profile-dropdown-toggle {
            color: white;
        }

        .container-post {
            margin-top: 30px;
            margin-bottom: 30px;
        }

        .card-post {
            padding: 16px;
        }

        .btn-close-post {
            display: inline-block;
            height: 40px;
            width: 40px;
        }

        .card-like-button-wrap {
            display: flex;
            justify-content: flex-end;
        }

        .card-like-button {
            margin-left: 16px;
            padding: 0;
            width: 50px;
            background: transparent;
            border: 0;
        }

        .card-like-icon {
            max-width: 32px;
            height: auto;
        }

        .media-author {
            padding: 16px 0;
        }

        .media-author-photo {
            max-width: 130px;
        }

        .comment-date {
            color: #6c757d;
        }
    </style>
</#macro>
<#macro content>
    <div class="container container-post">
        <div class="row justify-content-center">
            <div class="col-10">
                <div class="card card-post">
                    <div class="media media-author">
                        <img src="${user.getPhotoPath()}"  class="mr-3 img-fluid media-author-photo" alt="User">
                        <div class="media-body">
                            <h5 class="mt-0">${user.getName()}</h5>
                            ${user.getEmail()}
                        </div>
                        <a href="/profile" class="btn-close-post">
                            <img src="/img/close.svg" alt="Close">
                        </a>
                    </div>
                    <img src="${post.getPhoto()}" class="card-img-top" alt="POST">
                    <div class="card-body">
                        <p class="card-text">
                           <h5>${post.getPlace()}</h5>
                        </p>
                    </div>
                    <div class="card-body">
                        <p class="card-text">
                          ${post.getText()}
                        </p>
                    </div>
                    <div class="card-like-button-wrap">
                        <button class="card-like-button">
                            <img src="../img/star.svg" alt="FAVOURITES" class="card-like-icon">
                        </button>
                        <button class="card-like-button">
                            <img src="../img/heart.svg" alt="LIKE" class="card-like-icon">
                        </button>
                    </div>
                    <div class="card-body">
                        <ul class="list-unstyled">
                            <li class="media">
                                <div class="media-body">
                                    <h6 class="mt-0 mb-1">NAME</h6>
                                    Comment
                                    <p class="comment-date">
                                        <small>DATE</small>
                                    </p>
                                </div>
                            </li>
                        </ul>
                        <form>
                            <div class="form-group">
                                <label for="comment">COMMENT</label>
                                <textarea class="form-control" id="comment" rows="3"></textarea>
                            </div>
                            <button type="submit" class="btn btn-dark float-right">PUBLISH</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#macro>
<@main/>
