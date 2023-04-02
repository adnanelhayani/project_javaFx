package com.example.project_esalaf;
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
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class Produit_Cl implements Initializable {
    @FXML
    private Label lb;
    @FXML
    private TextField testnom;
    @FXML
    private TextField testdescription;
    @FXML
    private TextField testprix;
    @FXML
    private TextField testquantite;
    @FXML
    private Button btnajouter;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsear;
    @FXML
    private TextField testsearch;
    @FXML
    private TableView table;
    @FXML
    private TableColumn colid;
    @FXML
    private TableColumn colnom;
    @FXML
    private TableColumn colprix;
    @FXML
    private TableColumn colquantite;
    @FXML
    private TableColumn coldescription;
    ProduitDAO  pd=new ProduitDAO();
    int ID;

      public void search() throws SQLException {
         table.setItems(pd.getSearchProduits(testsearch.getText()));
      }
      public void ajouter (ActionEvent actionEvent) throws SQLException {
          Produits pp=new Produits();
         pp.setNom( testnom.getText());
         pp.setPrix( Float.parseFloat(testprix.getText()));
         pp.setQuantite( Integer.parseInt(testquantite.getText()));
         pp.setDescription( testdescription.getText());
         pd.insert(pp);
          table.setItems(pd.getAllProduits());
         testnom.setText("");
          testprix.setText("");
          testquantite.setText("");
          testdescription.setText("");
      }
    public void modifier(ActionEvent actionEvent) throws SQLException {
        Produits pp = new Produits();
        pp.setId(ID); // on récupère l'ID du produit à modifier
        pp.setNom(testnom.getText());
        pp.setPrix(Float.parseFloat(testprix.getText()));
        pp.setQuantite(Integer.parseInt(testquantite.getText()));
        pp.setDescription(testdescription.getText());
        // appel de la méthode update de ProduitDAO pour mettre à jour les informations du produit dans la base de données
        pd.update(pp);
        // on rafraîchit le tableau avec les produits mis à jour
        table.setItems(pd.getAllProduits());
        // on réinitialise les champs de saisie et on désactive les boutons Modifier et Supprimer
        testnom.setText("");
        testprix.setText("");
        testquantite.setText("");
        testdescription.setText("");
    }
      public void supprimer(ActionEvent actionEvent) throws SQLException {
          pd.delete(ID);
          table.setItems(pd.getAllProduits());
          testnom.setText("");
          testprix.setText("");
          testquantite.setText("");
          testdescription.setText("");
      }
    @FXML
    public void ClickTable(MouseEvent mouseEvent) {
        // Obtenir le produit sélectionné dans le tableau
        Produits produits = (Produits) table.getSelectionModel().getSelectedItem();
        // Vérifier si un produit a été sélectionné
        if (produits != null) {
            // Afficher les informations du produit sélectionné dans les champs correspondants
            testnom.setText(produits.getNom());
            testprix.setText(produits.getPrix() + "");
            testquantite.setText(produits.getQuantite() + "");
            testdescription.setText(produits.getDescription());
            // Conserver l'ID du produit sélectionné pour la modification ultérieure
            ID = produits.getId();
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
    public void quitterApplication(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colnom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        colquantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        try {
            table.setItems(pd.getAllProduits());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
