package com.projeto01_web.servlets;

import java.io.IOException;
import java.util.UUID;

import com.projeto01_web.dao.ClientDAO;
import com.projeto01_web.dto.ClientDTO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UUID id = UUID.fromString(request.getParameter("id"));

        if (id == null)
            response.sendRedirect("index.jsp");
        else {
            ClientDAO clientDAO = new ClientDAO();
            ClientDTO client = clientDAO.getClientById(id);
            if (client == null) {
                response.sendRedirect("index.jsp");
            } else {
                request.setAttribute("client", client);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editClient.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
