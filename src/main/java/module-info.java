module com.example.snake_and_ladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snake_and_ladder to javafx.fxml;
    exports com.example.snake_and_ladder;
}