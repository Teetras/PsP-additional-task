package com.example.demo1;

import com.example.demo1.MenuFunctions.MenuFunctions;
import com.example.demo1.entity.User;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
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

        // Создание и настройка лоадера
        ProgressIndicator loaderIndicator = new ProgressIndicator();
        loaderIndicator.setProgress(-1);
        StackPane loaderPane = new StackPane(loaderIndicator);
        loaderPane.setStyle("-fx-background-color: rgba(255,255,255,0);");
        Scene loaderScene = new Scene(loaderPane, 200, 200);
        Stage loaderStage = new Stage();
        loaderStage.initModality(Modality.APPLICATION_MODAL);
        loaderStage.initOwner(currentStage);
        loaderStage.setScene(loaderScene);

        // Запуск фонового потока для выполнения длительной операции
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                // Имитация длительной операции
                Thread.sleep(2000);
                return null;
            }
        };

        // Показать лоадер и выполнить операцию в фоновом потоке
        loaderStage.show();
        Thread thread = new Thread(task);
        thread.start();

        // Ожидание завершения операции и отображение следующей страницы
        task.setOnSucceeded(e -> {
            loaderStage.hide(); // Скрыть лоадер после завершения операции

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/example/demo1/NecklaceView.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Parent root = loader.getRoot();
            NecklaceController controller = loader.getController();
            controller.setUser(user);
            controller.initialize();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    @FXML
    void onLoginButtonClick(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Ошибка", "Заполните все поля", Alert.AlertType.ERROR);
        } else {
            try {
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
            } catch (Exception e) {
                e.printStackTrace();
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
