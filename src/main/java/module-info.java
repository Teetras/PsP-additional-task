module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires lombok;
    requires org.hibernate.orm.core;


    opens com.example.demo1 to javafx.fxml;
    exports com.example.demo1;
}