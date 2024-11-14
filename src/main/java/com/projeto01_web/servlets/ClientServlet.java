package com.projeto01_web.servlets;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.projeto01_web.dao.ClientDAO;
import com.projeto01_web.dao.DiscountDAO;
import com.projeto01_web.dao.MembershipDAO;
import com.projeto01_web.dto.ClientDTO;
import com.projeto01_web.dto.DiscountDTO;
import com.projeto01_web.dto.MembershipDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClientServlet extends HttpServlet {

    private ClientDAO clientDAO = new ClientDAO();
    private MembershipDAO membershipDAO = new MembershipDAO();
    private DiscountDAO discountDAO = new DiscountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ClientDTO> clients = clientDAO.getAllClients();
        request.setAttribute("clients", clients);
        request.getRequestDispatcher("listClients.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");

        if ("PUT".equalsIgnoreCase(method)) {
            doPut(request, response);
        } else if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response);
        } else {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            UUID membership_id = UUID.fromString(request.getParameter("membership"));
            UUID discount_id = UUID.fromString(request.getParameter("discount"));

            MembershipDTO membership = membershipDAO.getMembershipById(membership_id);
            DiscountDTO discount = discountDAO.getDiscountById(discount_id);
            ClientDTO client = new ClientDTO(name, email, phone, membership, discount);
            clientDAO.saveClient(client);

            response.sendRedirect("");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        UUID membership_id = UUID.fromString(request.getParameter("membership"));
        UUID discount_id = UUID.fromString(request.getParameter("discount"));

        ClientDTO client = new ClientDTO(id, name, email, phone,
                membershipDAO.getMembershipById(membership_id),
                discountDAO.getDiscountById(discount_id));
        System.out.println("Client created on ClientServlet.doPut: " +
                client.getId() + client.getName() +
                client.getMembership().getName());
        clientDAO.updateClient(client);

        response.sendRedirect("");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));
        clientDAO.deleteClient(id);
        response.sendRedirect("");
    }
}
