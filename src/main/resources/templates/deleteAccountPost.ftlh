<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<body>
<#if form??>
    <h1>Удален счет: </h1>
    <p>${name}</p>
<#else>
    <h1>Список счетов пуст.</h1>
</#if>
</body>
</html>

