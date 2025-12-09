package farmingdale.collocate;

import java.time.LocalDate;

public class Event {
    private String eventName;
    private Client associatedClient;
    private LocalDate date;

    public Event(String eventName, Client client, LocalDate date) {
        this.eventName = eventName;
        this.associatedClient = client;
        this.date = date;
    }

    public String getEventName() {
        return eventName;
    }

    public Client getAssociatedClient() {
        return associatedClient;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return eventName + " (" + date + ")";
    }
}