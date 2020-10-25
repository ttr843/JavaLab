<#macro content>
</#macro>

<#macro title></#macro>

<#macro main>
    <!doctype html>
    <html lang="en">
    <head>
        <@head/>
    </head>
    <body>
    <nav class="sticky-top navbar navbar-dark navbar-expand bg-dark">
        <a class="navbar-brand" href="/news">FakeInstagram</a>
        <div class="collapse navbar-collapse" id="navbarText">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" href="/news">News</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/favorites">Favorites</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/subscriptions">Subscriptions</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/addPost">Add</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/chat">Chat</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0" action="/search" method="post" >
                <input class="form-control mr-sm-2" name="keyWord" type="search" placeholder="input key word" aria-label="Search">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
            </form>
            <div class="dropdown ml-4">
                <a class="dropdown-toggle profile-dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    ${user.getName() + " " + user.getSurname()}
                </a>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenuLink">
                    <a class="dropdown-item" href="/profile">Profile</a>
                    <a class="dropdown-item" href="/editProfile">Edit profile</a>
                    <a class="dropdown-item" href="/logout">logout</a>
                </div>
            </div>
        </div>
    </nav>
    <@content/>
    </body>
    </html>
</#macro>