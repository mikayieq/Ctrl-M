import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/* ---------------------------
   Custom Exceptions
   --------------------------- */
class ReportNotFoundException extends Exception {
    public ReportNotFoundException(String message) { super(message); }
}

class InvalidChoiceException extends Exception {
    public InvalidChoiceException(String message) { super(message); }
}

/* ---------------------------
   Simple Hospital model (Encapsulation)
   --------------------------- */
class Hospital {
    private final String name;
    private final String city;
    private final String extra;

    public Hospital(String name, String city) {
        this(name, city, "");
    }

    public Hospital(String name, String city, String extra) {
        this.name = name;
        this.city = city;
        this.extra = extra;
    }

    public String getName() { return name; }
    public String getCity() { return city; }
    public String getExtra() { return extra; }

    @Override
    public String toString() {
        if (extra == null || extra.isEmpty()) return name + " " + city;
        return name + " " + city + " " + extra;
    }
}

/* ---------------------------
   Report model (Encapsulation)
   --------------------------- */
class Report {
    private final String id;
    private final String reporter;
    private final String location;
    private final String description;
    private final String injuryType;
    private String status;

    public Report(String id, String reporter, String location, String description, String injuryType) {
        this.id = id;
        this.reporter = reporter;
        this.location = location;
        this.description = description;
        this.injuryType = injuryType;
        this.status = "Active";
    }

    public String getId() { return id; }
    public String getReporter() { return reporter; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
    public String getInjuryType() { return injuryType; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }

    @Override
    public String toString() {
        return String.format("Report ID : %s\nReporter  : %s\nLocation  : %s\nDescription: %s\nInjury    : %s\nStatus    : %s",
                id, reporter, location, description, injuryType, status);
    }
}

/* ---------------------------
   Abstract DisasterManager (Abstraction)
   --------------------------- */
abstract class DisasterManager {
    protected final Map<String, Report> reports = new LinkedHashMap<>();
    protected final AtomicInteger counter = new AtomicInteger(0);
    protected final String codePrefix;
    protected final HospitalDirectory hospitalDirectory;

    public DisasterManager(String codePrefix, HospitalDirectory hd) {
        this.codePrefix = codePrefix;
        this.hospitalDirectory = hd;
    }

    protected String createId() {
        int n = counter.incrementAndGet();
        return String.format("%s%03d", codePrefix, n);
    }

    public Report insertReport(String reporter, String location, String desc, String injury) {
        String id = createId();
        Report r = new Report(id, reporter, location, desc, injury);
        reports.put(id, r);
        return r;
    }

    public Report searchByIdOrName(String key) throws ReportNotFoundException {
        if (key == null || key.trim().isEmpty()) throw new ReportNotFoundException("Empty search key.");
        String k = key.trim();
        for (String id : reports.keySet()) {
            if (id.equalsIgnoreCase(k)) return reports.get(id);
        }
        for (Report r : reports.values()) {
            if (r.getReporter().equalsIgnoreCase(k)) return r;
        }
        for (Report r : reports.values()) {
            if (r.getReporter().toLowerCase().contains(k.toLowerCase())) return r;
        }
        throw new ReportNotFoundException("No report found matching: " + key);
    }

    public List<Report> getAllReports() {
        return new ArrayList<>(reports.values());
    }

    public boolean deleteReport(String id) throws ReportNotFoundException {
        if (id == null || id.trim().isEmpty()) throw new ReportNotFoundException("Empty report ID.");
        String match = null;
        for (String key : reports.keySet()) {
            if (key.equalsIgnoreCase(id.trim())) { match = key; break; }
        }
        if (match == null) throw new ReportNotFoundException("Report ID not found: " + id);
        reports.get(match).setStatus("Deleted");
        reports.remove(match);
        return true;
    }

    public abstract List<String> getSafetySuggestions(int situationChoice) throws InvalidChoiceException;

    public List<Hospital> suggestHospitalsNearby(String location) {
        return hospitalDirectory.findHospitalsByCity(location);
    }
}

/* ---------------------------
   Concrete Managers
   --------------------------- */
class EarthquakeManager extends DisasterManager {
    public EarthquakeManager(HospitalDirectory hd) { super("EQ", hd); }

    @Override
    public List<String> getSafetySuggestions(int choice) throws InvalidChoiceException {
        switch (choice) {
            case 1 -> {
                return Arrays.asList(
                        "Stop any bleeding with direct pressure.",
                        "Immobilize fractures if trained to do so.",
                        "Call for emergency medical help."
                );
            }
            case 2 -> {
                return Arrays.asList(
                        "Keep the patient still and monitor breathing.",
                        "Control bleeding and avoid moving the neck/head.",
                        "Transport to nearest hospital immediately."
                );
            }
            case 3 -> {
                return Arrays.asList(
                        "Move person to a dry, warm place.",
                        "Remove wet clothing and cover with blankets.",
                        "Give warm fluids if conscious and seek medical care."
                );
            }
            case 4 -> {
                return Arrays.asList(
                        "Do not touch the person if still in contact with the electrical source.",
                        "Turn off power before approaching.",
                        "Check breathing and pulse; perform CPR if needed."
                );
            }
            default -> throw new InvalidChoiceException("Invalid situation for Earthquake suggestions.");
        }
    }
}

class TyphoonManager extends DisasterManager {
    public TyphoonManager(HospitalDirectory hd) { super("TYP", hd); }

    @Override
    public List<String> getSafetySuggestions(int choice) throws InvalidChoiceException {
        switch (choice) {
            case 1 -> {
                return Arrays.asList("Move the injured to safety and apply first aid.", "Get professional medical help.");
            }
            case 2 -> {
                return Arrays.asList("Avoid moving the person unless danger is imminent.", "Stabilize head/neck if suspected trauma.");
            }
            case 3 -> {
                return Arrays.asList("Dry and warm the victim; prevent further heat loss.");
            }
            case 4 -> {
                return Arrays.asList("Do not touch person while in contact with electricity; turn off power first.", "Call emergency services.");
            }
            default -> throw new InvalidChoiceException("Invalid situation for Typhoon suggestions.");
        }
    }
}

class FloodManager extends DisasterManager {
    public FloodManager(HospitalDirectory hd) { super("FLD", hd); }

    @Override
    public List<String> getSafetySuggestions(int choice) throws InvalidChoiceException {
        switch (choice) {
            case 1 -> {
                return Arrays.asList("Prioritize breathing and bleeding control.", "Avoid moving victims in water unless necessary.");
            }
            case 2 -> {
                return Arrays.asList("Check if the person is breathing.", "Perform CPR immediately if necessary.", "Keep them warm and transport urgently.");
            }
            case 3 -> {
                return Arrays.asList("Avoid water near downed power lines.", "Inform power company and emergency responders.");
            }
            case 4 -> {
                return Arrays.asList("Avoid ingesting flood water.", "Seek medical advice for wounds exposed to contaminated water.");
            }
            default -> throw new InvalidChoiceException("Invalid situation for Flood suggestions.");
        }
    }
}

class FireManager extends DisasterManager {
    public FireManager(HospitalDirectory hd) { super("FIR", hd); }

    @Override
    public List<String> getSafetySuggestions(int choice) throws InvalidChoiceException {
        switch (choice) {
            case 1 -> {
                return Arrays.asList("Move the injured to fresh air.", "Control bleeding and call emergency services.");
            }
            case 2 -> {
                return Arrays.asList("Cool burns with running water for 10-20 minutes.", "Avoid applying creams; seek medical care for severe burns.");
            }
            case 3 -> {
                return Arrays.asList("Move to fresh air immediately.", "Loosen clothing around chest and neck; perform rescue breathing if needed.");
            }
            case 4 -> {
                return Arrays.asList("Check breathing and pulse; begin CPR if necessary.", "Call emergency responders immediately.");
            }
            default -> throw new InvalidChoiceException("Invalid situation for Fire suggestions.");
        }
    }
}

/* ---------------------------
   Hospital Directory
   --------------------------- */
class HospitalDirectory {
    private final Map<String, List<Hospital>> hospitalsByCity = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public HospitalDirectory() {
        populateHospitals();
    }

    private void addHospital(String city, String name) {
        addHospital(city, name, "");
    }

    private void addHospital(String city, String name, String extra) {
        hospitalsByCity.computeIfAbsent(city.trim(), k -> new ArrayList<>()).add(new Hospital(name, city, extra));
    }

    private void populateHospitals() {
        addHospital("Batangas City", "Batangas Medical Center");
        addHospital("Batangas City", "Golden Gate Batangas Hospital, Inc.");
        addHospital("Batangas City", "St. Patrick's Hospital Medical Center");
        addHospital("Batangas City", "Batangas Health Care Hospital (Jesus of Nazareth)");
        addHospital("Batangas City", "Batangas Healthcare Specialists Medical Center");
        addHospital("Batangas City", "United Doctors of St. Camillus de Lellis Hospital");
        addHospital("Lipa City", "Mary Mediatrix Medical Center");
        addHospital("Lipa City", "Metro Lipa Medical Center");
        addHospital("Lipa City", "Ospital ng Lipa (Lipa City District Hospital)");
        addHospital("Lipa City", "Divine Love General Hospital");
        addHospital("Lipa City", "N.L. Villa Memorial Medical Center");
        addHospital("Lipa City", "Lipa Medix Medical Center");
        addHospital("Lipa City", "San Antonio Medical Center of Lipa");
        addHospital("Lipa City", "San Antonio Life Care Corporation");
        addHospital("Tanauan City", "Daniel O. Mercado Medical Center");
        addHospital("Tanauan City", "C.P. Reyes Hospital (Tanauan)");
        addHospital("Bauan", "Bauan Doctors General Hospital");
        addHospital("Bauan", "Dr. Mario D. Bejasa General Hospital");
        addHospital("Balayan", "Metro Balayan Medical Center");
        addHospital("Balayan", "Don Manuel Lopez Memorial District Hospital");
        addHospital("Balayan", "Medical Center Western Batangas");
        addHospital("Balayan", "Balayan Bayview Hospital & Medical Center");
        addHospital("Nasugbu", "Apacible Memorial District Hospital (Nasugbu)");
        addHospital("Nasugbu", "Nasugbu Doctors Hospital");
        addHospital("Nasugbu", "Central Azucarera Don Pedro Hospital");
        addHospital("Nasugbu", "Jabez Medical Center (Nasugbu)");
        addHospital("Lemery", "Lemery Doctors Hospital");
        addHospital("Lemery", "Metro Lemery Medical Center");
        addHospital("Lemery", "Our Lady of Caysasay Medical Center, Inc.");
        addHospital("Sto. Tomas", "Sto. Tomas Doctors Hospital and Medical Center");
        addHospital("Sto. Tomas", "St. Frances Cabrini Medical Center");
        addHospital("San Jose", "Metro San Jose Medical Center");
        addHospital("San Jose", "San Jose Doctors General Hospital");
        addHospital("Talisay", "Talisay Community Hospital");
        addHospital("Padre Garcia", "Padre Garcia District Hospital");
        addHospital("Rosario", "Rosario Community Hospital");
        addHospital("Calaca", "Ospital ng Calaca");
        addHospital("Calatagan", "Calatagan Medicare Hospital");
        addHospital("Tuy", "Tuy District Hospital");
        addHospital("Lian", "Lian District Hospital");
        addHospital("Mabini", "Mabini General Hospital");
        addHospital("Mabini", "Zigzag Hospital (Mabini)");
        addHospital("Lobo", "Lobo Municipal Hospital");
        addHospital("San Pascual", "San Antonio Life Care Corporation");
        addHospital("San Pascual", "San Pascual Baylon Hospital");
        addHospital("Malvar", "Malvar General Hospital");
        addHospital("Ibaan", "Ibaan District Hospital");
        addHospital("San Juan", "San Juan District Hospital");
        addHospital("Taysan", "Taysan Community Hospital");
        addHospital("Agoncillo", "Agoncillo Medicare Hospital");
        addHospital("Mataasnakahoy", "Mataasnakahoy Community Hospital");
    }

    public List<Hospital> findHospitalsByCity(String city) {
        if (city == null || city.trim().isEmpty()) {
            return getTopHospitals();
        }
        String key = city.trim();
        List<Hospital> local = hospitalsByCity.get(key);
        if (local != null && !local.isEmpty()) return local;
        for (String c : hospitalsByCity.keySet()) {
            if (c.equalsIgnoreCase(key) || c.toLowerCase().contains(key.toLowerCase())) {
                return hospitalsByCity.get(c);
            }
        }
        return getTopHospitals();
    }

    public List<Hospital> getTopHospitals() {
        List<Hospital> out = new ArrayList<>();
        String[] cities = {"Batangas City", "Lipa City", "Tanauan City", "Bauan", "Sto. Tomas", "Balayan", "Nasugbu", "Lemery"};
        for (String c : cities) {
            List<Hospital> list = hospitalsByCity.get(c);
            if (list != null) out.addAll(list);
        }
        return out;
    }
}

/* ---------------------------
   Main DuringDisaster Class
   --------------------------- */
public class DuringDisaster {
    private static final Scanner sc = new Scanner(System.in);
    private final EarthquakeManager eqManager;
    private final TyphoonManager tyManager;
    private final FloodManager flManager;
    private final FireManager fiManager;
    private final HospitalDirectory hospitalDirectory;

    public DuringDisaster() {
        hospitalDirectory = new HospitalDirectory();
        eqManager = new EarthquakeManager(hospitalDirectory);
        tyManager = new TyphoonManager(hospitalDirectory);
        flManager = new FloodManager(hospitalDirectory);
        fiManager = new FireManager(hospitalDirectory);
    }

    public void runMainMenu() {
        while (true) {
            printHeader();
            System.out.println("Select the type of disaster you're facing:");
            System.out.println();
            System.out.println("[1] Earthquake");
            System.out.println("[2] Typhoon");
            System.out.println("[3] Flood");
            System.out.println("[4] Fire");
            System.out.println("[0] Return to Main Menu");
            System.out.println("---------------------------------------------------------");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> disasterMenu(eqManager, "EARTHQUAKE RESPONSE MANAGEMENT");
                    case "2" -> disasterMenu(tyManager, "TYPHOON RESPONSE MANAGEMENT");
                    case "3" -> disasterMenu(flManager, "FLOOD RESPONSE MANAGEMENT");
                    case "4" -> disasterMenu(fiManager, "FIRE RESPONSE MANAGEMENT");
                    case "0" -> {
                        System.out.println("\nReturning to Main Menu...");
                        return;
                    }
                    default -> System.out.println("\n Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private void printHeader() {
        System.out.println("==========================================================");
        System.out.println("                   DURING A DISASTER  ");
        System.out.println("        Real-time Response and Emergency Management");
        System.out.println("==========================================================");
        System.out.println();
    }

    private void disasterMenu(DisasterManager manager, String header) {
        boolean continueMenu = true;
        while (continueMenu) {
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("             " + header);
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("[1] Insert New " + header.split(" ")[0] + " Report");
            System.out.println("[2] Search a Report");
            System.out.println("[3] Display All Reports");
            System.out.println("[4] Delete a Report");
            System.out.println("[5] Get Safety Suggestions");
            System.out.println("[0] Back to Main Menu");
            System.out.println("------------------------------------------------------------------------------------");
            System.out.print("Enter choice: ");
            String c = sc.nextLine().trim();
            try {
                switch (c) {
                    case "1" -> insertFlow(manager);
                    case "2" -> searchFlow(manager);
                    case "3" -> displayAllFlow(manager);
                    case "4" -> deleteFlow(manager);
                    case "5" -> suggestionsFlow(manager);
                    case "0" -> continueMenu = false;
                    default -> System.out.println("Invalid choice. Please choose from the menu.");
                }
            } catch (ReportNotFoundException | InvalidChoiceException rnfe) {
                System.out.println(" " + rnfe.getMessage());
            }

            if (!continueMenu) break;
            System.out.print("Would you like to perform another action in this disaster menu? (Y/N): ");
            String again = sc.nextLine().trim();
            if (!again.equalsIgnoreCase("Y")) break;
        }
    }

    private void insertFlow(DisasterManager manager) {
        System.out.println();
        System.out.println("(1) Insert New Report");
        System.out.print("Enter Reporter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Location (City/Municipality): ");
        String loc = sc.nextLine().trim();
        System.out.print("Enter Description of Incident: ");
        String desc = sc.nextLine().trim();
        System.out.print("Enter Type of Injury: ");
        String injury = sc.nextLine().trim();
        Report r = manager.insertReport(name, loc, desc, injury);
        System.out.println();
        System.out.println(" Report successfully recorded!");
        System.out.println("--------------------------------------------------");
        System.out.println(r);
        System.out.println("--------------------------------------------------");
        System.out.println();
    }

    private void searchFlow(DisasterManager manager) throws ReportNotFoundException {
        System.out.println();
        System.out.println("(2) Search a Report");
        System.out.print("Enter report ID or reporter name to search: ");
        String k = sc.nextLine().trim();
        Report r = manager.searchByIdOrName(k);
        System.out.println();
        System.out.println("Found Report:");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(r);
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("\nNearby Hospitals in " + r.getLocation() + ":");
        List<Hospital> nearby = manager.suggestHospitalsNearby(r.getLocation());
        int idx = 1;
        for (Hospital h : nearby) {
            System.out.printf("%d. %s\n", idx++, h.toString());
            if (idx > 15) break;
        }
        System.out.println();
    }

    private void displayAllFlow(DisasterManager manager) {
        System.out.println();
        System.out.println("(3) Display All Reports");
        List<Report> all = manager.getAllReports();
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("                          All Recorded Reports");
        System.out.println("----------------------------------------------------------------------------------------");
        if (all.isEmpty()) {
            System.out.println("No reports recorded.");
        } else {
            int i = 1;
            for (Report r : all) {
                System.out.printf("#%d | %s | %s | %s | %s\n", i++, r.getReporter(), r.getLocation(), r.getInjuryType(), truncateText(r.getDescription(), 40));
            }
            System.out.println("----------------------------------------------------------------------------------------");
            System.out.println("Total Reports: " + all.size());
        }
        System.out.println();
    }

    private void deleteFlow(DisasterManager manager) throws ReportNotFoundException {
        System.out.println();
        System.out.println("(4) Delete a Report");
        System.out.print("Enter Report ID to delete (e.g., EQ001): ");
        String id = sc.nextLine().trim();
        manager.deleteReport(id);
        System.out.println();
        System.out.println(" Report successfully deleted!");
    }

    private void suggestionsFlow(DisasterManager manager) throws InvalidChoiceException {
        System.out.println();
        System.out.println("(5) Get Safety Suggestions");
        System.out.println("Select current situation:");
        System.out.println("[1] Injured");
        System.out.println("[2] Head and Chest Trauma");
        System.out.println("[3] Hypothermia / Drowning / Other (contextual)");
        System.out.println("[4] Electric Shock / Other urgent");
        System.out.println("[0] Cancel");
        System.out.print("Enter choice: ");
        String choiceStr = sc.nextLine().trim();
        if ("0".equals(choiceStr)) {
            System.out.println("Cancelled.");
            return;
        }
        int choice;
        try {
            choice = Integer.parseInt(choiceStr);
        } catch (NumberFormatException nfe) {
            throw new InvalidChoiceException("Choice must be a number.");
        }
        List<String> suggestions = manager.getSafetySuggestions(choice);
        System.out.println("\nSuggested Actions:");
        System.out.println("--------------------------------------------------------------------------------");
        for (String s : suggestions) {
            System.out.println(" - " + s);
        }
        System.out.println("--------------------------------------------------------------------------------");

        System.out.print("\nEnter current city/location so we can suggest nearby hospitals (or press Enter to show major hospitals): ");
        String city = sc.nextLine().trim();
        List<Hospital> nearby = manager.suggestHospitalsNearby(city);
        System.out.println("\nNearby Hospitals in Batangas:");
        System.out.println("--------------------------------------------------------------------------------");
        int i = 1;
        for (Hospital h : nearby) {
            System.out.printf("%2d. %s\n", i++, h.toString());
            if (i > 15) break;
        }
        System.out.println("--------------------------------------------------------------------------------\n");
    }

    private String truncateText(String s, int max) {
        if (s == null) return "";
        if (s.length() <= max) return s;
        return s.substring(0, max - 3) + "...";
    }
}