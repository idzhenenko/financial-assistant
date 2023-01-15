<#--<#import "/spring.ftl" as spring />-->

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <link href="css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<br>
<br>
<br>
<div class="container">
    <div class="jumbotron">
        <h3 class="text-center">Новое имя транзакции успешно создано!</h3>
        <br>
        <br>
        <div class="row">
            <div class="text-center">
                <form action="/add-type-account" method="post">
                    <h1 class="text-center">${name}- (${id})!</h1>
                    <a class="btn btn-dark" href="/personal-area" role="button">Вернуться обратно</a>
                </form>
            </div>
        </div>
        <div class="col-sm"></div>
    </div>
</div>
<script src="js/bootstrap.min.js"/>
</body>
</html>
