<#import "/spring.ftl" as spring />

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <br>
    <div class="jumbotron">
        <h3 class="text-center">Какой тип транзакции Вы хотите удалить?</h3>
        <br>
        <br>
        <div class="row">
            <div class="col-sm"></div>
            <div class="col-sm">
                <form action="/delete-type-account" method="post">
                    <div class="form-group">
                        <label for="id">Id типа транзакции: </label>
                        <@spring.formInput "form.id" "class=\"form-control\" id=\"id\" placeholder=\"Введите Id типа транзакции:\"" "text"/>
                        <@spring.showErrors "<br>"/>
                        <button type="submit" class="btn btn-success">Удалить</button>
                        <a class="btn btn-dark" href="/personal-area" role="button">Вернуться братно</a>
                    </div>
                </form>
                </div>
            </div>
        </div>
    </div>
<script src="/js/bootstrap.min.js"></script>
</body>
</html>