package farmingdale.collocate;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static farmingdale.collocate.Main.currProject;
import static farmingdale.collocate.Main.fstore;

public class EventViewController {

    @FXML private VBox eventListContainer;
    @FXML private TextField quickAddInput;
    @FXML private Label subtitleLabel;


    private List<EventItem> events = new ArrayList<>();

    @FXML
    public void initialize() {
            /*
            events.add(new EventItem("Initial Client Meeting", LocalDate.now().minusDays(1), true));
            events.add(new EventItem("Draft Architecture Diagram", LocalDate.now().plusDays(2), false));
            events.add(new EventItem("Submit Budget Proposal", LocalDate.now().plusDays(5), false));
            events.add(new EventItem("Team Kickoff", LocalDate.now().plusDays(7), false));
            */
            renderEvents();
    }

    /**
     * Called by the Parent ProjectView to filter events for a specific project
     */
    public void loadEventsFor(Project project) {
        System.out.println("Loading events for: " + project.getProjectName());
        renderEvents();
    }

    @FXML
    private void handleQuickAdd() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/farmingdale/collocate/ProjectView/new-project-event.fxml"));

        Scene newScene = new Scene(fxmlLoader.load(), 600, 400);
        Stage newStage = new Stage();
        newStage.setTitle("Create Event");
        newStage.setScene(newScene);
        newStage.showAndWait();
        quickAddInput.clear();
        renderEvents();

    }

    /**
     * Clears the list and rebuilds the UI from the data list.
     * @author Milton Moses
     */
    private void renderEvents() {
        eventListContainer.getChildren().clear();
        events.clear();
        int pendingCount = 0;

        for (Event event : currProject.getEvents()) {
            events.add(new EventItem(event.getEventName(), event.getDate(), false));
        }

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
            for (Event event : currProject.getEvents()) {
                if (event.getEventName().equals(item.title)) {
                    currProject.removeEvent(event);
                    try {
                        deleteEvent(item.title);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                }
            }
            events.remove(item);
            renderEvents();
        });

        card.getChildren().addAll(checkBox, details, spacer, delBtn);
        return card;
    }

    private void deleteEvent(String eventTitle) throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> eventsQuery = fstore.collection("eventList").get();
        QuerySnapshot projectsQuerySnapshot = eventsQuery.get();
        List<QueryDocumentSnapshot> eventsDocuments = projectsQuerySnapshot.getDocuments();

        for (QueryDocumentSnapshot document : eventsDocuments) {
            if (document.get("title").equals(eventTitle)) {
                ApiFuture<WriteResult> eventToDelete = fstore.collection("eventList").document(document.getId()).delete();
            }
        }
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