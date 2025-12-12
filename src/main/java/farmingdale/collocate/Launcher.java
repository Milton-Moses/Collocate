package farmingdale.collocate;

import javafx.application.Application;

import java.io.FileInputStream;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("ServiceAccount");
        FileInputStream ServiceAccount = (FileInputStream) Launcher.class.getResourceAsStream("/key.json");

        Application.launch(Main.class, args);
    }

}
