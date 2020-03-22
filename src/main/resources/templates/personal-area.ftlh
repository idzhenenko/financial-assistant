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
        <h1 class="display-4">Привет, ${name}!</h1>
        <h5 class="display-6">твой id: ${id}</h5>
        <p class="lead">Начни пользоваться прямо сейчас!</p>
        <hr class="my-4">
        <p>Простое и удобное приложение для отслеживания своих расходов</p>
        <form>
            <a class="btn btn-outline-success" href="/view-account" role="button">Показать счета</a>
            <button type="button" class="btn btn-outline-success">Отчёт</button>
            <#--<button type="button" class="btn btn-outline-success">Показать отчёт по категориям</button>-->
            <a class="btn btn-outline-success" href="/insert-new-transaction" role="button">Создать новую транзакцию</a>
            <a class="btn btn-outline-success" href="/add-type-account" role="button">Добавить тип транзакцию</a>
            <a class="btn btn-outline-success" href="/rename-type-account" role="button">Переименовать тип транзакцию</a>
            <hr class="my-4">
            <a class="btn btn-outline-success" href="/delete-type-account" role="button">Удалить тип транзакцию</a>
            <a class="btn btn-outline-success" href="/view-type-account" role="button">Показать типы транзакций</a>
        </form>
    </div>
    <div class="row">
        <a href="/logout">Logout</a>
    </div>
</div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>