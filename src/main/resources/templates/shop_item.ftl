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
        <li><a href="/">Домой</a></li>
        <li><a href='/user/shop'>Магазин</a></li>
        <li><a href='/user/shop/cart'>Корзина</a></li>
        <li><a href='/logout'>Выход</a></li>
    </ul>
</div>
<#if error??>
<br>
<div>${error}</div>
</#if>
<div class="shop-container">
    <h2>${model.product.name}</h2>
    <a>${model.product.description}</a><br>
    <a>Стоимость: ${model.product.cost}</a><br>
    <a>В наличии: ${model.product.available}</a><br>


    <form class="form-horizontal" action="/user/shop/${model.product.id}" method="post">
        <p><input name="quantity" placeholder="Количество заказа">
        <p><input type="submit" title="Заказать">
    </form>
</div>
</body>