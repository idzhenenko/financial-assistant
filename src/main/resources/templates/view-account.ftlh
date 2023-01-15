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
        <h1 class="display-4">Доступные счета:</h1>
        <#list accounts as a>
        <h1 class="display-4">id счета: ${a.id}(${a.name}) - ${a.balance}!</h1>
        </#list>
        <hr class="my-4">
        <a class="btn btn-dark" href="/add-account" role="button">Создать счет</a>
        <a class="btn btn-danger" href="/delete-account" role="button">Удалить счет</a>
        <a class="btn btn-light" href="/personal-area" role="button">Вернуться обратно</a>
    </div>
</div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>