package com.projeto01_web.servlets;

import java.io.IOException;

import com.projeto01_web.dao.DiscountDAO;
import com.projeto01_web.dao.MembershipDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NewClientServlet extends HttpServlet {

    MembershipDAO membershipDAO = new MembershipDAO();
    DiscountDAO discountDAO = new DiscountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("memberships", membershipDAO.getAllMemberships());
        request.setAttribute("discounts", discountDAO.getAllDiscounts());
        RequestDispatcher dispatcher = request.getRequestDispatcher("newClient.jsp");
        dispatcher.forward(request, response);
    }
}
