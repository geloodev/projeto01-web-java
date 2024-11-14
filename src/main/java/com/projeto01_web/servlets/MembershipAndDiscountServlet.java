package com.projeto01_web.servlets;

import java.io.IOException;
import java.util.List;

import com.projeto01_web.dao.DiscountDAO;
import com.projeto01_web.dao.MembershipDAO;
import com.projeto01_web.dto.DiscountDTO;
import com.projeto01_web.dto.MembershipDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MembershipAndDiscountServlet extends HttpServlet {

    private MembershipDAO membershipDAO = new MembershipDAO();
    private DiscountDAO discountDAO = new DiscountDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<MembershipDTO> memberships = membershipDAO.getAllMemberships();
        List<DiscountDTO> discounts = discountDAO.getAllDiscounts();
        request.setAttribute("memberships", memberships);
        request.setAttribute("discounts", discounts);
        request.getRequestDispatcher("listMembershipsAndDiscounts.jsp").forward(request, response);
    }
}
