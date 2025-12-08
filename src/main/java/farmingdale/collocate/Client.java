package farmingdale.collocate;

public class Client {
    private String name;
    private boolean isCompany;

    public Client() {
        name = "None";
        isCompany = false;
    }

    public Client(String name, boolean isCompany) {
        this.name = name;
        this.isCompany = isCompany;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setCompany(boolean isCompany) {
        this.isCompany = isCompany;
    }
}
