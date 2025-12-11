package farmingdale.collocate;

import java.util.ArrayList;


public class Project {

    private ArrayList<Client> clients;
    private ArrayList<Event> events;

    public Project() {
        this.clients = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void addEvent(Event event) {
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        this.events.remove(event);
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public boolean hasClients() {
        return !clients.isEmpty();
    }
}