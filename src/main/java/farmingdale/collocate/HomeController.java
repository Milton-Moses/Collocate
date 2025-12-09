package farmingdale.collocate;


import javafx.fxml.FXML;

import javafx.scene.text.Text;

public class HomeController {

    @FXML
    private Text GreetingText;

    @FXML
    public void initialize() {
        TesterPerson myUser = new TesterPerson();

        String fullName = myUser.getUsername();
        GreetingText.setText("Hello, " + fullName);

    }
}