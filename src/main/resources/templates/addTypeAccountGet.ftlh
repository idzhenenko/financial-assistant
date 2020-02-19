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
        <h3 class="text-center">Создание типа транзакции!</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/addTypeAccount" method="post">
                <div class="form-group">
                    <label for="name">Имя транзакции</label>
                    <@spring.formInput "form.name" "class=\"form-control\" id=\"name\" placeholder=\"Введите имя\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <button type="submit" class="btn btn-success">Создать тип счета</button>
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
