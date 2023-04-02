package com.example.project_esalaf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandeDAO {
    Statement state;
    public void insert(Commandes commande) throws SQLException {
        try {
            PreparedStatement ps = connectionDB.openConnection().prepareStatement(
                    "INSERT INTO commandes (date_commande, id_client, montant_total, statut, id_produit) " +
                            "VALUES (?, ?, ?, ?, ?)");

            // Récupérer l'id du client correspondant au nom renseigné
            PreparedStatement psClient = connectionDB.openConnection().prepareStatement(
                    "SELECT id FROM clients WHERE nom = ?");
            psClient.setString(1, commande.getClient().getNom());
            ResultSet rsClient = psClient.executeQuery();

            if (rsClient.next()) {
                int idClient = rsClient.getInt("id");

                // Récupérer l'id du produit correspondant au nom renseigné
                PreparedStatement psProduit = connectionDB.openConnection().prepareStatement(
                        "SELECT id FROM produits WHERE nom = ?");
                psProduit.setString(1, commande.getProduit().getNom());
                ResultSet rsProduit = psProduit.executeQuery();

                if (rsProduit.next()) {
                    int idProduit = rsProduit.getInt("id");

                    // Insérer la nouvelle commande avec l'id du client et l'id du produit correspondants
                    ps.setDate(1, commande.getDate_commande());
                    ps.setInt(2, idClient);
                    ps.setFloat(3, commande.getMontant_total());
                    ps.setString(4, commande.getStatut());
                    ps.setInt(5, idProduit);
                    ps.executeUpdate();
                }
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }


//    public void insert(Commandes commande, String nomClient, String nomProduit) throws SQLException {
//        try {
//            PreparedStatement ps = connectionDB.openConnection().prepareStatement(
//                    "INSERT INTO commandes (date_commande, id_client, montant_total, statut, id_produit) " +
//                            "VALUES (?, " +
//                            "(SELECT id FROM clients WHERE nom = ?), " +
//                            "?, " +
//                            "?, " +
//                            "(SELECT id FROM produits WHERE nom = ?))");
//            ps.setDate(1, commande.getDate_commande());
//            ps.setString(2, nomClient);
//            ps.setFloat(3, commande.getMontant_total());
//            ps.setString(4, commande.getStatut());
//            ps.setString(5, nomProduit);
//            ps.executeUpdate();
//            connectionDB.closeConnection();
//        } catch (SQLException e) {
//            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

//    public void insert(Commandes commande) throws SQLException {
//        try {
//            state = connectionDB.openConnection().createStatement();
//            // Récupérer l'id du client correspondant au nom renseigné
//            PreparedStatement ps = connectionDB.openConnection().prepareStatement("SELECT id FROM clients WHERE nom=?");
//            ps.setString(1, Commandes.getClient().getNom());
//            ResultSet rs = ps.executeQuery();
//            PreparedStatement pss = connectionDB.openConnection().prepareStatement("SELECT id FROM produits WHERE nom=?");
//            ps.setString(1, Commandes.getProduit().getNom());
//            ResultSet rss = ps.executeQuery();
//            if (rs.next()) {
//                int idClient = rs.getInt("id");
//                int idProduit = rss.getInt("id");
//                // Insérer le nouveau crédit avec l'id du client correspondant et l'id de la commande correspondante
//                ps = connectionDB.openConnection().prepareStatement("INSERT INTO commandes (id_client, id_produit, montant_total, date_commande, statut) VALUES (?, ?, ?, ?, ?)");
//                ps.setInt(1, idClient);
//                ps.setDate(2, commande.getDate_commande());
//                ps.setFloat(3, commande.getMontant_total());
//
//                pss.setInt(4, idProduit);
//                ps.setString(5, commande.getStatut());
//                ps.executeUpdate();
//            }
//            connectionDB.closeConnection();
//        } catch (SQLException e) {
//            Logger.getLogger(CreditsDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }



    public void delete(int commandeId) throws SQLException {
        try {
            String query = "DELETE FROM commandes WHERE id=?";
            PreparedStatement statement = connectionDB.openConnection().prepareStatement(query);
            statement.setInt(1, commandeId);
            statement.executeUpdate();
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void updateCommande(Commandes commande) throws SQLException {
        try {
            PreparedStatement ps = connectionDB.openConnection().prepareStatement(
                    "UPDATE commandes " +
                            "INNER JOIN clients ON commandes.id_client = clients.id " +
                            "INNER JOIN produits ON commandes.id_produit = produits.id " +
                            " SET commandes.date_commande = ?, " +
                            "commandes.montant_total = ?, " +
                            "commandes.statut = ?, " +

                            "clients.nom = ?, " +
                            "produits.nom = ? " +
                            "WHERE commandes.id = ?");
            ps.setDate(1, commande.getDate_commande());
            ps.setFloat(2, commande.getMontant_total());

            ps.setString(3, commande.getStatut());
            ps.setString(4, commande.getClient().getNom());
            ps.setString(5, commande.getProduit().getNom());
            ps.setInt(6, commande.getId());
            ps.executeUpdate();
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
//    public void updateCommande(Commandes commande, String nouveauNomClient, String nouveauNomProduit, float nouveauMontantTotal, String nouveauStatut, Date nouveaudate ) throws SQLException {
//        try {
//            PreparedStatement ps = connectionDB.openConnection().prepareStatement(
//                    "UPDATE commandes " +
//                            "INNER JOIN clients ON commandes.id_client = clients.id " +
//                            "INNER JOIN produits ON commandes.id_produit = produits.id " +
//                            " SET commandes.date_commande = ?, " +
//                            "commandes.montant_total = ?, " +
//                            "commandes.statut = ?, " +
//
//                            "clients.nom = ?, " +
//                            "produits.nom = ? " +
//                            "WHERE commandes.id = ?");
//            ps.setDate(1, nouveaudate);
//            ps.setFloat(2, nouveauMontantTotal);
//
//            ps.setString(3, nouveauStatut);
//            ps.setString(4, nouveauNomClient);
//            ps.setString(5, nouveauNomProduit);
//            ps.setInt(6, commande.getId());
//            ps.executeUpdate();
//            connectionDB.closeConnection();
//        } catch (SQLException e) {
//            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }





    public ObservableList<Commandes> getAllCommandes() throws SQLException {
        ObservableList<Commandes> commandeList = FXCollections.observableArrayList();

        try {
            state = connectionDB.openConnection().createStatement();
            String query = "SELECT commandes.id,commandes.montant_total,commandes.statut, commandes.date_commande, commandes.id_client, commandes.id_produit,clients.nom AS nom_client, produits.nom AS nom_produit  " +
                    "FROM commandes " +
                    "INNER JOIN clients ON commandes.id_client = clients.id " +
                    "INNER JOIN produits ON commandes.id_produit = produits.id";
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                // Créer un objet Commande à partir des données récupérées
                Commandes commande = new Commandes();
                commande.setId(rs.getInt("id"));
                commande.setDate_commande(rs.getDate("date_commande"));
                commande.setMontant_total(rs.getFloat("montant_total"));
                commande.setStatut(rs.getString("statut"));

                // Créer un objet Client à partir des données récupérées
                Clients client = new Clients();
                client.setNom(rs.getString("nom_client"));

                // Créer un objet Produit à partir des données récupérées
                Produits produit = new Produits();
                produit.setNom(rs.getString("nom_produit"));

                // Ajouter les références du client et du produit à la commande
                commande.setClient(client);
                commande.setProduit(produit);

                commandeList.add(commande);
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return commandeList;
    }




//    public ObservableList<Commandes> getAllCommande() throws SQLException {
//        ObservableList commandes = FXCollections.observableArrayList();
//        try {
//            state = connectionDB.openConnection().createStatement();
//            ResultSet resul = state.executeQuery("SELECT commandes.id, commandes.date_commande, commandes.montant_total, commandes.statut, clients.id FROM commandes JOIN clients ON commandes.id_client = clients.id");
////            ResultSet resul = state.executeQuery("SELECT * from commandes");
//            while (resul.next()) {
//                Commandes commande = new Commandes();
//                commande.setId(resul.getInt(1));
//                commande.setDate_commande(resul.getString(2));
//                commande.setMontant_total(resul.getFloat(3));
//                commande.setStatut(resul.getString(4));
//                commande.setId_client(resul.getInt(5));
//                commandes.add(commande);
//            }
//            connectionDB.closeConnection();
//        } catch (SQLException e) {
//            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//        return commandes;
//    }




    public ObservableList<Commandes> getSearchCommandes(String nom) throws SQLException {
        ObservableList<Commandes> commandesList = FXCollections.observableArrayList();

        try {
            state = connectionDB.openConnection().createStatement();
            String query = "SELECT commandes.id, commandes.date_commande, commandes.montant_total, commandes.statut, " +
                    "clients.nom AS nom_client, produits.nom AS nom_produit " +
                    "FROM commandes " +
                    "INNER JOIN clients ON commandes.id_client = clients.id " +
                    "INNER JOIN produits ON commandes.id_produit = produits.id " +
                    "WHERE clients.nom LIKE '%" + nom + "%'";
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                // Créer un objet Commandes à partir des données récupérées
                Commandes commande = new Commandes();
                commande.setId(rs.getInt("id"));
                commande.setDate_commande(rs.getDate("date_commande"));
                commande.setMontant_total(rs.getFloat("montant_total"));
                commande.setStatut(rs.getString("statut"));

                // Créer un objet Client à partir des données récupérées
                Clients client = new Clients();
                client.setNom(rs.getString("nom_client"));

                // Créer un objet Produits à partir des données récupérées
                Produits produit = new Produits();
                produit.setNom(rs.getString("nom_produit"));

                // Ajouter les références du client et du produit à la commande
                commande.setClient(client);
                commande.setProduit(produit);
                commandesList.add(commande);
            }
            connectionDB.closeConnection();

        } catch (SQLException e) {
            Logger.getLogger(CommandeDAO.class.getName()).log(Level.SEVERE,null,e);
        }

        return commandesList;
    }



}
