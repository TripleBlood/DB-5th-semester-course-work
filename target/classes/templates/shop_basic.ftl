<#ftl encoding='UTF-8'>
<#import "/spring.ftl" as spring/>

<head>
    <head>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/menu.css'/>"/>
        <link rel="stylesheet" type="text/css" href="<@spring.url '/css/table.css'/>"/>
    </head>
</head>


<body>
<div class="shop-container">
<#list model.products as product>
    <h2>${product.name}</h2>
    <a>${product.description}</a><br>
    <a>Стоимость: ${product.cost}</a><br>
    <a>В наличии: ${product.available}</a><br>
    <div class="buttona"><a href="/user/shop/${product.id}">Подробнее</a></div>
</#list>
</div>
</body>