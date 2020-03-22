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
        <h3 class="text-center">Регистрация нового пользователя!</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/add-user" method="post">
                <div class="form-group">
                    <label for="firstName">Имя</label>
                    <@spring.formInput "form.firstName" "class=\"form-control\" id=\"firstName\" placeholder=\"Введите Ваше имя\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="form-group">
                    <label for="lastName">Фамилия</label>
                    <@spring.formInput "form.lastName" "class=\"form-control\" id=\"lastName\" placeholder=\"Введите Вашу амилию\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="form-group">
                    <label for="phone">Телефон</label>
                    <@spring.formInput "form.phone" "class=\"form-control\" id=\"phone\" placeholder=\"Номер телефона\"" "phone"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="form-group">
                    <label for="Email">Email</label>
                    <@spring.formInput "form.email" "class=\"form-control\" id=\"email\" placeholder=\"Ваш Email\"" "email"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <@spring.formInput "form.password" "class=\"form-control\" id=\"password\" placeholder=\"Придумайте пароль\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <button type="submit" class="btn btn-success">Зарегистрироваться</button>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>
   </div>
</div>
<script src="js/bootstrap.min.js"/>
</body>
</html>
