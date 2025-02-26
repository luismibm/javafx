module com.luismibm.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    opens com.luismibm.javafx to javafx.fxml;
    exports com.luismibm.javafx;
}