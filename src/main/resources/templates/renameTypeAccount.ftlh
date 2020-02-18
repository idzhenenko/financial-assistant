<#import "/spring.ftl" as spring />

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
        <h3 class="text-center">Вы успешно переименновали транзакцию!</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/renameTypeAccount" method="post">
                <h1 class="display-4">${name}- (${id})!</h1>
                <a class="btn btn-dark" href="/login" role="button">Вернуться братно</a>
                <#--<button type="submit" href="/addNewTypeAccount" class="btn btn-success">Создать тип транзакции</button>-->
                <#--<a class="btn btn-outline-success" type="submit" href="/viewAccount" role="button">Создать счет</a>-->
            </form>
        </div>
        </div>
        <div class="col-sm"></div>
    </div>
   </div>
</div>
<script src="js/bootstrap.min.js"/>
</body>
</html>
