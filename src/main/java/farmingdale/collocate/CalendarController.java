package farmingdale.collocate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;

public class CalendarController {

    @FXML private Text monthYearText;
    @FXML private GridPane calendarGrid;
    @FXML private Button aiButton, logoutButton, clientButton, newEventButton;

    private YearMonth currentYearMonth;
    private String activeProjectName;

    @FXML
    public void initialize() {
        currentYearMonth = YearMonth.now();
        populateCalendar(currentYearMonth);
    }

    private void populateCalendar(YearMonth yearMonth) {
        monthYearText.setText(yearMonth.getMonth().toString() + " " + yearMonth.getYear());

        calendarGrid.getChildren().clear();
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

        int dayOfWeekOfFirst = calendarDate.getDayOfWeek().getValue() % 7; // Sunday becomes 0, Monday 1...
        int daysInMonth = yearMonth.lengthOfMonth();

        int row = 0;
        int col = dayOfWeekOfFirst;

        for (int day = 1; day <= daysInMonth; day++) {
            Button dayButton = new Button(String.valueOf(day));
            dayButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            dayButton.getStyleClass().add("calendar-day-button");
            if (LocalDate.now().equals(LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), day))) {
                dayButton.getStyleClass().add("current-day");
            }
            calendarGrid.add(dayButton, col, row);
            col++;
            if (col == 7) {
                col = 0;
                row++;
            }
        }
    }

    @FXML
    private void previousMonth() {
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }

    @FXML
    private void nextMonth() {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }

    @FXML
    private void logOut() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }

    @FXML
    private void goToAi() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ai-view.fxml"));
        Main.scene = new Scene(fxmlLoader.load(), 1080, 720);
        Main.mainStage.setScene(Main.scene);
        Main.mainStage.show();
    }

    public void loadCalendarForProject(String projectName) {
        this.activeProjectName = projectName;
        System.out.println("Calendar initializing for project: " + projectName);

        if (logoutButton != null) {
            logoutButton.setVisible(false);
        }


    }
}