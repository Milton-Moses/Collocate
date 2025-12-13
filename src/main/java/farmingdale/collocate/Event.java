package farmingdale.collocate;

import java.time.LocalDate;

public class Event {
    private String eventName;
    private String associatedProject;
    private LocalDate date;

    public Event(String eventName, String project, LocalDate date) {
        this.eventName = eventName;
        this.associatedProject = project;
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public String getAssociatedProject() {
        return associatedProject;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return eventName + " (" + date + ")";
    }
}