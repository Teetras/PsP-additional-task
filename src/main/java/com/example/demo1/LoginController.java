package com.example.demo1;

import com.example.demo1.MenuFunctions.MenuFunctions;
import com.example.demo1.entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController {
    MenuFunctions menuFunctions = new MenuFunctions();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    void showNewPage(ActionEvent event, User user) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/demo1/NecklaceView.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root = loader.getRoot();
        NecklaceController controller = loader.getController();
        controller.setUser(user);
        controller.initialize();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Ошибка", "Заполните все поля", Alert.AlertType.ERROR);
        } else {
            User user = menuFunctions.findUserByLogin(username);
            if (user == null) {
                showAlert("Ошибка", "Неправильный логин или пароль", Alert.AlertType.ERROR);
            } else {
                if (password.equals(user.getPassword())) {
                    showNewPage(event, user);
                } else {
                    showAlert("Ошибка", "Неправильный логин или пароль", Alert.AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    void initialize() {

    }


    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
