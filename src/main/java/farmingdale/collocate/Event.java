package farmingdale.collocate;

public class Event {
    private String date;
    private String time;
    private Client associatedClient;

    public Event() {
        date = "0/0/2000";
        time = "12:00am";
        associatedClient = null;
    }

    public Event(String date, String time, Client associatedClient) {
        this.date = date;
        this.time = time;
        this.associatedClient = associatedClient;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Client getAssociatedClient() {
        return this.associatedClient;
    }

    public void setClient(Client associatedClient) {
        this.associatedClient = associatedClient;
    }
}
