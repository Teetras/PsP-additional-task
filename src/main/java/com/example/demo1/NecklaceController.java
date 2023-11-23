package com.example.demo1;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NecklaceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private TextField transparencyMaxField;

    @FXML
    private TextField transparencyMinField;

    @FXML
    private Button add;
    @FXML
    private TextField weightField;

    @FXML
    void createNecklace(ActionEvent event) {

    }

    @FXML
    void findStonesByTransparency(ActionEvent event) {

    }



    @FXML
    void sortByValue(ActionEvent event) {

    }

    @FXML
    void sortByWeight(ActionEvent event) {

    }
    private BooleanProperty condition = new SimpleBooleanProperty();

    @FXML
    private ListView<String> necklaceList; // Замените String на тип объектов, которые вы хотите отображать в ListView
    @FXML
    void showNewPage(ActionEvent event){
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.hide();
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/demo1/addStone.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Parent root =loader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    public void initialize() {
        // Пример условия для определения видимости кнопки
        condition.set(true);

        // Привязка свойства visible кнопки к свойству condition
        add.visibleProperty().bind(condition);;
        // Пример добавления объектов в ListView при инициализации контроллера
        necklaceList.getItems().addAll("Объект 1", "Объект 2", "Объект 3");
    }
    @FXML
    private void showHelp(ActionEvent event) {
        String helpMessage = "Добро пожаловать в программу для работы с ожерельем!\n\n"
                + "1. Введите вес ожерелья и нажмите кнопку 'Создать ожерелье'.\n"
                + "2. Будет создано ожерелье из камней, с суммарным весом, равным заданному.\n"
                + "3. Если на заданный вес невозможно подобрать ожерелье, будет показано сообщение об ошибке.\n"
                + "4. Общая стоимость ожерелья будет отображена ниже списка камней.\n"
                + "5. Используйте кнопки 'Сортировка по ценности' и 'Сортировка по весу' для сортировки камней ожерелья.\n"
                + "6. Введите диапазон прозрачности и нажмите кнопку 'Найти камни', чтобы найти камни в ожерелье,\n"
                + "   соответствующие заданному диапазону параметров прозрачности.\n"
                + "7. Наслаждайтесь работой с программой!\n";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Справка");
        alert.setHeaderText("Описание функциональности программы");
        alert.setContentText(helpMessage);
        alert.showAndWait();
    }
}
