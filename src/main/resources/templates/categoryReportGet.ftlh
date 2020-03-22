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
        <h3 class="text-center">Отчет!</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/report-account-category" method="post">
                <div class="form-group">
                    <label for="name">Отчет за временной промежуток</label>
                    <@spring.formInput "form.startDay" "class=\"form-control\" id=\"startDay\" placeholder=\"Введите первую дату\"" "text"/>
                    <@spring.showErrors "<br>"/>
                    <br>
                    <@spring.formInput "form.endDay" "class=\"form-control\" id=\"endDay\" placeholder=\"Введите вторую дату\"" "text"/>
                    <@spring.showErrors "<br>"/>
                </div>
                <button type="submit" class="btn btn-success">Посмотреть</button>
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
