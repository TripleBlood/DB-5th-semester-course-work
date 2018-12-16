<body>
<table>
    <tr>
        <th>ID</th>
        <th>Логин</th>
        <th>Имя</th>
    </tr>
<#list model.users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.name}</td>
    </tr>
</#list>
</table>
</body>