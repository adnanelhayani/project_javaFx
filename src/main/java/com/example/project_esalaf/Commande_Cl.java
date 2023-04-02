package com.example.project_esalaf;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Commande_Cl implements Initializable {


    @FXML
    private Label lb;
    @FXML
    private DatePicker testdate;
    @FXML
    private TextField teststatut;
    @FXML
    private TextField testidclient;
    @FXML
    private TextField testmontant;
    @FXML
    private TextField testnomproduit;


    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
//    @FXML
//    private Button btnreteur;
    @FXML
    private Button btnsear;
    @FXML
    private TextField testsearch;
    @FXML
    private TableView table;
    @FXML
    private TableColumn colid;
    @FXML
    private TableColumn colnomclient;
    @FXML
    private TableColumn colmontant;
    @FXML
    private TableColumn colstatut;
    @FXML
    public TableColumn colnomProduit;
    @FXML
    public TableColumn coldate;

    CommandeDAO  pd= new CommandeDAO();
    int ID;

   

    //      ObservableList<String> oltype= FXCollections.observableArrayList();
      public void search() throws SQLException {
          table.setItems(pd.getSearchCommandes(testsearch.getText()));

      }
    @FXML
    public void ajouter(ActionEvent actionEvent) throws SQLException {
        Commandes commandes = new Commandes();
        commandes.setDate_commande(Date.valueOf(testdate.getValue()));
        commandes.setMontant_total(Float.parseFloat(testmontant.getText()));

        commandes.setStatut(teststatut.getText());
        Clients client = new Clients();
        client.setNom(testidclient.getText());
        commandes.setClient(client);
        Produits produits = new Produits();
        produits.setNom(testnomproduit.getText());
        commandes.setProduit(produits);

        pd.insert(commandes);
        table.setItems(pd.getAllCommandes());
        testidclient.setText("");
        testmontant.setText("");

        teststatut.setText("");
        testdate.setValue(null);
        testnomproduit.setText("");
    }


    public void modifier(ActionEvent actionEvent) throws SQLException {
        // Obtenir la commande sélectionnée dans le tableau
        Commandes commande = (Commandes) table.getSelectionModel().getSelectedItem();

        // Vérifier si une commande a été sélectionnée
        if (commande != null) {
            // Mettre à jour les informations de la commande
            commande.setMontant_total(Float.parseFloat(testmontant.getText()));
            commande.setStatut(teststatut.getText());
            commande.setDate_commande(Date.valueOf(testdate.getValue()));
            Clients client = new Clients();
            client.setNom(testidclient.getText());
            commande.setClient(client);
            Produits produit = new Produits();
            produit.setNom(testnomproduit.getText());
            commande.setProduit(produit);

            // Mettre à jour la commande dans la base de données
            pd.updateCommande(commande);

            // Actualiser le tableau
            table.setItems(pd.getAllCommandes());

            // Réinitialiser les champs de saisie
            testmontant.setText("");
            teststatut.setText("");
            testidclient.setText("");
            testnomproduit.setText("");
            testdate.setValue(null);

            // Réinitialiser la variable ID
            ID = 0;
        }
    }




//      public void modifier(ActionEvent actionEvent) throws SQLException {
//          Produits pp=new Produits();
//          pp.setNom( testnom.getText());
//          pp.setPrix( Float.parseFloat(testprix.getText()));
//          pp.setQuantite( Integer.parseInt(testquantite.getText()));
//          pp.setDescription( testdescription.getText());
//          pd.update(pp);
//          table.setItems(pd.getAllProduits());
//          testnom.setText("");
//          testprix.setText("");
//          testquantite.setText("");
//          testdescription.setText("");
//      }
//public void supprimer() throws SQLException {
//    // Obtenir la commande sélectionnée dans le tableau
//    Commandes commandes = (Commandes) table.getSelectionModel().getSelectedItem();
//
//    // Vérifier si une commande a été sélectionnée
//    if (commandes != null) {
//        // Supprimer la commande de la base de données
//        pd.delete(commandes.getId());
//
//        // Rafraîchir le tableau
//        table.setItems(pd.getAllCommande());
//    }
//}

    public void supprimer(ActionEvent actionEvent) throws SQLException {
        // Obtenir la commande sélectionnée dans le tableau
        Commandes commande = (Commandes) table.getSelectionModel().getSelectedItem();

        // Vérifier si une commande a été sélectionnée
        if (commande != null) {
            // Demander la confirmation de suppression
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer la commande sélectionnée ?");
            alert.setContentText("Cette action est irréversible.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                // Supprimer la commande de la base de données
                CommandeDAO commandeDAO = new CommandeDAO();
                commandeDAO.delete(commande.getId());

                // Rafraîchir le tableau
                table.setItems(commandeDAO.getAllCommandes());
            }
        }
    }




    @FXML
    public void ClickTable(MouseEvent mouseEvent) {
        // Obtenir le produit sélectionné dans le tableau
        Commandes commandes = (Commandes) table.getSelectionModel().getSelectedItem();

        // Vérifier si un produit a été sélectionné
        // Vérifier si une commande a été sélectionnée
        if (commandes != null) {
            // Afficher les informations de la commande sélectionnée dans les champs correspondants
            testdate.setValue(commandes.getDate_commande().toLocalDate());
            testidclient.setText(commandes.getClient().getNom());
            testnomproduit.setText(commandes.getProduit().getNom());
            testmontant.setText(Float.toString(commandes.getMontant_total()));
            teststatut.setText(commandes.getStatut());

            // Conserver l'ID de la commande sélectionnée pour la modification ultérieure
            ID = commandes.getId();
        }

    }

    public void retourPagePrecedente(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Home.fxml"));
        Scene scene = new Scene(root);
//        Node node =(Node) actionEvent.getSource();
//        Stage stage=(Stage) node.getScene().getWindow();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public void quitterApplication(ActionEvent event)  {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }






    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
        colnomclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commandes, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Commandes, String> cellData) {
                return new SimpleObjectProperty<>(cellData.getValue().getClient().getNom());
            }
        });
        colmontant.setCellValueFactory(new PropertyValueFactory<>("montant_total"));
        colstatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        colnomProduit.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commandes, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Commandes, String> cellData) {
                return new SimpleObjectProperty<>(cellData.getValue().getProduit().getNom());
            }
        });

        try {
            table.setItems(pd.getAllCommandes());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







//    public void initialize(URL location, ResourceBundle resources) {
//        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
//        coldate.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
//        colnomclient.setCellValueFactory(new PropertyValueFactory<>("id_client"));
//        colmontant.setCellValueFactory(new PropertyValueFactory<>("montant_total"));
//        colstatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
//
//
//        try {
//            table.setItems(pd.getAllCommande());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    public void initialize(URL location, ResourceBundle resources) {
//        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
//        coldate.setCellValueFactory(new PropertyValueFactory<>("date_commande"));
//        colnomclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Commandes, String>, ObservableValue<String>>() {
//
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<Commandes, String> cellData)  {
//                try {
//                    int idClient = Integer.parseInt(cellData.getValue().getId_clients());
//                    ClientDAO clientDAO = new ClientDAO();
//                    Clients client = clientDAO.getClientById(idClient);
//                    return new SimpleStringProperty(client.getNom());
//                } catch (SQLException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//        colmontant.setCellValueFactory(new PropertyValueFactory<>("montant_total"));
//        colstatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
//
//        try {
//            ObservableList<Commandes> commandes = pd.getAllCommande();
//            table.setItems(commandes);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }





}
