package com.example.project_esalaf;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ProduitDAO {
    Statement state;
    public void insert(Produits produit){
        try {
            state= connectionDB.openConnection().createStatement();
            state.executeUpdate("INSERT INTO `produits`(`nom`,`prix`,`quantite`,`description`)VALUES('"+produit.getNom()+"',"+produit.getPrix()+","+produit.getQuantite()+",'"+produit.getDescription()+"')");
            connectionDB.closeConnection();
        } catch (SQLException e) {
            connectionDB.closeConnection();
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    public void delete(int id){
        try {
            state= connectionDB.openConnection().createStatement();
            state.executeUpdate("Delete FROM `produits`WHERE id="+id);
            connectionDB.closeConnection();
        } catch (SQLException e) {
            connectionDB.closeConnection();
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    public void update(Produits produit){
        try {
            state= connectionDB.openConnection().createStatement();
            state.executeUpdate("UPDATE produits SET `nom` = '"+produit.getNom()+"', `prix` = "+produit.getPrix()+", `quantite` = "+produit.getQuantite()+", `description` = '"+produit.getDescription()+"' WHERE id = "+produit.getId());
            connectionDB.closeConnection();
        } catch (SQLException e) {
            connectionDB.closeConnection();
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE,null,e);
        }
    }
    public ObservableList<Produits> getAllProduits() throws SQLException {
        ObservableList pro= FXCollections.observableArrayList();
        try {
            state=connectionDB.openConnection().createStatement();
            ResultSet resul= state.executeQuery("SELECT *FROM produits");
            while (resul.next()){
                Produits obj =new Produits();
                obj.setId(resul.getInt(1));
                obj.setNom(resul.getString(2));
                obj.setPrix(resul.getFloat(3));
                obj.setQuantite(resul.getInt(4));
                obj.setDescription(resul.getString(5));
                pro.add(obj);
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return pro;
    }
    public ObservableList<Produits> getSearchProduits(String nom) throws SQLException {

        ObservableList pro= FXCollections.observableArrayList();
        try {
            state=connectionDB.openConnection().createStatement();
            ResultSet resul= state.executeQuery("SELECT *FROM produits WHERE nom LIKE '%"+nom+"%'");
            while (resul.next()){
                Produits obj =new Produits();
                obj.setId(resul.getInt(1));
                obj.setNom(resul.getString(2));
                obj.setPrix(resul.getFloat(3));
                obj.setQuantite(resul.getInt(4));
                obj.setDescription(resul.getString(5));
                pro.add(obj);
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(ProduitDAO.class.getName()).log(Level.SEVERE,null,e);
        }
        return pro;
    }
}
