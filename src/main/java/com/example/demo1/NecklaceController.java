package com.example.demo1;

import com.example.demo1.MenuFunctions.MenuFunctions;
import com.example.demo1.MenuFunctions.NecklaceCreator;
import com.example.demo1.entity.Gem;
import com.example.demo1.entity.Necklace;
import com.example.demo1.entity.NecklaceGem;
import com.example.demo1.entity.User;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Getter
@Setter
public class NecklaceController {
    private User user = null;

    final String ADMIN_CHECK = "admin";

    MenuFunctions menuFunctions = new MenuFunctions();
    NecklaceCreator necklaceCreator = new NecklaceCreator();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TableColumn<Gem, Double> weidth_gem;

    @FXML
    private TableColumn<Gem, String> name_gem;

    @FXML
    private TableColumn<Gem, Double> opacity_gem;

    @FXML
    private TableColumn<Gem, Double> price_gem;

    @FXML
    private TableView<Gem> table_gems;



    @FXML
    private TableColumn<Necklace, String> neckGems;

    @FXML
    private TableColumn<Necklace, Double> neckPrice;

    @FXML
    private TableView<Necklace> necklaceTable;

    @FXML
    private TableColumn<Necklace, String> neclaceName;


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
        String weightText = weightField.getText();
        final ChangeListener<Necklace>[] listener = new ChangeListener[]{null}; // Create a final array to hold the listener

        if (weightText.isEmpty()) {
            showAlert("Ошибка", "Введите вес ожерелья", Alert.AlertType.ERROR);
        } else {
            try {
                double weight = Double.parseDouble(weightText);

                if (weight <= 0) {
                    showAlert("Ошибка", "Введите положительное значение веса ожерелья", Alert.AlertType.ERROR);
                } else {

                    necklaceTable.getItems().clear();

                    conditionTable.set(false);
                    conditionNeckTable.set(true);

                    table_gems.visibleProperty().bind(conditionTable);
                    necklaceTable.visibleProperty().bind(conditionNeckTable);
                    neckGems.setCellValueFactory(cellData ->
                            new SimpleStringProperty(necklaceCreator.printGemCounts(cellData.getValue().getNecklaceGems())));
                    neckPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
                    neclaceName.setCellValueFactory(new PropertyValueFactory<>("name"));

                    List<Necklace> necklaces = necklaceCreator.createNecklacesByWeight(Double.parseDouble(weightText), 10);
                    necklaceTable.getItems().addAll(necklaces);

                    listener[0] = (obs, oldSelection, newSelection) -> {
                        if (newSelection != null) {
                            System.out.println("Выбранный элемент: " + newSelection);

                            boolean added = user.getNecklaces().add(newSelection); // Добавляем ожерелье пользователю

                            if (added) {
                                newSelection.setUser(user);
                                boolean isAddedToDB = menuFunctions.addNecklace(newSelection); // Добавляем ожерелье в базу данных и связываем с пользователем
                                if (isAddedToDB) {
                                    System.out.println("Ожерелье добавлено в базу данных");
                                    List<NecklaceGem> necklaceGems = newSelection.getNecklaceGems();
                                    for (NecklaceGem necklaceGem : necklaceGems) {
                                        menuFunctions.addNecklaceGem(necklaceGem);
                                    }
                                } else {
                                    System.out.println("Ошибка при добавлении ожерелья в базу данных");
                                }
                            } else {
                                System.out.println("Ошибка при добавлении ожерелья пользователю");
                            }
                        }

                        // Удаляем слушателя после одного нажатия
                        necklaceTable.getSelectionModel().selectedItemProperty().removeListener(listener[0]);
                    };

                    // Добавляем слушатель к свойству selectedItemProperty()
                    necklaceTable.getSelectionModel().selectedItemProperty().addListener(listener[0]);
                }
            } catch (NumberFormatException e) {
                showAlert("Ошибка", "Некорректный формат числа", Alert.AlertType.ERROR);
            }
        }
    }
    @FXML
    void showNeck(ActionEvent event) {
        necklaceTable.getItems().clear();

        conditionTable.set(false);
        conditionNeckTable.set(true);

        table_gems.visibleProperty().bind(conditionTable);
        necklaceTable.visibleProperty().bind(conditionNeckTable);
        neckGems.setCellValueFactory(cellData ->
                new SimpleStringProperty(necklaceCreator.printGemCounts(cellData.getValue().getNecklaceGems())));
        neckPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        neclaceName.setCellValueFactory(new PropertyValueFactory<>("name"));

        List<Necklace> necklaces = user.getNecklaces();
        necklaceTable.getItems().addAll(necklaces);
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void findStonesByTransparency(ActionEvent event) {
        String maxText = transparencyMaxField.getText();
        String minText = transparencyMinField.getText();

        // Проверка на ввод и валидация диапазона прозрачности
        if (maxText.isEmpty() || minText.isEmpty()) {
            showAlert("Ошибка", "Заполните оба поля диапазона", Alert.AlertType.ERROR);
        } else {
            try {
                int maxTransparency = Integer.parseInt(maxText);
                int minTransparency = Integer.parseInt(minText);

                if (maxTransparency < 0 || minTransparency < 0) {
                    showAlert("Ошибка", "Введите положительные значения", Alert.AlertType.ERROR);
                } else if (maxTransparency < minTransparency) {
                    showAlert("Ошибка", "Максимальное значение должно быть больше или равно минимальному", Alert.AlertType.ERROR);
                } else {
                    // Очистка текущих элементов таблицы
                    table_gems.getItems().clear();

                    conditionTable.set(true);
                    conditionNeckTable.set(false);

                    table_gems.visibleProperty().bind(conditionTable);
                    necklaceTable.visibleProperty().bind(conditionNeckTable);
                    weidth_gem.setCellValueFactory(new PropertyValueFactory<>("weight"));
                    opacity_gem.setCellValueFactory(new PropertyValueFactory<>("opacity"));
                    name_gem.setCellValueFactory(new PropertyValueFactory<>("name"));
                    price_gem.setCellValueFactory(new PropertyValueFactory<>("price"));

                    List<Gem> gems = menuFunctions.showDiapasonOpacityGem(minTransparency, maxTransparency);
                    table_gems.getItems().addAll(gems);


                }
            } catch (NumberFormatException e) {
                showAlert("Ошибка", "Некорректный формат числа", Alert.AlertType.ERROR);
            }
        }
    }




    @FXML
    void sortByValue(ActionEvent event) {
        table_gems.getItems().clear();

        conditionTable.set(true);
        conditionNeckTable.set(false);

        table_gems.visibleProperty().bind(conditionTable);
        necklaceTable.visibleProperty().bind(conditionNeckTable);
        weidth_gem.setCellValueFactory(new PropertyValueFactory<>("weight"));
        opacity_gem.setCellValueFactory(new PropertyValueFactory<>("opacity"));
        name_gem.setCellValueFactory(new PropertyValueFactory<>("name"));
        price_gem.setCellValueFactory(new PropertyValueFactory<>("price"));

        List<Gem> gems = menuFunctions.showSortedByPriceGem();
        table_gems.getItems().addAll(gems);

    }

    @FXML
    void sortByWeight(ActionEvent event) {
        table_gems.getItems().clear();

        conditionTable.set(true);
        conditionNeckTable.set(false);

        table_gems.visibleProperty().bind(conditionTable);
        necklaceTable.visibleProperty().bind(conditionNeckTable);
        weidth_gem.setCellValueFactory(new
                PropertyValueFactory<>("weight"));
        opacity_gem.setCellValueFactory(new
                PropertyValueFactory<>("opacity"));
        name_gem.setCellValueFactory(new
                PropertyValueFactory<>("name"));
        price_gem.setCellValueFactory(new
                PropertyValueFactory<>("price"));

        List<Gem> gems = menuFunctions.showSortedByWeightGem();
        table_gems.getItems().addAll(gems);


    }

    private BooleanProperty condition = new SimpleBooleanProperty();
    private BooleanProperty conditionTable = new SimpleBooleanProperty();
    private BooleanProperty conditionNeckTable = new SimpleBooleanProperty();

    @FXML
    private ListView<String> necklaceList; // Замените String на тип объектов, которые вы хотите отображать в ListView

    @FXML
    void showNewPage(ActionEvent event) {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/demo1/addStone.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        AddStoneController controller = loader.getController();
        controller.setUser(user);
        controller.initialize();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    void show_Gems(ActionEvent event) {
        conditionTable.set(true);
        conditionNeckTable.set(false);

        table_gems.visibleProperty().bind(conditionTable);
        necklaceTable.visibleProperty().bind(conditionNeckTable);
        table_gems.getItems().clear();
        weidth_gem.setCellValueFactory(new PropertyValueFactory<>("weight"));
        opacity_gem.setCellValueFactory(new PropertyValueFactory<>("opacity"));
        name_gem.setCellValueFactory(new PropertyValueFactory<>("name"));
        price_gem.setCellValueFactory(new PropertyValueFactory<>("price"));

        List<Gem> gems = menuFunctions.showGem();
        table_gems.getItems().addAll(gems);

        // Добавляем слушатель события выбора элемента в таблице

    }

    public void initialize() {
        conditionTable.set(false);
        conditionNeckTable.set(false);

        table_gems.visibleProperty().bind(conditionTable);
        necklaceTable.visibleProperty().bind(conditionTable);





        // Пример условия для определения видимости кнопки
        if (getUser() != null) {
            if (user.getRole().equals(ADMIN_CHECK)) {
                condition.set(true);
            } else {
                condition.set(false);
            }
        }else{
            condition.set(false);
        }
        // Привязка свойства visible кнопки к свойству condition
        add.visibleProperty().bind(condition);
        ;
        // Пример добавления объектов в ListView при инициализации контроллера
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
