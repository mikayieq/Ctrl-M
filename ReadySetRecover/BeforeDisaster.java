import java.util.Scanner;

public class BeforeDisaster {
    private final Scanner scanner = new Scanner(System.in);
    private final DisasterGuide guide = new DisasterGuide();

    public void run() {
        while (true) {
            System.out.println("╔════════════════════════════════════════════════╗");
            System.out.println("║      BEFORE DISASTER - PREPARATION GUIDE       ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("Select a disaster type to view preparation guide:");
            System.out.println();
            System.out.println("[1] Earthquake Preparation");
            System.out.println("[2] Typhoon Preparation");
            System.out.println("[3] Flood Preparation");
            System.out.println("[4] Fire Preparation");
            System.out.println("[0] Return to Main Menu");
            System.out.println();
            System.out.println("─────────────────────────────────────────────────");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    guide.displayEarthquakeGuide();
                    pressEnterToContinue();
                }
                case "2" -> {
                    guide.displayTyphoonGuide();
                    pressEnterToContinue();
                }
                case "3" -> {
                    guide.displayFloodGuide();
                    pressEnterToContinue();
                }
                case "4" -> {
                    guide.displayFireGuide();
                    pressEnterToContinue();
                }
                case "0" -> {
                    System.out.println("\nReturning to Main Menu...\n");
                    return;
                }
                default -> System.out.println("\n Invalid choice. Please try again.\n");
            }
        }
    }

    private void pressEnterToContinue() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }
}

class DisasterGuide {
   
    public void displayEarthquakeGuide() {
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("BEFORE DISASTER: EARTHQUAKE PREPARATION GUIDE");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("What you need to DO:");
        System.out.println("1) Identify safe spots in every room (under sturdy furniture, away from windows).");
        System.out.println("2) Secure heavy furniture and appliances.");
        System.out.println("3) Make an evacuation plan.");
        System.out.println("\nYour EMERGENCY KIT CHECKLIST for Earthquake:");
        System.out.println("Water (3-5 liters per person)");
        System.out.println("Non-perishable food (canned, dry snacks)");
        System.out.println("Flashlight & spare batteries");
        System.out.println("First aid kit");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }
   
    public void displayTyphoonGuide() {
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("BEFORE DISASTER: TYPHOON PREPARATION GUIDE");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("What you need to DO:");
        System.out.println("1) Secure windows, doors, and loose outdoor items.");
        System.out.println("2) Charge all mobile devices and save emergency contacts.");
        System.out.println("3) Monitor weather updates regularly and plan evacuation routes/safe shelters.");
        System.out.println("\nYour EMERGENCY KIT CHECKLIST for Typhoon:");
        System.out.println("3-day emergency kit (food, water)");
        System.out.println("Flashlight & spare batteries");
        System.out.println("Emergency contacts");
        System.out.println("First aid kit");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }
   
    public void displayFloodGuide() {
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("BEFORE DISASTER: FLOOD PREPARATION GUIDE");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("What you need to DO:");
        System.out.println("1) Move valuables and important documents to higher ground or upper floors.");
        System.out.println("2) Monitor water levels, weather updates, and local alerts.");
        System.out.println("3) Know the nearest evacuation shelters and safe routes.");
        System.out.println("\nYour EMERGENCY KIT CHECKLIST for Flood:");
        System.out.println("Water (3-5 liters per person)");
        System.out.println("Non-perishable food (canned, dry snacks)");
        System.out.println("First aid kit");
        System.out.println("Rubber boots / water shoes");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }
   
    public void displayFireGuide() {
        System.out.println("\n-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("BEFORE DISASTER: FIRE PREPARATION GUIDE");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("What you need to DO:");
        System.out.println("1) Check and maintain all electrical wiring and appliances.");
        System.out.println("2) Keep fire extinguishers and fire blankets accessible.");
        System.out.println("3) Plan escape routes from your home.");
        System.out.println("\nYour EMERGENCY KIT CHECKLIST for Fire:");
        System.out.println("Fire extinguisher");
        System.out.println("First aid kit");
        System.out.println("Smoke detectors installed & batteries checked");
        System.out.println("Protective gloves and masks (for smoke)");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }
}