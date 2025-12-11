package farmingdale.collocate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.*;
import com.google.cloud.firestore.*;

import com.google.firebase.*;

public class Main extends Application {
    public static Scene scene;
    public static Stage mainStage;
    public static Firestore fstore;
    public static FirebaseAuth fauth;
    public static final FirestoreContext contextFirebase = new FirestoreContext();
    public static User currUser;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        mainStage = stage;
        scene = new Scene(fxmlLoader.load(), 1080, 720);

        fstore = contextFirebase.firebase();
        mainStage.setTitle("Collocate");
        mainStage.setScene(scene);
        mainStage.show();
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        /*
        ArrayList<TesterPerson> allUsers = DataSeeder.generateUsers(20);

        for (TesterPerson p : allUsers) {
            System.out.println("User: " + p.getUsername() + " | Password: " + p.getPassword());
            System.out.println("   -> Clients: " + p.getDb().getClients().size());
            System.out.println("   -> Events: " + p.getDb().getEvents().size());
        }*/
        launch();
    }
}
