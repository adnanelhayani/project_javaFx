package com.example.project_esalaf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDAO {
    Statement state;
    public void insert(Clients client){
        try {
            state = connectionDB.openConnection().createStatement();
            state.executeUpdate("INSERT INTO `clients`(`nom`, `prenom`, `adresse`, `email`, `telephone`) VALUES ('" + client.getNom() + "', '" + client.getPrenom() + "', '" + client.getAdresse() + "', '" + client.getEmail() + "', '" + client.getTelephone() + "')");

            connectionDB.closeConnection();
        } catch (SQLException e) {
            connectionDB.closeConnection();
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void delete(int id){
        try {
            state= connectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `clients`WHERE id="+id);
            connectionDB.closeConnection();
        } catch (SQLException e) {
            connectionDB.closeConnection();
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    public void update(Clients client){
        try {
            state = connectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE clients SET `nom` = '"+client.getNom()+"', `prenom` = '"+client.getPrenom()+"', `adresse` = '"+client.getAdresse()+"', `email` = '"+client.getEmail()+"', `telephone` = '"+client.getTelephone()+"' WHERE id = "+client.getId());

            connectionDB.closeConnection();
        } catch (SQLException e) {
            connectionDB.closeConnection();
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public ObservableList<Produits> getAllClient() throws SQLException {
        ObservableList pro= FXCollections.observableArrayList();
        try {
            state=connectionDB.openConnection().createStatement();
            ResultSet resul= state.executeQuery("SELECT *FROM clients");
            while (resul.next()){
                Clients obj =new Clients();
                obj.setId(resul.getInt(1));
                obj.setNom(resul.getString(2));
                obj.setPrenom(resul.getString(3));
                obj.setAdresse(resul.getString(4));
                obj.setEmail(resul.getString(5));
                obj.setTelephone(resul.getInt(6));
                pro.add(obj);
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE,null,e);
        }

        return pro;
    }

    public ObservableList<Produits> getSearchClients(String nom) throws SQLException {

        ObservableList pro= FXCollections.observableArrayList();
        try {
            state=connectionDB.openConnection().createStatement();
            ResultSet resul= state.executeQuery("SELECT *FROM clients WHERE nom LIKE '%"+nom+"%'");
            while (resul.next()){
                Clients obj =new Clients();
                obj.setId(resul.getInt(1));
                obj.setNom(resul.getString(2));
                obj.setPrenom(resul.getString(3));
                obj.setAdresse(resul.getString(4));
                obj.setEmail(resul.getString(5));
                obj.setTelephone(resul.getInt(6));
                pro.add(obj);
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE,null,e);
        }

        return pro;
    }


    public Clients getClientById(int idClient) {
        Clients client = null;
        try {
            state = connectionDB.openConnection().createStatement();
            ResultSet result = state.executeQuery("SELECT * FROM clients WHERE id = " + idClient);
            if (result.next()) {
                client = new Clients();
                client.setId(result.getInt(1));
                client.setNom(result.getString(2));
                client.setPrenom(result.getString(3));
                client.setAdresse(result.getString(4));
                client.setEmail(result.getString(5));
                client.setTelephone(Integer.parseInt(result.getString(6)));
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            connectionDB.closeConnection();
            Logger.getLogger(ClientDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return client;
    }
//public Clients getClientById(int id) throws SQLException {
//    Clients client = null;
//    String query = "SELECT * FROM clients WHERE id = ?";
//
//    try (PreparedStatement statement = Connection.prepareStatement(query)) {
//        statement.setInt(1, id);
//        ResultSet resultSet = statement.executeQuery();
//
//        if (resultSet.next()) {
//            client = new Clients(
//                    resultSet.getInt("id"),
//                    resultSet.getString("nom"),
//                    resultSet.getString("prenom"),
//                    resultSet.getString("adresse"),
//                    resultSet.getString("email"),
//                    resultSet.getString("telephone")
//            );
//        }
//    }
//
//    return client;
//}

//    public Clients getClientById(int idClient) throws SQLException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        ResultSet rs = null;
//        Clients client = null;
//
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/esalaf","root","");
//            stmt = conn.prepareStatement("SELECT * FROM clients WHERE id = ?");
//            stmt.setInt(1, idClient);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                client = new Clients();
//            }
//        } catch (SQLException e) {
//            throw new SQLException("Error fetching client with ID " + idClient, e);
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (stmt != null) {
//                stmt.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        }
//
//        return client;
//    }


}
