package com.example.project_esalaf;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreditsDAO {
    Statement state;
    public void insert(Credits credit) throws SQLException {
        try {
            state = connectionDB.openConnection().createStatement();
            // Récupérer l'id du client correspondant au nom renseigné
            PreparedStatement ps = connectionDB.openConnection().prepareStatement("SELECT id FROM clients WHERE nom=?");
            ps.setString(1, credit.getClient().getNom());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idClient = rs.getInt("id");
                // Insérer le nouveau crédit avec l'id du client correspondant et l'id de la commande correspondante
                ps = connectionDB.openConnection().prepareStatement("INSERT INTO credits (id_client, id_commande, montant_total, montant_restant, date_echeance, statut) VALUES (?, ?, ?, ?, ?, ?)");
                ps.setInt(1, idClient);
                ps.setInt(2, credit.getID_commande());
                ps.setFloat(3, credit.getMontant_total());
                ps.setFloat(4, credit.getMontant_restant());
                ps.setDate(5, credit.getDate_echeance());
                ps.setString(6, credit.getStatut());
                ps.executeUpdate();
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CreditsDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }



//    public void insert(Credits credits) throws SQLException {
//        try {
//            state = connectionDB.openConnection().createStatement();
//            // Récupérer l'id du client correspondant au nom renseigné
//            PreparedStatement ps = connectionDB.openConnection().prepareStatement("SELECT id FROM clients WHERE nom=?");
//            ps.setString(1, credits.getClient().getNom());
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                int idClient = rs.getInt("id");
//                // Insérer le nouveau crédit avec l'id du client et l'id de la commande correspondants
//                ps = connectionDB.openConnection().prepareStatement("INSERT INTO credits (id_client, id_commande, montant_total, montant_restant, date_echeance, statut) VALUES (?, ?, ?, ?, ?, ?)");
//                ps.setInt(1, idClient);
//                ps.setInt(2, idCommande);
//                ps.setFloat(3, credits.getMontant_total());
//                ps.setFloat(4, credits.getMontant_restant());
//                ps.setString(5, credits.getDate_echeance());
//                ps.setString(6, credits.getStatut());
//                ps.executeUpdate();
//            }
//            connectionDB.closeConnection();
//        } catch (SQLException e) {
//            Logger.getLogger(CreditsDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    public void delete(int id) throws SQLException {
        try {
            PreparedStatement ps = connectionDB.openConnection().prepareStatement("DELETE FROM credits WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CreditsDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void update(Credits credits) throws SQLException {
        try {
            PreparedStatement ps = connectionDB.openConnection().prepareStatement(
                    "UPDATE credits " +
                            "INNER JOIN clients ON credits.id_client = clients.id " +
                            "SET credits.date_echeance = ?, " +
                            "credits.montant_total = ?, " +
                            "credits.montant_restant = ?, " +
                            "credits.statut = ?, " +
                            "credits.id_commande = ?, " +
                            "clients.nom = ? " +
                            "WHERE credits.id = ?");
            ps.setDate(1, credits.getDate_echeance());
            ps.setFloat(2, credits.getMontant_total());
            ps.setFloat(3, credits.getMontant_restant());
            ps.setString(4, credits.getStatut());
            ps.setInt(5, credits.getID_commande());
            ps.setString(6, credits.getClient().getNom());
            ps.setInt(7, credits.getId());
            ps.executeUpdate();
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CreditsDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }












    public ObservableList<Credits> getAllCredit() throws SQLException {
        ObservableList<Credits> creditsList = FXCollections.observableArrayList();

        try {
            state = connectionDB.openConnection().createStatement();
            String query = "SELECT credits.id, credits.id_client, clients.nom AS nom_client, credits.id_commande, credits.montant_total, credits.montant_restant, credits.date_echeance, credits.statut, commandes.id AS id_commande " +
                    "FROM credits " +
                    "INNER JOIN clients ON credits.id_client = clients.id " +
                    "INNER JOIN commandes ON credits.id_commande = commandes.id";
            ResultSet rs = state.executeQuery(query);
            while (rs.next()) {
                // Créer un objet Credits à partir des données récupérées
                Credits credits = new Credits();
                credits.setId(rs.getInt("id"));
                credits.setMontant_total(rs.getFloat("montant_total"));
                credits.setMontant_restant(rs.getFloat("montant_restant"));
                credits.setDate_echeance(rs.getDate("date_echeance"));
                credits.setStatut(rs.getString("statut"));
                credits.setID_commande(rs.getInt("id_commande"));

                // Créer un objet Client à partir des données récupérées
                Clients client = new Clients();
                client.setNom(rs.getString("nom_client"));

                // Ajouter les références du client et de la commande au crédit
                credits.setClient(client);
                creditsList.add(credits);
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CreditsDAO.class.getName()).log(Level.SEVERE, null, e);
        }

        return creditsList;
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




    public ObservableList<Credits> getSearchCredits(String nom) throws SQLException {

        ObservableList<Credits> credits= FXCollections.observableArrayList();
        try {
            state=connectionDB.openConnection().createStatement();
            ResultSet resul= state.executeQuery("SELECT credits.id, credits.id_commande, credits.montant_total, credits.montant_restant, credits.date_echeance, credits.statut, clients.nom " +
                    "FROM credits " +
                    "JOIN commandes ON credits.id_commande = commandes.id " +
                    "JOIN clients ON commandes.id_client = clients.id " +
                    "WHERE clients.nom LIKE '%"+nom+"%'");
            while (resul.next()){
                Credits obj =new Credits();
                obj.setId(resul.getInt(1));
                obj.setID_commande(resul.getInt(2));
                obj.setMontant_total(resul.getFloat(3));
                obj.setMontant_restant(resul.getFloat(4));
                obj.setDate_echeance(resul.getDate(5));
                obj.setStatut(resul.getString(6));
                Clients client = new Clients();
                client.setNom(resul.getString(7));
                obj.setClient(client);
                credits.add(obj);
            }
            connectionDB.closeConnection();
        } catch (SQLException e) {
            Logger.getLogger(CreditsDAO.class.getName()).log(Level.SEVERE,null,e);
        }

        return credits;
    }





}
