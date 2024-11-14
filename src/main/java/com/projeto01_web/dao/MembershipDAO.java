package com.projeto01_web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projeto01_web.dto.MembershipDTO;
import com.projeto01_web.utils.DatabaseConnectionFactory;

public class MembershipDAO {

    public MembershipDAO() {
    }

    public MembershipDTO getMembershipById(UUID id) {
        String sql = "SELECT id, name, price FROM memberships " +
                "WHERE id = ?";
        MembershipDTO membership = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                membership = new MembershipDTO(id, name, price);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return membership;
    }

    public List<MembershipDTO> getAllMemberships() {
        List<MembershipDTO> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";
        MembershipDTO membership = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                Double price = Double.parseDouble(resultSet.getString("price"));
                membership = new MembershipDTO(id, name, price);
                memberships.add(membership);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return memberships;
    }

    public MembershipDTO getMembershipByName(String name) {
        String sql = "SELECT * FROM memberships WHERE name = ?";
        MembershipDTO membership = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                membership = new MembershipDTO(id, name, price);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return membership;
    }

}
