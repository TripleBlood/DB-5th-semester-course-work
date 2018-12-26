<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>

<head>
    <head>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/menu.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/table.css'/>"/>
    </head>
</head>

<body>

<div id='cssmenu'>
    <ul>
        <li><a href="/admin">Домой</a></li>
        <li><a>Продукты</a>
            <ul>
                <li><a>Артефакт</a>
                    <ul>
                        <li><a href='/admin/listArtefact'>Просмотреть список артефактов</a></li>
                        <li><a href='/admin/newArtefact'>Создать новый артефакт</a></li>
                    </ul>
                </li>
                <li><a>Ингридиент</a>
                    <ul>
                        <li><a href='/admin/listIngredient'>Просмотреть список ингридиенов</a></li>
                        <li><a href='/admin/newIngredient'>Создать новый ингридиент</a></li>
                    </ul>
                </li>
                <li><a>Зелье</a>
                    <ul>
                        <li><a href='/admin/listPotion'>Просмотреть список зельев</a></li>
                        <li><a href='/admin/newPotion'>Создать нове зелье</a></li>
                    </ul>
                </li>
                <li><a href='/admin/newPotion'>Списки</a></li>
            </ul>
        </li>
        <li><a href='#'>Заказы</a></li>
        <li><a href='#'>Поставки</a></li>
        <li><a href='/logout'>Выход</a></li>
    </ul>
</div>

<h1>Добро пожаловать в магический магазинчик!</h1>

<#if error??>
<br>
<div>${error}</div>
</#if>

<br>
<div class="content-block">
    <form class="form-horizontal" action="/signUp" method="post">
        <input name="name" placeholder="Имя">
        <p><input name="status" placeholder="Статус в обществе">
        <p><input name="login" placeholder="Логин">
        <p><input name="password" placeholder="Пароль">
        <p><input name="magic_index" placeholder="Магичекий индекс (точно не номер телефона)">
        <p><input type="submit">
    </form>
</div>

<form action="/login">
    <input type="submit" value="Go to to main page" />
</form>
</body>