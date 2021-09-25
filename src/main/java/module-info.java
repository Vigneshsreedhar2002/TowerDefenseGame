module com.example.judym2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.judym2 to javafx.fxml;
    exports com.example.judym2;
    exports com.example.judym2.controllers;
    opens com.example.judym2.controllers to javafx.fxml;
}