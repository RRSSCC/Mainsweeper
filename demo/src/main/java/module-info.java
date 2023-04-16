module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens saolei to javafx.fxml;
    exports saolei;
}