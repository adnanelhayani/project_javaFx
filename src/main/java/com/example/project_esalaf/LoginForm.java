package com.example.project_esalaf;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
public class LoginForm {
    @FXML
    public TextField textUser;
   public PasswordField testPass;
    public Button btnSign;
    public Button btnSignup;
   public Label lblErr;
    admin adm=new admin();
     AdminDAO lg=new AdminDAO();
    public LoginForm() {
        textUser = new TextField();
        testPass = new PasswordField();
        lblErr = new Label();
    }

    public void isSign(ActionEvent actionEvent) throws SQLException, IOException {
        adm.setUsername(textUser.getText());
        adm.setPassword(Integer.parseInt(testPass.getText()));
        if(lg.isLogin(adm)){
            Node node =(Node) actionEvent.getSource();
            Stage stage=(Stage) node.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Home.fxml"));
            Parent root =FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Home.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            lblErr.setText("le prenom ou le mot de passe incorrect");
        }
    }
    public void openNewAdmin(ActionEvent actionEvent) throws IOException {
        Node node =(Node) actionEvent.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
//            Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/Home.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/project_esalaf/LoginFormNew.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
