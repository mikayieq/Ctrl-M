import java.util.Scanner;

public class ReadySetRecover {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayWelcome();
        runMainMenu();
        System.out.println("\n=====================================================");
        System.out.println("       THANK YOU FOR USING READY. SET. RECOVER!");
        System.out.println("            Stay safe and resilient!");
        System.out.println("=====================================================");
    }

    private static void displayWelcome() {
        System.out.println("=====================================================");
        System.out.println("             READY. SET. RECOVER!  ");
        System.out.println("=====================================================");
        System.out.println();
        System.out.println("   A Comprehensive Disaster Preparedness and");
        System.out.println("       Response Management System");
        System.out.println();
        System.out.println("This system helps you prepare for, respond to,");
        System.out.println("and recover from natural disasters including:");
        System.out.println("   Earthquakes , Typhoons , Floods , Fires");
        System.out.println();
        System.out.println("=====================================================");
        System.out.println();
    }

    private static void runMainMenu() {
        while (true) {
            System.out.println("\n╔════════════════════════════════════════════════╗");
            System.out.println("║           DISASTER RESPONSE PHASES             ║");
            System.out.println("╚════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("Where are you in the disaster timeline?");
            System.out.println();
            System.out.println("[1] BEFORE - Preparation & Planning");
            System.out.println("    Learn what to do before a disaster strikes");
            System.out.println("    Prepare emergency kits and evacuation plans");
            System.out.println();
            System.out.println("[2] DURING - Emergency Response");
            System.out.println("    Report incidents and injuries in real-time");
            System.out.println("    Get safety suggestions and find nearby hospitals");
            System.out.println();
            System.out.println("[3] AFTER - Recovery & Relief");
            System.out.println("    Record and manage survivor information");
            System.out.println("    Access post-disaster recovery guidelines");
            System.out.println();
            System.out.println("[0] Exit System");
            System.out.println();
            System.out.println("─────────────────────────────────────────────────");
            System.out.print("Enter your choice (0-3): ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.println("\nRedirecting to BEFORE DISASTER module...\n");
                    runBeforeDisaster();
                }
                case "2" -> {
                    System.out.println("\nRedirecting to DURING DISASTER module...\n");
                    runDuringDisaster();
                }
                case "3" -> {
                    System.out.println("\nRedirecting to AFTER DISASTER module...\n");
                    runAfterDisaster();
                }
                case "0" -> {
                    return;
                }
                default -> System.out.println("\n Invalid choice. Please enter a number between 0 and 3.");
            }
        }
    }

    private static void runBeforeDisaster() {
        BeforeDisaster beforeModule = new BeforeDisaster();
        beforeModule.run();
    }

    private static void runDuringDisaster() {
        DuringDisaster duringModule = new DuringDisaster();
        duringModule.runMainMenu();
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private static void runAfterDisaster() {
        AfterDisaster afterModule = new AfterDisaster();
        afterModule.run();
    }
}