import java.util.InputMismatchException;
import java.util.Scanner;

public class AfterDisaster {

    private final Scanner scanner = new Scanner(System.in);
    private final Survivor[] survivors = new Survivor[100];
    private int count = 0;

    // === Encapsulation: Survivor class with private fields and getters/setters ===
    private class Survivor {
        private final String name;
        private final String location;
        private final String healthStatus;
        private final String assistanceNeeded;

        public Survivor(String name, String location, String healthStatus, String assistanceNeeded) {
            this.name = name;
            this.location = location;
            this.healthStatus = healthStatus;
            this.assistanceNeeded = assistanceNeeded;
        }

        public String getName() { return name; }
        public void display() {
            System.out.println("-----------------------------");
            System.out.println("Name: " + name);
            System.out.println("Location: " + location);
            System.out.println("Health Status: " + healthStatus);
            System.out.println("Assistance Needed: " + assistanceNeeded);
        }
    }

    // === Abstraction, Inheritance, Polymorphism: DisasterGuide abstract class and subclasses ===
    private abstract class DisasterGuide {
        public abstract void displayGuide();
    }

    private class EarthquakeGuide extends DisasterGuide {
        @Override
        public void displayGuide() {
            System.out.println("\n AFTER EARTHQUAKE GUIDE ");
            System.out.println("- Check for injuries.");
            System.out.println("- Evacuate damaged structures.");
            System.out.println("- Avoid using candles.");
            System.out.println("------------------------------------------------------");
        }
    }

    private class TyphoonGuide extends DisasterGuide {
        @Override
        public void displayGuide() {
            System.out.println("\n AFTER TYPHOON GUIDE ");
            System.out.println("- Avoid flooded areas.");
            System.out.println("- Stay away from damaged power lines.");
            System.out.println("- Inspect home for structural damage.");
            System.out.println("------------------------------------------------------");
        }
    }

    private class FloodGuide extends DisasterGuide {
        @Override
        public void displayGuide() {
            System.out.println("\n AFTER FLOOD GUIDE ");
            System.out.println("- Do not walk on floodwater.");
            System.out.println("- Boil or purify water.");
            System.out.println("- Assist neighbors if safe.");
            System.out.println("------------------------------------------------------");
        }
    }

    private class FireGuide extends DisasterGuide {
        @Override
        public void displayGuide() {
            System.out.println("\n AFTER FIRE GUIDE ");
            System.out.println("- Do not re-enter burnt structures.");
            System.out.println("- Watch out for hot spots.");
            System.out.println("- Seek medical care if necessary.");
            System.out.println("------------------------------------------------------");
        }
    }

    // === Exception for survivor not found ===
    private class SurvivorNotFoundException extends Exception {
        public SurvivorNotFoundException(String message) {
            super(message);
        }
    }

    // === Menu and functionality methods ===

    private void showDisasterGuideMenu() {
        while (true) {
            System.out.println("\n================== AFTER DISASTER GUIDE ==================");
            System.out.println("[1] Earthquake");
            System.out.println("[2] Typhoon");
            System.out.println("[3] Flood");
            System.out.println("[4] Fire");
            System.out.println("[5] Return to Main Menu");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(" Invalid input! Numbers only.");
                continue;
            }

            DisasterGuide guide = null;

            switch (choice) {
                case 1 -> guide = new EarthquakeGuide();
                case 2 -> guide = new TyphoonGuide();
                case 3 -> guide = new FloodGuide();
                case 4 -> guide = new FireGuide();
                case 5 -> { return; }
                default -> System.out.println(" Invalid choice.");
            }

            if (guide != null) {
                guide.displayGuide();
            }
        }
    }

    private void addSurvivor() {
        if (count >= survivors.length) {
            System.out.println(" Cannot add more survivors. Maximum limit reached.");
            return;
        }
        scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Location: ");
        String location = scanner.nextLine();

        System.out.print("Enter Health Status: ");
        String health = scanner.nextLine();

        System.out.print("Enter Assistance Needed: ");
        String assist = scanner.nextLine();

        survivors[count++] = new Survivor(name, location, health, assist);
        System.out.println(" Survivor added successfully.");
    }

    private void searchSurvivor() {
        scanner.nextLine();
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        for (int i = 0; i < count; i++) {
            if (survivors[i].getName().equalsIgnoreCase(name)) {
                System.out.println(" Survivor Found:");
                survivors[i].display();
                return;
            }
        }
        System.out.println(" Survivor not found.");
    }

    private void deleteSurvivor() {
        scanner.nextLine();
        System.out.print("Enter survivor name to delete: ");
        String name = scanner.nextLine();

        try {
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (survivors[i].getName().equalsIgnoreCase(name)) {
                    found = true;
                    for (int j = i; j < count - 1; j++) {
                        survivors[j] = survivors[j + 1];
                    }
                    survivors[count - 1] = null;
                    count--;
                    System.out.println(" Survivor deleted successfully.");
                    break;
                }
            }
            if (!found) {
                throw new SurvivorNotFoundException("Survivor not found in the system.");
            }
        } catch (SurvivorNotFoundException e) {
            System.out.println(" " + e.getMessage());
        }
    }

    private void displaySurvivors() {
        if (count == 0) {
            System.out.println("No survivors recorded yet.");
            return;
        }
        for (int i = 0; i < count; i++) {
            survivors[i].display();
        }
    }

    private void sortSurvivors() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (survivors[j].getName().compareToIgnoreCase(survivors[j + 1].getName()) > 0) {
                    Survivor temp = survivors[j];
                    survivors[j] = survivors[j + 1];
                    survivors[j + 1] = temp;
                }
            }
        }
        System.out.println(" Survivors sorted by name.");
    }

    // === Main program loop ===
    public void run() {
        System.out.println("=====================================================");
        System.out.println("    AFTER DISASTER RESPONSE & SURVIVOR MANAGEMENT  ");
        System.out.println("=====================================================");

        while (true) {
            System.out.println("\n[1] After Disaster Guide");
            System.out.println("[2] Add Survivor");
            System.out.println("[3] Delete Survivor");
            System.out.println("[4] Search Survivor");
            System.out.println("[5] Sort Survivors");
            System.out.println("[6] Display All Survivors");
            System.out.println("[0] Return to Main Menu");
            System.out.print("Choose an option: ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println(" Invalid input! Numbers only.");
                continue;
            }

            switch (choice) {
                case 1 -> showDisasterGuideMenu();
                case 2 -> addSurvivor();
                case 3 -> deleteSurvivor();
                case 4 -> searchSurvivor();
                case 5 -> sortSurvivors();
                case 6 -> displaySurvivors();
                case 0 -> {
                    System.out.println("\nReturning to Main Menu...\n");
                    return;
                }
                default -> System.out.println(" Invalid choice.");
            }
        }
    }
}
