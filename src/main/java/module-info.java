module farmingdale.collocate {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.google.auth;
    requires com.google.auth.oauth2;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires google.cloud.firestore;
    requires firebase.admin;
    requires com.google.api.apicommon;
    requires google.api.client;
    requires java.sql;
    requires google.cloud.core;

    opens farmingdale.collocate to javafx.fxml;
    exports farmingdale.collocate;
}