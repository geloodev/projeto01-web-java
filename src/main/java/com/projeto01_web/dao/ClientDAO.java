package com.projeto01_web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projeto01_web.dto.ClientDTO;
import com.projeto01_web.dto.DiscountDTO;
import com.projeto01_web.dto.MembershipDTO;
import com.projeto01_web.utils.DatabaseConnectionFactory;

public class ClientDAO {

    DiscountDAO discountDAO = new DiscountDAO();
    MembershipDAO membershipDAO = new MembershipDAO();

    public ClientDAO() {
    }

    public ClientDTO getClientById(UUID id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        ClientDTO client = null;
        DiscountDTO discount = null;
        MembershipDTO membership = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                UUID membership_id = UUID.fromString(
                        resultSet.getString("membership_id"));
                UUID discount_id = UUID.fromString(
                        resultSet.getString("discount_id"));

                membership = membershipDAO.getMembershipById(membership_id);
                discount = discountDAO.getDiscountById(discount_id);
                client = new ClientDTO(id, name, email, phone, membership, discount);
            }
        } catch (Exception e) {
            System.out.println("Error on ClientDAO.getClientById: " +
                    e.getMessage());
        }

        return client;
    }

    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clients = new ArrayList<>();
        String sql = "SELECT clients.id, clients.name, clients.email, " +
                "clients.phone, d.duration AS discount_duration, m.name AS " +
                "membership_name " +
                "FROM clients " +
                "INNER JOIN discounts d ON clients.discount_id = d.id " +
                "INNER JOIN memberships m ON clients.membership_id = m.id";
        ClientDTO client = null;
        MembershipDTO membership = null;
        DiscountDTO discount = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                UUID id = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String membership_name = resultSet.getString("membership_name");
                int discount_duration = Integer.parseInt(
                        resultSet.getString("discount_duration"));

                membership = membershipDAO.getMembershipByName(membership_name);
                discount = discountDAO.getDiscountByDuration(
                        discount_duration);
                client = new ClientDTO(id, name, email, phone, membership, discount);
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println("Error on ClientDAO.getAllClients: " +
                    e.getMessage());
        }

        return clients;
    }

    public void saveClient(ClientDTO client) {
        String sql = "INSERT INTO clients (id, name, email, phone, " +
                "membership_id, discount_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getId().toString());
            statement.setString(2, client.getName());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getMembership().getId().toString());
            statement.setString(6, client.getDiscount().getId().toString());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void updateClient(ClientDTO client) {
        String sql = "UPDATE clients SET name = ?, email = ?, phone = ?, " +
                "membership_id = ?, discount_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getMembership().getId().toString());
            statement.setString(5, client.getDiscount().getId().toString());
            statement.setString(6, client.getId().toString());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error on ClientDAO.updateCLient():" +
                    e.getMessage());
        }
    }

    public boolean deleteClient(UUID id) {
        String sql = "DELETE FROM clients WHERE id = ?";
        boolean rowDeleted = false;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id.toString());
            rowDeleted = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error on ClientDAO.deleteClient: " +
                    e.getMessage());
        }

        return rowDeleted;
    }
}
