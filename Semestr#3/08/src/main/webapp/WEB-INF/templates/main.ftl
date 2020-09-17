<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Main</title>
</head>
<body>
    <#if User??>
        <h1>Welcome, ${User.getLogin()}!</h1>
    </#if>
    <a href="http://localhost:8080/products">
        <button>
            See products!
        </button>
    </a>
</body>
</html>