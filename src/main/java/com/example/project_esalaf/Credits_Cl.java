package com.example.project_esalaf;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Credits_Cl implements Initializable {

    @FXML
    private Label lb;
    @FXML
    private TextField testnomclient;
    @FXML
    private TextField testmontant_restant;
    @FXML
    private TextField testidcommande;
    @FXML
    private TextField testmontant_total;
    @FXML
    private DatePicker testdate_echeance;
    @FXML
    private TextField teststatut;


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
    private TableColumn colidcommande;
    @FXML
    private TableColumn colmontant_total;
    @FXML
    public TableColumn colmontant_restant;
    public TableColumn coldateecheance;
    public TableColumn colstatut;


    CreditsDAO  pd= new CreditsDAO();
    int ID;
//      ObservableList<String> oltype= FXCollections.observableArrayList();
      public void search() throws SQLException {
          table.setItems(pd.getSearchCredits(testsearch.getText()));

      }
    public void ajouter(ActionEvent actionEvent) throws SQLException {
        Credits credit = new Credits();
        credit.setDate_echeance(Date.valueOf(testdate_echeance.getValue()));
        credit.setMontant_total(Float.parseFloat(testmontant_total.getText()));
        credit.setMontant_restant(Float.parseFloat(testmontant_restant.getText()));
        credit.setStatut(teststatut.getText());
        Clients client = new Clients();
        client.setNom(testnomclient.getText());
        credit.setClient(client);

        credit.setID_commande(Integer.parseInt(testidcommande.getText()));
        pd.insert(credit);
        table.setItems(pd.getAllCredit());
        testnomclient.setText("");
        testmontant_total.setText("");
        testmontant_restant.setText("");
        teststatut.setText("");
        testdate_echeance.setValue(null);
        testidcommande.setText("");
    }


    public void modifier(ActionEvent actionEvent) throws SQLException {
        // Obtenir le crédit sélectionné dans le tableau
        Credits credit = (Credits) table.getSelectionModel().getSelectedItem();

        // Vérifier si un crédit a été sélectionné
        if (credit != null) {
            // Mettre à jour les informations du crédit
            credit.setDate_echeance(Date.valueOf(testdate_echeance.getValue()));
            credit.setMontant_total(Float.parseFloat(testmontant_total.getText()));
            credit.setMontant_restant(Float.parseFloat(testmontant_restant.getText()));
            credit.setStatut(teststatut.getText());
            credit.setID_commande(Integer.parseInt(testidcommande.getText()));
            Clients client = new Clients();
            client.setNom(testnomclient.getText());
            credit.setClient(client);

            // Mettre à jour le crédit dans la base de données
            pd.update(credit);

            // Actualiser le tableau
            table.setItems(pd.getAllCredit());

            // Réinitialiser les champs de saisie
            testdate_echeance.setValue(null);
            testmontant_total.setText("");
            testmontant_restant.setText("");
            teststatut.setText("");
            testnomclient.setText("");
            testidcommande.setText("");

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
public void supprimer(ActionEvent actionEvent) throws SQLException {
    // Obtenir le crédit sélectionné dans le tableau
    Credits credits = (Credits) table.getSelectionModel().getSelectedItem();

    // Vérifier si un crédit a été sélectionné
    if (credits != null) {
        // Demander la confirmation de suppression
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir supprimer le crédit sélectionné ?");
        alert.setContentText("Cette action est irréversible.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // Supprimer le crédit de la base de données
            pd.delete(credits.getId());

            // Rafraîchir le tableau
            table.setItems(pd.getAllCredit());
        }
    }
}


//    public void supprimer(ActionEvent actionEvent) throws SQLException {
//        // Obtenir la commande sélectionnée dans le tableau
//        Commandes commandes = (Commandes) table.getSelectionModel().getSelectedItem();
//
//        // Vérifier si une commande a été sélectionnée
//        if (commandes != null) {
//            // Demander la confirmation de suppression
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("Confirmation de suppression");
//            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer la commande sélectionnée ?");
//            alert.setContentText("Cette action est irréversible.");
//
//            Optional<ButtonType> result = alert.showAndWait();
//            if (result.get() == ButtonType.OK) {
//                // Supprimer la commande de la base de données
//                pd.delete(commandes.getId());
//
//                // Rafraîchir le tableau
//                table.setItems(pd.getAllCommande());
//            }
//        }
//    }

    @FXML
    public void ClickTable(MouseEvent mouseEvent) {
// Obtenir le crédit sélectionné dans le tableau
        Credits credits = (Credits) table.getSelectionModel().getSelectedItem();

        // Vérifier si un produit a été sélectionné
        // Vérifier si une commande a été sélectionnée
        if (credits != null) {
            // Afficher les informations de la commande sélectionnée dans les champs correspondants
            testdate_echeance.setValue(credits.getDate_echeance().toLocalDate());
            testnomclient.setText(credits.getClient().getNom());
            testmontant_total.setText(Float.toString(credits.getMontant_total()));
            testmontant_restant.setText(Float.toString(credits.getMontant_restant()));
            teststatut.setText(credits.getStatut());
            testidcommande.setText(Integer.toString(credits.getID_commande()));

            // Conserver l'ID de la commande sélectionnée pour la modification ultérieure
            ID = credits.getId();
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
        colnomclient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Credits, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Credits, String> cellData) {
                return new SimpleStringProperty(cellData.getValue().getClient().getNom());
            }
        });
        colidcommande.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Credits, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Credits, Integer> cellData) {
                return new SimpleObjectProperty<>(cellData.getValue().getID_commande());
            }
        });

        colmontant_total.setCellValueFactory(new PropertyValueFactory<>("montant_total"));
        colmontant_restant.setCellValueFactory(new PropertyValueFactory<>("montant_restant"));
        coldateecheance.setCellValueFactory(new PropertyValueFactory<>("date_echeance"));
        colstatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        try {
            table.setItems(pd.getAllCredit());
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
