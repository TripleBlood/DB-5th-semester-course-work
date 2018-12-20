<#ftl encoding='UTF-8'>
<head>
    <head>
        <link href="/css/bootstrap.main.css" rel="stylesheet"/>
    </head>
</head>
<body>

<h1>Добро пожаловать в магический магазинчик!</h1>

<#if error??>
<br>
<div>${error}</div>
</#if>

<br>
<div class="content-block">
    <form class="form-horizontal" action="/sign_up" method="post">
        <input name="name" placeholder="Имя">
        <p><input name="status" placeholder="Статус в обществе">
        <p><input name="login" placeholder="Логин">
        <p><input name="pass" placeholder="Пароль">
        <p><input name="magic_index" placeholder="Магичекий индекс (точно не номер телефона)">
        <p><input type="submit">
    </form>
</div>

<form action="/">
    <input type="submit" value="Go to to main page" />
</form>
</body>