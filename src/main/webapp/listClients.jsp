<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.projeto01_web.dto.ClientDTO" %>
<!DOCTYPE html>
<html lang="en">
    
<head>
    <meta charset="UTF-8">
    <title>Lista de Clientes</title>
</head>

<body>
    <a href='index.jsp'>Página Inicial</a>

    <h1>Lista de Clientes</h1>

    <div class='clients'>
        <%
            List<ClientDTO> clients = (List<ClientDTO>) request.getAttribute("clients");
            if (clients != null) {
                for (ClientDTO client : clients) {
        %>
            <div class='client'>
                <h4><%= client.getName() %></h4>
                <p><%= client.getEmail() %></p>
                <p><%= client.getPhone() %></p>
                <a href='editar-cliente?id=<%= client.getId() %>'>Editar</a>
                <form action="clientes" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= client.getId() %>" />
                    <input type="hidden" name="_method" value="DELETE" />
                    <button type="submit"
                        onclick="return confirm('Tem certeza que deseja excluir?')">
                        Deletar
                    </button>
                </form>
            </div>
        <%
                }
            }
            else {
        %>
            <div>
                <p>Nenhum cliente cadastrado</p>
            </div>
        <%
            }
        %>
    </div>
</body>

</html>
