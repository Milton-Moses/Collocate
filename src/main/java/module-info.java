module farmingdale.collocate {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.google.auth.oauth2;
    requires google.cloud.firestore;
    requires firebase.admin;

    opens farmingdale.collocate to javafx.fxml;
    exports farmingdale.collocate;
}