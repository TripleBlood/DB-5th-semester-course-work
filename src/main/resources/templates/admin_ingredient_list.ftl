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
    <h1>Список ингридиентов!</h1>
    <br>
    <table>
        <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Cost</th>
            <th>Available</th>
            <th>Type</th>
        </tr>
<#list model.ingredients as ingredient>
    <tr>
        <td>${ingredient.name}</td>
        <td>${ingredient.description}</td>
        <td>${ingredient.cost}</td>
        <td>${ingredient.available}</td>
        <td>${ingredient.ingredientType}</td>
    </tr>
</#list>
    </table>
</body>

