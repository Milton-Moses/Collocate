package farmingdale.collocate;

import java.util.ArrayList;
import java.util.List;

/**
 * Acts as the central data container for a User.
 * References:
 * - Section 4.4: Stores 'Events' for the Calendar.
 * - Section 4.5: Stores 'Clients' for the Client View.
 * - Section 4.8: The "state of the user's database" for AI suggestions.
 */
public class Project {

    // Lists to hold the data required by Sections 4.4 and 4.5
    private ArrayList<Client> clients;
    private ArrayList<Event> events;

    public Project() {
        this.clients = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    // ------------------- Client Management (Section 4.5) -------------------

    /**
     * Adds a new client to the system.
     * Used by the "New Client View" (Section 4.6).
     */
    public void addClient(Client client) {
        this.clients.add(client);
    }

    /**
     * Removes a client from the system.
     * Used by the "Client View" (Section 4.5.1).
     */
    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    // ------------------- Event Management (Section 4.4) -------------------

    /**
     * Adds a new event to the calendar.
     * Used by "Create Event View" (Section 4.4).
     */
    public void addEvent(Event event) {
        this.events.add(event);
    }

    /**
     * Removes an event.
     * Used by "Schedule View" (Section 4.3.2) -> "delete them entirely".
     */
    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    // ------------------- Helper Methods -------------------

    /**
     * Helper to check if any clients exist.
     * Essential for Section 4.4.2: "If the user does not have any inputted clients,
     * they will receive an error message".
     */
    public boolean hasClients() {
        return !clients.isEmpty();
    }
}