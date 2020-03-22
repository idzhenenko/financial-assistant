<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <br>
    <div class="jumbotron">
        <p class="lead">Ваши типы транзакций!</p>
        <#list accountsType as a>
        <h1 class="display-4">${a.id}-${a.name}!</h1>
        </#list>
        <hr class="my-4">
        <a class="btn btn-dark" href="/login-form" role="button">Начать пользоваться</a>
    </div>
</div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>