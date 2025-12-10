package farmingdale.collocate;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.ServiceOptions;
import com.google.firebase.*;
import com.google.firebase.cloud.*;
import java.io.FileInputStream;
import java.io.IOException;

public class FirestoreContext {

    public Firestore firebase() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("src/main/resources/farmingdale/collocate/key.json");


            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return FirestoreClient.getFirestore();
    }
}
