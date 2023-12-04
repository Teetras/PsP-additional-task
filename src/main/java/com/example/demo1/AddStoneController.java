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
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class AddStoneController {
    private User user = null;
    MenuFunctions menuFunctions = new MenuFunctions();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameField;

    @FXML
    private TextField transparencyField;

    @FXML
    private TextField valueField;

    @FXML
    private TextField weightField1;

    @FXML
    void showNewPage(ActionEvent event, User user) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.hide();

        // Создание и настройка лоадера
        ProgressIndicator loaderIndicator = new ProgressIndicator();
        loaderIndicator.setProgress(-1);
        StackPane loaderPane = new StackPane(loaderIndicator);
        loaderPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
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
    void initialize() {

    }
    public void addStone(ActionEvent actionEvent) {// Добавление камня и проверки на корректное ее создание
        String name = nameField.getText();
        String weightText = weightField1.getText();
        String transparencyText = transparencyField.getText();
        String valueText = valueField.getText();

        if (name.isEmpty() || weightText.isEmpty() || transparencyText.isEmpty() || valueText.isEmpty()) {
            showAlert("Ошибка", "Заполните все поля", Alert.AlertType.ERROR);
        } else {
            try {
                int weight = Integer.parseInt(weightText);
                double transparency = Double.parseDouble(transparencyText.replace(",", "."));
                double value = Double.parseDouble(valueText.replace(",", "."));

                if (transparency < 0 || transparency >= 1) {
                    showAlert("Ошибка", "Прозрачность должна быть в диапазоне от 0 до 1", Alert.AlertType.ERROR);
                } else {
                    if (menuFunctions.addGem(name, transparency, value, weight)) {
                        showNewPage(actionEvent, user);
                    }
                }

            } catch (NumberFormatException e) {
                showAlert("Ошибка", "Некорректный формат числа", Alert.AlertType.ERROR);
            }
        }
    }
    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
