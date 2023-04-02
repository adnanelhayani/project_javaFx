package com.example.project_esalaf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
public class Home {
    @FXML
    Button btnProduit, btnClient, btnCommande, btnCredit;
    public void openProduit(ActionEvent actionEvent) throws IOException {
        Node node =(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Home.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Produits.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openClient(ActionEvent actionEvent) throws IOException {
        Node node =(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Home.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Clients.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openCommande(ActionEvent actionEvent) throws IOException {
        Node node =(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Home.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Commandes.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void openCredit(ActionEvent actionEvent) throws IOException {
        Node node =(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Home.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Credits.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}