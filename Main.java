import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:42408/smarc029_shop";
        String user = "smarc029";
        String password = "";

        try (
            Connection conn = DriverManager.getConnection(url, user, password);
            Scanner scanner = new Scanner(System.in);
        ) {

            System.out.println("Connected to database!");

            boolean running = true;

            while (running) {
                System.out.println("\n===== Mechanics Shop Menu =====");
                System.out.println("1. Add Customer");
                System.out.println("2. Add Mechanic");
                System.out.println("3. Add Car");
                System.out.println("4. Initiate Service Request");
                System.out.println("5. Close Service Request");
                System.out.println("6. Lists");
                System.out.println("0. Exit");
                System.out.print("Choose option: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Add Customer selected.");
                        Add_functions.addCustomer(conn);
                        break;
                    case 2:
                        System.out.println("Add Mechanic selected.");
                        Add_functions.addMechanic(conn);
                        break;
                    case 3:
                        System.out.println("Add Car selected.");
                        Add_functions.addCar(conn);
                        break;
                    case 4:
                        System.out.println("Initiate Service Request selected.");
                        break;
                    case 5:
                        System.out.println("Close Service Request selected.");
                        break;
                    case 6:
                        System.out.println("Reports selected.");
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}