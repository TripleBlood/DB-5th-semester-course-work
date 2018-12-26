<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>

<head>
    <head>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/menu.css'/>"/>
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

<h1>Новое зелье!</h1>

<#if error??>
<br>
<div>${error}</div>
</#if>
<br>
<div class="content-block">
    <form class="form-horizontal" action="/admin/newPotion" method="post">
        <input name="name" placeholder="Имя">
        <p><textarea name="description" rows="10" cols="30">Введите описание продукта</textarea>
        <p><input name="available" placeholder="сколько?">
        <p><input name="cost" placeholder="Стоимость">
        <p><input name="volume" placeholder="Объем">
        <p><input name="catalystIng" placeholder="Катализатор" list="catalystIng"></p>
        <datalist id="catalystIng"><#list model.catalystIngs as catIng>
            <option value="${catIng.name}"></option>
        </#list></datalist>
        <p><input name="essenceIng" placeholder="Эссенция" list="essenceIng"></p>
        <datalist id="essenceIng"><#list model.essenceIngs as catIng>
            <option value="${catIng.name}"></option>
        </#list></datalist>
        <p><input name="conservatorIng" placeholder="Хранитель" list="conservatorIng"></p>
        <datalist id="conservatorIng"><#list model.conservatorIngs as catIng>
            <option value="${catIng.name}"></option>
        </#list></datalist>
        <p><input type="submit">
    </form>
</div>
</body>

