package farmingdale.collocate;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class TesterPerson {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    // --- Added starredProjects list here ---
    private ArrayList<String> recentProjects = new ArrayList<>();
    private ArrayList<String> starredProjects = new ArrayList<>(); // NEW
    private ArrayList<String> recentClients = new ArrayList<>();
    private ArrayList<String> allProjects = new ArrayList<>();

    private Project db;

    public TesterPerson() {
        this.db = new Project();
    }

    public TesterPerson(String firstName, String lastName, String email, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.db = new Project();
        generateDummyData();
    }

    // ------------------- Getters for Lists -------------------

    public ArrayList<String> getRecentProjects() { return recentProjects; }

    // --- Added Getter for Starred Projects ---
    public ArrayList<String> getStarredProjects() { return starredProjects; }

    public ArrayList<String> getRecentClients() { return recentClients; }

    public ArrayList<String> getAllProjects() { return allProjects; }

    private void generateDummyData() {
        // 1. Recent Projects
        recentProjects.add("Website Redesign");
        recentProjects.add("Mobile App v2.0");
        recentProjects.add("Database Migration");

        // 2. All Projects (Add Recent + New ones)
        allProjects.addAll(recentProjects);
        allProjects.add("Legacy System Cleanup");
        allProjects.add("HR Portal Internal");
        allProjects.add("Q3 Financial Audit");
        allProjects.add("Inventory Management System");
        allProjects.add("Cloud Server Setup");
        allProjects.add("Marketing Campaign 2024");

        // 3. Starred Projects (NEW DATA)
        starredProjects.add("Mobile App v2.0");
        starredProjects.add("Inventory Management System");

        // 4. Recent Clients
        recentClients.add("Acme Corp");
        recentClients.add("Stark Industries");
        recentClients.add("Wayne Enterprises");
    }

    public boolean isValidEmail() {
        if (this.email == null) return false;
        return Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(this.email).matches();
    }

    public boolean verifyPassword(String inputPassword) {
        return this.password != null && this.password.equals(inputPassword);
    }

    // ------------------- Standard Getters/Setters -------------------

    public Project getDb() { return db; }
    public void setDb(Project db) { this.db = db; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}