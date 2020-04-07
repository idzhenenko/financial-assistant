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
        <h3 class="text-center">Отчет</h3>
        <br>
        <br>
    <div class="row">
        <div class="col-sm"></div>
        <div class="col-sm">
            <form action="/report-account-category" method="post">
                <#--<h1 class="display-4">${report})!</h1>-->
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
