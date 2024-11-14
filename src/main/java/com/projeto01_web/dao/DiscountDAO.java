package com.projeto01_web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projeto01_web.dto.DiscountDTO;
import com.projeto01_web.utils.DatabaseConnectionFactory;

public class DiscountDAO {

    public DiscountDAO() {
    }

    public List<DiscountDTO> getAllDiscounts() {
        List<DiscountDTO> discounts = new ArrayList<>();
        String sql = "SELECT * FROM discounts";
        DiscountDTO discount = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                int duration = resultSet.getInt("duration");
                int discount_percentage = resultSet.getInt("discount_percentage");
                discount = new DiscountDTO(id, duration, discount_percentage);
                discounts.add(discount);
            }
        } catch (Exception e) {
            System.out.println("Error on DiscoutDAO.getAllDiscounts: "
                    + e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return discounts;
    }

    public DiscountDTO getDiscountById(UUID id) {
        String sql = "SELECT id, duration, discount_percentage FROM discounts " +
                "WHERE id = ?";
        DiscountDTO discount = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = UUID.fromString(resultSet.getString("id"));
                int duration = resultSet.getInt("duration");
                int discount_percentage = resultSet.getInt("discount_percentage");
                discount = new DiscountDTO(id, duration, discount_percentage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return discount;
    }

    public DiscountDTO getDiscountByDuration(int duration) {
        String sql = "SELECT * FROM discounts WHERE duration = ?";
        DiscountDTO discount = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, duration);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                int discountDuration = Integer.parseInt(
                        resultSet.getString("duration"));
                int discount_percentage = Integer.parseInt(
                        resultSet.getString("discount_percentage"));
                discount = new DiscountDTO(id, discountDuration,
                        discount_percentage);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return discount;
    }
}
