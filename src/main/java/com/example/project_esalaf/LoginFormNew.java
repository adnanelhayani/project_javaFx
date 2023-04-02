package com.example.project_esalaf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
public class LoginFormNew {
    @FXML
    public TextField textUser;
    public PasswordField testPass;
    public Button btnSign;
    public Label lblErr;
    admin adm=new admin();
    AdminDAO lg=new AdminDAO();
    public LoginFormNew() {
        textUser = new TextField();
        testPass = new PasswordField();
        lblErr = new Label();
    }
    public boolean addAdmin(admin adm) throws SQLException {
        Statement stm = connectionDB.openConnection().createStatement();
        String query = "INSERT INTO admin (username, password) VALUES ('" + adm.getUsername() + "', '" + adm.getPassword() + "')";
        int rowsInserted = stm.executeUpdate(query);
        if (rowsInserted > 0) {
            return true;
        }
        return false;
    }
@FXML
public void handleAddAdmin(ActionEvent event) throws SQLException, IOException {
    // Création d'un nouvel objet admin
    admin adm = new admin(textUser.getText(), Integer.parseInt(testPass.getText()));
    boolean result =addAdmin(adm);
    if (result) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Administrateur ajouté avec succès !");
        alert.showAndWait();
        Node node =(Node) event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/LoginForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText("Erreur lors de l'ajout de l'administrateur !");
        alert.showAndWait();
    }
}
    public void loginAdmin(ActionEvent actionEvent) throws IOException {
        Node node =(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/LoginForm.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}


