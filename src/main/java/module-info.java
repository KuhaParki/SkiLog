module com.github.kuhaparkigithub.skilog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.kuhaparkigithub.skilog to javafx.fxml;
    exports com.github.kuhaparkigithub.skilog;
}