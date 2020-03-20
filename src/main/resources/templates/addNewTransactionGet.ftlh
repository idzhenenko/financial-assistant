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
        <h3 class="text-center">Создание транзакции!</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/insert-new-transaction" method="post">
                <div class="form-group">
                    <label for="name">Имя транзакции</label>
                    <@spring.formInput "form.sourceAccount" "class=\"form-control\" id=\"sourceAccount\" placeholder=\"Введите счет списания\"" "text"/>
                    <@spring.showErrors "<br>"/>
                    <br>
                    <@spring.formInput "form.targetAccount" "class=\"form-control\" id=\"targetAccount\" placeholder=\"Введите счет зачисления\"" "text"/>
                    <@spring.showErrors "<br>"/>
                    <br>
                    <@spring.formInput "form.typeTransaction" "class=\"form-control\" id=\"typeTransaction\" placeholder=\"Введите id транзакции\"" "text"/>
                    <@spring.showErrors "<br>"/>
                    <br>
                    <@spring.formInput "form.idCategory" "class=\"form-control\" id=\"idCategory\" placeholder=\"Введите id категории\"" "text"/>
                    <@spring.showErrors "<br>"/>
                    <br>
                    <@spring.formInput "form.amount" "class=\"form-control\" id=\"amount\" placeholder=\"Введите сумму\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <button type="submit" class="btn btn-success">Создать транзакцию</button>
                <a class="btn btn-dark" href="/personal-area" role="button">Вернуться братно</a>
            </form>
        </div>
        <div class="col-sm"></div>
    </div>
   </div>
</div>
<script src="js/bootstrap.min.js"/>
</body>
</html>
