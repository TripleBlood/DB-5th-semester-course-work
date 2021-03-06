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
                <li><a href='/admin/list'>Списки</a></li>
            </ul>
        </li>
        <li><a href='/admin/requests'>Заказы</a></li>
        <li><a href='/admin/savings'>Сбережения</a></li>
        <li><a href='/logout'>Выход</a></li>
    </ul>
</div>

<div class="shop-container">
<h1>Новый Ингридиент!</h1>

<#if error??>
<br>
<div>${error}</div>
</#if>
<br>

<div class="content-block">
    <form class="form-horizontal" action="/admin/newIngredient" method="post">
        <input name="name" placeholder="Имя">
        <p><textarea name="description" rows="10" cols="30">Введите описание продукта</textarea>
        <p><input name="available" placeholder="сколько?">
        <p><input name="cost" placeholder="Стоимость">
        <p><input name="ingredientType" placeholder="Тип ингридиента" list="ingTypeList"></p>
        <p><datalist id="ingTypeList">
            <option value="CATALYST">
            <option value="ESSENCE">
            <option value="CONSERVATOR">
        </datalist>
        <p><input type="submit">
    </form>
</div>
</div>
</body>