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
<div class="shop-container">
<#list model.cartItems as product>
    <h2>${product.product.name}</h2><br>
    <a>В заказе: ${product.count}</a><br>
    <a>Стоимость за штуку: ${product.product.cost}</a><br>
    <a>Сумма: ${product.count * product.product.cost}</a><br>
    <div class="buttona"><a href="/user/shop/cart/abort/${product.product.id}">Убрать из заказа</a></div>
</#list><br>
    <h3>Сумма заказа составит: ${model.sum} золота.</h3>
    <div class="buttona"><a href="/user/shop/cart/confirm">Подтведить заказ</a></div>
</div>
</body>