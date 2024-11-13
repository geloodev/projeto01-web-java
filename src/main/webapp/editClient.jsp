<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <title>Editar Cliente</title>
</head>

<body>
    <h1>Editar Cliente</h1>

    <form action="clientes" method="POST">
        <input type="hidden" name="_method" value="PUT" />
        <input type="hidden" name="id" value="${client.getId()}" />

        <label for="name">Nome:</label>
        <input type="text" id="name" name="name" value="${client.getName()}" required>

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" value="${client.getEmail()}" required>
        
        <label for="phone">Telefone:</label>
        <input type="text" id="phone" name="phone" value="${client.getPhone()}" required>

        <input type="submit" value="Salvar">
    </form>
</body>

</html>
