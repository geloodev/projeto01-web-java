package com.projeto01_web.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.projeto01_web.dto.ClientDTO;
import com.projeto01_web.utils.DatabaseConnectionFactory;

public class ClientDAO {

    public ClientDAO() {
    }

    public ClientDTO getClientById(UUID id) {
        String sql = "SELECT * FROM clients WHERE id = ?";
        ClientDTO client = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id.toString());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                UUID clientId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                client = new ClientDTO(clientId, name, email, phone);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return client;
    }

    public List<ClientDTO> getAllClients() {
        List<ClientDTO> clients = new ArrayList<>();
        String sql = "SELECT id, name, email, phone FROM clients";
        ClientDTO client = null;

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                UUID clientId = UUID.fromString(resultSet.getString("id"));
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                client = new ClientDTO(clientId, name, email, phone);
                clients.add(client);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

        return clients;
    }

    public void saveClient(ClientDTO client) {
        String sql = "INSERT INTO clients (id, name, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getId().toString());
            statement.setString(2, client.getName());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPhone());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    public void updateClient(ClientDTO client) {
        String sql = "UPDATE clients SET name = ?, email = ?, phone = ? WHERE id = ?";

        try (Connection connection = DatabaseConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, client.getName());
            statement.setString(2, client.getEmail());
            statement.setString(3, client.getPhone());
            statement.setString(4, client.getId().toString());

            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error on ClientDAO.updateCLient():" + e.getMessage());
            System.out.println(e.getStackTrace());
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
            System.out.println("Error while deleting client: " + e.getMessage());
        }

        return rowDeleted;
    }
}
