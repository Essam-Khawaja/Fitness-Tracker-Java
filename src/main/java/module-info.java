module MainFiles {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens MainFiles to javafx.fxml;
    exports MainFiles;
}