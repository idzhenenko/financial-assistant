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
                        <input name="firstName" class="form-control" type="text" placeholder="Имя">
                    </div>
                    <div class="form-group">
                        <label for="lastName">Фамилия</label>
                        <input name="lastName" class="form-control" type="text" placeholder="Введите Вашу амилию">
                    </div>
                    <div class="form-group">
                        <label for="phone">Телефон</label>
                        <input name="phone" class="form-control" type="text" placeholder="Номер телефона">
                    </div>
                    <div class="form-group">
                        <label for="Email">Email</label>
                        <input name="email" class="form-control" type="text" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
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