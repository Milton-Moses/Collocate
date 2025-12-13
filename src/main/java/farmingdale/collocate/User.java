package farmingdale.collocate;

import java.util.ArrayList;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String orgName;
    private String pass;
    private ArrayList<Project> projectList = new ArrayList<Project>();
    private ArrayList<Project> starredProjectList = new ArrayList<Project>();
    private ArrayList<Client> clientList = new ArrayList<Client>();

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
        return this.firstName + " " + this.lastName;
    }

    public ArrayList<String> getAllProjects() {
        ArrayList<String> projectNames = new ArrayList<String>();
        if (this.projectList.isEmpty()) {
            return null;
        }
        else {
            for (Project project : this.projectList) {
                projectNames.add(project.getProjectName());
            }
            return projectNames;
        }

    }

    public void addProject(Project project) {
        this.projectList.add(project);
    }

    public Project getProjectByName(String name) {
        for (Project project : projectList) {
            if (project.getProjectName().equals(name)) {
                return project;
            }
        }
        return null;
    }
    public ArrayList<Project> getAllProjectsAsSource() {
        return this.projectList;
    }

    public ArrayList<String> getStarredProjects() {
        ArrayList<String> starredProjectNames = new ArrayList<String>();
        if (this.starredProjectList.isEmpty()) {
            return null;
        }
        else {
            for (Project project : this.starredProjectList) {
                starredProjectNames.add(project.getProjectName());
            }
            return starredProjectNames;
        }
    }

    public void addStarredProject(Project project) {
        this.starredProjectList.add(project);
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public ArrayList<String> getClientList() {
        ArrayList<String> clientNames = new ArrayList<String>();
        if (this.clientList.isEmpty()) {
            return null;
        }
        else {
            for (Client client : this.clientList) {
                clientNames.add(client.getName());
            }
            return clientNames;
        }
    }
    public ArrayList<Client> getClientListAsSource() {
        return this.clientList;
    }

    public Client getClientByName(String name) {
        for (Client client : clientList) {
            if (client.getName().equals(name)) {
                return client;
            }
        }
        return null;
    }
}
