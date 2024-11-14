<%@ page contentType="text/html; charset=UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.projeto01_web.dto.MembershipDTO" %>
<%@ page import="com.projeto01_web.dto.DiscountDTO" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Novo Cliente</title>
    <link rel="stylesheet" href="css/style.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>

<body>
    <main class='main wrapper'>
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
            <h2 class='content-title'>Novo Cliente</h2>

            <form class='form' action="clientes" method="POST">
                <div class='form-section'>
                    <h4>Informações Básicas</h4>

                    <div class='form-field'>
                        <input type="text" id="name" name="name" required
                            placeholder='Nome'
                        >
                    </div>

                    <div class='form-field'>
                        <input type="email" id="email" name="email" required
                            placeholder='E-mail'
                        >
                    </div>

                    <div class='form-field'>
                        <input type="text" id="phone" name="phone" required
                            placeholder='Telefone'
                        >
                    </div>
                </div>

                <div class='form-section'>
                    <h4>Plano</h4>

                    <div class='input-group'>
                        <%
                            List<MembershipDTO> memberships = (List<MembershipDTO>) 
                                request.getAttribute("memberships");
                            if (memberships != null) {
                                for (MembershipDTO membership : memberships) {
                        %>
                        <input type="radio" id="membership-<%= membership.getName() %>" 
                            name="membership" value="<%= membership.getId() %>" 
                            required>
                        <label for='membership-<%= membership.getName() %>'>
                            <%= membership.getName() %><br>
                            R$<%= membership.getPrice() %>0/mês
                        </label>
                        <%
                                }
                            }
                        %>
                    </div>
                </div>

                <div class='form-section'>
                    <h4>Período</h4>

                    <div class='input-group'>
                        <%
                            List<DiscountDTO> discounts = (List<DiscountDTO>) 
                                request.getAttribute("discounts");
                            if (discounts != null) {
                                for (DiscountDTO discount : discounts) {
                                    if (discount.getDuration() == 1) {
                        %>
                        <input type="radio" 
                            id="discount-<%= discount.getDuration() %>" 
                            name="discount" value="<%= discount.getId() %>" 
                            required>
                        <label for='discount-<%= discount.getDuration() %>'>
                            <%= discount.getDuration() %> mês<br>
                            (Sem desconto)
                        </label>
                        <%
                                    } else {
                        %>
                        <input type="radio" 
                            id="discount-<%= discount.getDuration() %>" 
                            name="discount" value="<%= discount.getId() %>" 
                            required>
                        <label for='discount-<%= discount.getDuration() %>'>
                            <%= discount.getDuration() %> meses<br>
                            (<%= discount.getDiscountPercentage() %>% OFF)
                        </label>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                </div>

                <input type="submit" value="Novo Cliente">
            </form>
        </div>
    </main>
</body>

</html>
