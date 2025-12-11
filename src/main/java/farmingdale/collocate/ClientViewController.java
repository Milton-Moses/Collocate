package farmingdale.collocate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ClientViewController {
    @FXML
    private Button newClientButton, editClientButton, deleteClientButton;

    @FXML
    private ListView<String> clientListView;

    @FXML
    public static void init() {

    }
}
