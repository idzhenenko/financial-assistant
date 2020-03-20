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
        <h3 class="text-center">Операции со счетами:</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/add-account" method="post">
                <div class="form-group">
                <label for="name">Имя счета</label>
                <@spring.formInput "form.name" "class=\"form-control\" id=\"name\" placeholder=\"Введите имя счета\"" "text"/>
                <@spring.showErrors "<br>"/>
                </div>
                <div class="form-group">
                <label for="name">Баланс</label>
                <@spring.formInput "form.balance" "class=\"form-control\" id=\"balance\" placeholder=\"Введите сумму\"" "text"/>
                <@spring.showErrors "<br>"/>
                </div>
                <button type="submit" class="btn btn-success">Создать счет</button>
                <#--<a class="btn btn-dark" href="/view-account" role="button">Создать счет</a>-->
                <a class="btn btn-dark" href="/login-form" role="button">Вернуться братно</a>
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
