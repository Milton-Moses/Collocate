package farmingdale.collocate;

public class Client {
    private String name;
    private boolean isCompany;
    private String owner;
    private String projectName;

    public Client() {
        name = "None";
        isCompany = false;
        owner = "None";
        projectName = "None";
    }
    public Client(String name, boolean isCompany, String owner, String projectName) {
        this.name = name;
        this.isCompany = isCompany;
        this.owner = owner;
        this.projectName = projectName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompany() {
        return isCompany;
    }

    public void setIsCompany(boolean isCompany) {
        this.isCompany = isCompany;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() { return name; }
}