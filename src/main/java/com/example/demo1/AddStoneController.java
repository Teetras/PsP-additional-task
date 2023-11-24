package com.example.demo1;

import javafx.event.ActionEvent;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddStoneController {

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
    void initialize() {

    }
    public void addStone(ActionEvent actionEvent) {
        String name = nameField.getText();
        String weightText = weightField1.getText();
        String transparencyText = transparencyField.getText();
        String valueText = valueField.getText();

        if (name.isEmpty() || weightText.isEmpty() || transparencyText.isEmpty() || valueText.isEmpty()) {
            showAlert("Ошибка", "Заполните все поля", Alert.AlertType.ERROR);
        } else {
            try {
                double weight = Double.parseDouble(weightText);
                int transparency = Integer.parseInt(transparencyText);
                double value = Double.parseDouble(valueText);

                // Логика добавления камня
                // ...
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
