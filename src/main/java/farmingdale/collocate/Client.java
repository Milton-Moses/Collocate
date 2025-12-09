package farmingdale.collocate;

public class Client {
    private String name;
    private String type;

    public Client(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public String getType() { return type; }

    @Override
    public String toString() { return name; } // Important for Dropdowns
}