package farmingdale.collocate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

//Dummy Class
public class DataSeeder {

    private static final String[] FIRST_NAMES = {"Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah", "Ian", "Julia"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
    private static final String[] CLIENT_TYPES = {"Individual", "Business", "Non-Profit", "Government", "Educational"};
    private static final String[] CLIENT_NAMES = {"TechCorp", "Mom & Pop Store", "City High School", "Global Logistics", "Local Bakery", "Consulting Firm"};
    private static final String[] EVENT_TYPES = {"Meeting", "Consultation", "Deadline", "Project Review", "Site Visit", "Audit"};

    /**
     * Generates a list of users with populated databases.
     * @param count How many users you want.
     * @return An ArrayList of TesterPerson objects.
     * @author Milton Moses
     */
    public static ArrayList<TesterPerson> generateUsers(int count) {
        ArrayList<TesterPerson> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String fName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

            String username = generateRandomUsername(fName, lName, random);

            String email = username + "@example.com";

            TesterPerson user = new TesterPerson(fName, lName, email, username, "password123");

            populateUserProject(user.getDb(), random);

            users.add(user);
        }

        TesterPerson admin = new TesterPerson("Admin", "User", "admin@collocate.com", "admin", "admin");
        populateUserProject(admin.getDb(), random);
        users.add(0, admin);

        return users;
    }

    private static String generateRandomUsername(String fName, String lName, Random rand) {
        int style = rand.nextInt(4);

        switch (style) {
            case 0: // "asmith"
                return fName.substring(0, 1).toLowerCase() + lName.toLowerCase();
            case 1: // "Alice_99"
                return fName + "_" + rand.nextInt(100);
            case 2: // "smith.b"
                return lName.toLowerCase() + "." + fName.substring(0, 1).toLowerCase();
            case 3: // "Charlie1985"
                return fName + (1980 + rand.nextInt(45));
            default:
                return fName.toLowerCase() + lName.toLowerCase();
        }
    }

    /**
     * Fills a user's Project with Clients and Events
     * @author Milton Moses
     */
    private static void populateUserProject(Project project, Random random) {
        int clientCount = random.nextInt(4) + 3;
        ArrayList<Client> tempClientList = new ArrayList<>();

        for (int j = 0; j < clientCount; j++) {
            String cName = CLIENT_NAMES[random.nextInt(CLIENT_NAMES.length)] + " " + (char)('A' + j);
            String cType = CLIENT_TYPES[random.nextInt(CLIENT_TYPES.length)];

            Client newClient = new Client(cName, cType);
            project.addClient(newClient);
            tempClientList.add(newClient);
        }

        int eventCount = random.nextInt(6) + 5;

        for (int k = 0; k < eventCount; k++) {
            Client linkedClient = tempClientList.get(random.nextInt(tempClientList.size()));

            String eName = EVENT_TYPES[random.nextInt(EVENT_TYPES.length)] + " with " + linkedClient.getName();
            LocalDate eDate = LocalDate.now().plusDays(random.nextInt(30));

            Event newEvent = new Event(eName, linkedClient, eDate);
            project.addEvent(newEvent);
        }
    }
}