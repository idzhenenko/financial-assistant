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
        <h3 class="text-center">Переименование типа транзакции!</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/renameTypeAccount" method="post">
                <div class="form-group">
                    <label for="id">Введите id транзакции:</label>
                    <@spring.formInput "form.id" "class=\"form-control\" id=\"id\" placeholder=\"Введите id\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="form-group">
                    <label for="name">Введите новое имя транзакции:</label>
                    <@spring.formInput "form.name" "class=\"form-control\" id=\"name\" placeholder=\"Введите новое имя\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <button type="submit" class="btn btn-success">Переименновать</button>
                <#--<a class="btn btn-dark" href="/addNewTypeAccount" role="button">Создать тип счет</a>-->
                <a class="btn btn-dark" href="/index" role="button">Вернуться братно</a>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>
   </div>
</div>
<script src="js/bootstrap.min.js"/>
</body>
</html>
