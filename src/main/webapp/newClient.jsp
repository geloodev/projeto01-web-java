<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <title>Novo Cliente</title>
</head>

<body>
    <h1>Novo Cliente</h1>
    <form action="clientes" method="POST">
        <label for="name">Nome:</label><br>
        <input type="text" id="name" name="name" required><br><br>
        <label for="email">Email:</label><br>
        <input type="text" id="email" name="email" required><br><br>
        <label for="phone">Phone:</label><br>
        <input type="text" id="phone" name="phone" required><br><br>

        <input type="submit" value="Novo Cliente">
    </form>
</body>

</html>
