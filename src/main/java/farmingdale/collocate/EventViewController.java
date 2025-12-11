package farmingdale.collocate;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static farmingdale.collocate.Main.currUser;
import static farmingdale.collocate.Main.fstore;

public class EventViewController {

    @FXML private VBox eventListContainer;
    @FXML private TextField quickAddInput;
    @FXML private Label subtitleLabel;

    private List<EventItem> events = new ArrayList<>();
    private String currentProject;

    @FXML
    public void initialize() {
        ApiFuture<QuerySnapshot> query = fstore.collection("eventList").get();
        try {
            QuerySnapshot querySnapshot = query.get();
            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();

            for (QueryDocumentSnapshot document : documents) {
                if (document.contains(currUser.getEmail())) {
                    System.out.println("Found document belonging to " + currUser.getEmail());
                    events.add(new EventItem(document.get("title").toString(), LocalDate.now(), (boolean) document.get("isCompleted")));
                }
            }

            /*
            events.add(new EventItem("Initial Client Meeting", LocalDate.now().minusDays(1), true));
            events.add(new EventItem("Draft Architecture Diagram", LocalDate.now().plusDays(2), false));
            events.add(new EventItem("Submit Budget Proposal", LocalDate.now().plusDays(5), false));
            events.add(new EventItem("Team Kickoff", LocalDate.now().plusDays(7), false));
            */
            renderEvents();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Failed to load files from database.");
        }
    }

    /**
     * Called by the Parent ProjectView to filter events for a specific project
     */
    public void loadEventsFor(String projectName) {
        this.currentProject = projectName;
        System.out.println("Loading events for: " + projectName);
        renderEvents();
    }

    @FXML
    private void handleQuickAdd() {
        String title = quickAddInput.getText().trim();
        if (!title.isEmpty()) {
            events.add(new EventItem(title, LocalDate.now(), false));
            quickAddInput.clear();
            renderEvents();
        }
    }

    /**
     * Clears the list and rebuilds the UI from the data list.
     * @author Milton Moses
     */
    private void renderEvents() {
        eventListContainer.getChildren().clear();
        int pendingCount = 0;

        for (EventItem item : events) {
            if (!item.isCompleted) pendingCount++;

            HBox card = createEventCard(item);
            eventListContainer.getChildren().add(card);
        }

        subtitleLabel.setText(pendingCount + " tasks pending");
    }

    /**
     * Programmatically creates the UI for a single event row.
     */
    private HBox createEventCard(EventItem item) {
        HBox card = new HBox();
        card.setAlignment(Pos.CENTER_LEFT);
        card.setSpacing(15);
        card.getStyleClass().add(item.isCompleted ? "event-card-completed" : "event-card");

        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(item.isCompleted);
        checkBox.setOnAction(e -> {
            item.isCompleted = checkBox.isSelected();
            renderEvents();
        });

        VBox details = new VBox();
        details.setAlignment(Pos.CENTER_LEFT);

        Label titleLbl = new Label(item.title);
        titleLbl.getStyleClass().add("event-title");

        Label dateLbl = new Label("Due: " + item.date.toString());
        dateLbl.getStyleClass().add("event-date");

        details.getChildren().addAll(titleLbl, dateLbl);

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button delBtn = new Button("✕");
        delBtn.getStyleClass().add("icon-button");
        delBtn.setOnAction(e -> {
            events.remove(item);
            renderEvents();
        });

        card.getChildren().addAll(checkBox, details, spacer, delBtn);
        return card;
    }

    private static class EventItem {
        String title;
        LocalDate date;
        boolean isCompleted;

        public EventItem(String title, LocalDate date, boolean isCompleted) {
            this.title = title;
            this.date = date;
            this.isCompleted = isCompleted;
        }
    }
}