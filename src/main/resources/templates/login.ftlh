<#import "/spring.ftl" as spring />

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<br>
<br>
<br>
<div class="container">
    <div class="jumbotron">
        <h3 class="text-center">Добро пожаловать</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/login" method="post">
                <div class="form-group">
                    <label for="exampleInputEmail">Email address</label>
                    <@spring.formInput "form.email" "class=\"form-control\" id=\"exampleInputEmail\" placeholder=\"Введите email\"" "email"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="form-group">
                    <label for="exampleInputPassword">Password</label>
                    <@spring.formInput "form.password" "class=\"form-control\" id=\"exampleInputPassword\" placeholder=\"Ввелите пароль\"" "passwprd"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <button type="submit" class="btn btn-success">Войти</button>
                <a class="btn btn-dark" href="/addUser" role="button">Регистрация</a>
                <div>
                </div>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>
</div>
    </div>
<script src="/static/js/bootstrap.min.js"></script>
</body>
</html>