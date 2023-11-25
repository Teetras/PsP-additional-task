package com.example;


import com.example.entity.User;
import com.example.service.UserService;
import com.example.service.serviceImpl.UserServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // Создаем пользователя
        User user = new User();
        user.setLogin("user");
        user.setPassword("user");
        user.setRole("user");

        // Сохраняем пользователя в базе данных или выполняем другие необходимые операции
        UserService userService = new UserServiceImpl();
        userService.addUser(user);

        // Запускаем JavaFX-приложение
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Создаем графический интерфейс JavaFX
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo1/hello-view.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 1000, 700));
        primaryStage.show();
    }
}
