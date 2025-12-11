package farmingdale.collocate;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String orgName;
    private String pass;

    public User() {
        firstName = "None";
        lastName = "None";
        email = "None";
        orgName = "None";
        pass = "None";
    }

    public User(String firstName, String lastName, String email, String orgName, String pass) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.orgName = orgName;
        this.pass = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setNewEmail(String email) {
        this.email = email;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFullName() {
        return this.firstName + this.lastName;
    }
}
