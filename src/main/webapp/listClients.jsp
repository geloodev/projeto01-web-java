<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.projeto01_web.dto.ClientDTO" %>

<!DOCTYPE html>
<html lang="pt-br">
    
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Clientes</title>
    <link rel="stylesheet" href="css/style.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
    <div class='main wrapper'>
        <div class='sidebar'>
            <div class='brand'>
                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" 
                        viewBox="0 0 24 24" fill="none" stroke="currentColor" 
                        stroke-width="2" stroke-linecap="round" 
                        stroke-linejoin="round" class="lucide lucide-dumbbell">
                    <path d="M14.4 14.4 9.6 9.6"/>
                    <path d="M18.657 21.485a2 2 0 1 1-2.829-2.828l-1.767 1.768a2 
                        2 0 1 1-2.829-2.829l6.364-6.364a2 2 0 1 1 2.829 
                        2.829l-1.768 1.767a2 2 0 1 1 2.828 2.829z"/>
                    <path d="m21.5 21.5-1.4-1.4"/>
                    <path d="M3.9 3.9 2.5 2.5"/>
                    <path d="M6.404 12.768a2 2 0 1 1-2.829-2.829l1.768-1.767a2 
                        2 0 1 1-2.828-2.829l2.828-2.828a2 2 0 1 1 2.829 
                        2.828l1.767-1.768a2 2 0 1 1 2.829 2.829z"/>
                </svg>
                GymMan
            </div>
            <div class='sidebar-items'>
                <a href='clientes' class='sidebar-item selected'>
                    <i class='bx bxs-user-rectangle'></i>
                    Clientes
                </a>

                <a href='planos' class='sidebar-item'>
                    <i class='bx bxs-credit-card-front'></i>
                    Planos
                </a>
            </div>
        </div>

        <div class='content'>
            <h2 class='content-title'>Todos os Clientes</h2>

            <div class='new-client-div'>
                <a href='novo-cliente' class='new-client-button'>
                    <i class='bx bxs-user-plus'></i>Novo Cliente
                </a>
            </div>

            <div class='table'>
                <div class='table-header'>
                    <table cellpadding='0' cellspacing='0' border='0'>
                        <thead>
                            <tr>
                                <th class='th-name'>Nome</th>
                                <th class='th-contacts'>Contatos</th>
                                <th class='th-plan'>Plano</th>
                                <th class='th-actions'>Ações</th>
                            </tr>
                        </thead>
                    </table>
                </div>

                <div class='table-content'>
                    <table cellpadding='0' cellspacing='0' border='0'>
                        <tbody>
                            <%
                                List<ClientDTO> clients = (List<ClientDTO>) request.getAttribute("clients");
                                if (clients != null) {
                                    for (ClientDTO client : clients) {
                            %>
                            <tr>
                                <td class='td-name'>
                                    <div>
                                        <img src='assets/user.svg' />
                                        <%= client.getName() %>
                                    </div>
                                </td>
                                <td class='td-contacts'>
                                    <span><%= client.getPhone() %></span><br>
                                    <span class="email">
                                        <%= client.getEmail() %>
                                    </span>
                                </td>
                                <td class='td-plan'>
                                    <%= client.getMembership().getName() %>
                                </td>
                                <td class='td-actions'>
                                    <div>
                                        <a href='editar-cliente?id=<%= client.getId() %>'
                                            class='action'>
                                            Editar
                                        </a>
                                        <form action="clientes" method="post" 
                                                style="display:inline;">
                                            <input type="hidden" name="id" 
                                                value="<%= client.getId() %>" 
                                            />
                                            <input type="hidden" name="_method" 
                                                value="DELETE" 
                                            />
                                            <button type="submit" class='action'
                                                onclick="return confirm('Tem certeza que deseja excluir?')">
                                                Deletar
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
