package farmingdale.collocate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class DataSeeder {

    // Arrays of dummy data to mix and match
    private static final String[] FIRST_NAMES = {"Alice", "Bob", "Charlie", "Diana", "Ethan", "Fiona", "George", "Hannah", "Ian", "Julia"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis", "Rodriguez", "Martinez"};
    private static final String[] CLIENT_TYPES = {"Individual", "Business", "Non-Profit", "Government", "Educational"};
    private static final String[] CLIENT_NAMES = {"TechCorp", "Mom & Pop Store", "City High School", "Global Logistics", "Local Bakery", "Consulting Firm"};
    private static final String[] EVENT_TYPES = {"Meeting", "Consultation", "Deadline", "Project Review", "Site Visit", "Audit"};

    /**
     * Generates a list of users with populated databases.
     * @param count How many users you want.
     * @return An ArrayList of TesterPerson objects.
     */
    public static ArrayList<TesterPerson> generateUsers(int count) {
        ArrayList<TesterPerson> users = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            // 1. Create the Basic User
            String fName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
            String lName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];

            // NEW: Generate a more realistic random username
            String username = generateRandomUsername(fName, lName, random);

            String email = username + "@example.com";

            // Everyone gets the password "password123" for easy testing
            TesterPerson user = new TesterPerson(fName, lName, email, username, "password123");

            // 2. Populate their Project (The internal DB)
            populateUserProject(user.getDb(), random);

            users.add(user);
        }

        // Always add a specific Admin/Test account so you don't have to guess
        TesterPerson admin = new TesterPerson("Admin", "User", "admin@collocate.com", "admin", "admin");
        populateUserProject(admin.getDb(), random);
        users.add(0, admin); // Put admin at the top

        return users;
    }

    /**
     * Helper to create varied username styles
     */
    private static String generateRandomUsername(String fName, String lName, Random rand) {
        int style = rand.nextInt(4); // Pick one of 4 styles

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
     */
    private static void populateUserProject(Project project, Random random) {
        // A. Add 3 to 6 Random Clients
        int clientCount = random.nextInt(4) + 3;
        ArrayList<Client> tempClientList = new ArrayList<>();

        for (int j = 0; j < clientCount; j++) {
            String cName = CLIENT_NAMES[random.nextInt(CLIENT_NAMES.length)] + " " + (char)('A' + j);
            String cType = CLIENT_TYPES[random.nextInt(CLIENT_TYPES.length)];

            Client newClient = new Client(cName, cType);
            project.addClient(newClient);
            tempClientList.add(newClient);
        }

        // B. Add 5 to 10 Random Events linked to those Clients
        int eventCount = random.nextInt(6) + 5;

        for (int k = 0; k < eventCount; k++) {
            // Pick a random client from the list we just made
            Client linkedClient = tempClientList.get(random.nextInt(tempClientList.size()));

            String eName = EVENT_TYPES[random.nextInt(EVENT_TYPES.length)] + " with " + linkedClient.getName();

            // Random date within the next 30 days
            LocalDate eDate = LocalDate.now().plusDays(random.nextInt(30));

            Event newEvent = new Event(eName, linkedClient, eDate);
            project.addEvent(newEvent);
        }
    }
}