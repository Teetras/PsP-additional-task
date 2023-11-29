module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    // Экспорт пакета com.example для модуля javafx.graphics

    requires java.naming;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    opens com.example.demo1.entity to javafx.controls, org.hibernate.orm.core, javafx.base;
    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
    exports com.example;
    opens com.example to javafx.fxml;
}