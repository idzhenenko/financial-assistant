<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
<#if form??>
    <h1>Удален тип транзакции: </h1>
    <p>${name}</p>
    <a class="btn btn-dark" href="/personal-area" role="button">Вернуться обратно</a>
<#else>
    <h1>Список счетов пуст.</h1>
</#if>
</body>
</html>

