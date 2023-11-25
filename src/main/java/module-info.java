module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires lombok;
    requires org.hibernate.orm.core;

    // Экспорт пакета com.example для модуля javafx.graphics
    exports com.example to javafx.graphics;

    requires java.naming;
    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}