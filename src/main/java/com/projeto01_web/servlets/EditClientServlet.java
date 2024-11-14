package com.projeto01_web.servlets;

import java.io.IOException;
import java.util.UUID;

import com.projeto01_web.dao.ClientDAO;
import com.projeto01_web.dao.DiscountDAO;
import com.projeto01_web.dao.MembershipDAO;
import com.projeto01_web.dto.ClientDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditClientServlet extends HttpServlet {

    private ClientDAO clientDAO = new ClientDAO();
    private MembershipDAO membershipDAO = new MembershipDAO();
    private DiscountDAO discountDAO = new DiscountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        if (id == null) {
            System.out.println("EditClientServlet: id is null.");
            response.sendRedirect("index.jsp");
        } else {
            ClientDTO client = clientDAO.getClientById(id);
            if (client == null) {
                System.out.println("EditClientServlet: client is null with this id.");
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("client", client);
                request.setAttribute("memberships", membershipDAO.getAllMemberships());
                request.setAttribute("discounts", discountDAO.getAllDiscounts());
                RequestDispatcher dispatcher = request.getRequestDispatcher("editClient.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
