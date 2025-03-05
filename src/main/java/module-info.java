module com.example {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires org.apache.poi.ooxml.schemas;
    requires java.management;

    opens com.example to javafx.fxml;
    exports com.example;
}
