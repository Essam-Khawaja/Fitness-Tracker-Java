module MainFiles {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens MainFiles to javafx.fxml;
    exports MainFiles;
}