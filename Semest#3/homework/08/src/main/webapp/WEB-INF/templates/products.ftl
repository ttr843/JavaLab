<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Products</title>
</head>
<body>
<#if products??>
<#list products as product>
    <div>
        <p>name: ${product.getName()}</p>
        <p>description: ${product.getDescription()}</p>
        <p>price: ${product.getPrice()}</p>
    </div>
    <p></p>
</#list>
</#if>
<a href="/login">
    <button>
        Logout
    </button>
</a>
</body>
</html>