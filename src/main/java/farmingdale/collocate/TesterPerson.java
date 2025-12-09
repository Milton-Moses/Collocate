package farmingdale.collocate;

public class TesterPerson {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;



    public TesterPerson() {

        this.username = "Atester";
        this.password = "Btester";
        this.email = "something@gmail.com";
        this.firstName = "Ctester";
        this.lastName = "Dtester";
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
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
