module com.example.judym2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.judy to javafx.fxml;
    exports com.example.judy;
    exports com.example.judy.controllers;
    opens com.example.judy.controllers to javafx.fxml;
}