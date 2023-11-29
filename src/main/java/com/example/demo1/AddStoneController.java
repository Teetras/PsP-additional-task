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
import javafx.scene.control.TextField;
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
                double transparency = Double.parseDouble(transparencyText);
                double value = Double.parseDouble(valueText);

                if( menuFunctions.addGem(name, transparency, value , weight)){
                  showNewPage(actionEvent, user);
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
