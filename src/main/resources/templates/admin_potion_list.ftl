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

<h1>Список зельев!</h1>
<br>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Cost</th>
        <th>Available</th>
        <th>Volume</th>
        <th>Ingredient 1</th>
        <th>Ingredient 2</th>
        <th>Ingredient 3</th>
    </tr>
<#list model.potions as potion>
    <tr>
        <td>${potion.name}</td>
        <td>${potion.description}</td>
        <td>${potion.cost}</td>
        <td>${potion.available}</td>
        <td>${potion.volume}</td>
        <td>${potion.ingredients[0].name}</td>
        <td>${potion.ingredients[1].name}</td>
        <td>${potion.ingredients[2].name}</td>
    </tr>
</#list>
</table>
</body>

