import java.sql.*;
import java.util.Scanner;

public class Query_functions {

    public static void allQueries(Connection conn, Scanner scanner){ 
        boolean running = true;
        while (running) {
            System.out.println("\n===== Lists =====");
            System.out.println("1. List date, comment, and bill for all closed requests with bill lower than 100");
            System.out.println("2. List the customers that have paid less than 100 dollars for repairs based on their previous service requests.");
            System.out.println("3. List first and last name of customers having more than 20 different cars");
            System.out.println("4. List Make, Model, and Year of all cars build before 1995 having less than 50000 miles");
            System.out.println("5. List the make, model and number of service requests for the first k cars with the highest number of service orders.");
            System.out.println("6. List the first name, last name and total bill of customers in descending order of their total bill for all cars brought to the mechanic.");
            System.out.println("0. To return to main menu");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Closed requests with bill lower than 100:");
                    closedRequests(conn);
                    break;
                case 2:
                    System.out.println("Customers that have paid less than 100 dollars for repairs:");
                    customersLessThan100(conn);
                    break;
                case 3:
                    System.out.println("Customers with more than 20 cars:");
                    customersMoreThan20Cars(conn);
                    break;
                case 4:
                    System.out.println("Cars built before 1995 with less than 50000 miles:");
                    break;
                case 5:
                    System.out.println("First k cars with the highest number of service orders:");
                    break;
                case 6:
                    System.out.println("Customers in descending order of total bill:");
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting Lists.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void closedRequests(Connection conn){
        try{
            String sql =
            "SELECT closed_date, comments, final_bill FROM service_request " +
            "WHERE is_closed = TRUE AND final_bill < 100";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                System.out.println(
                    rs.getDate("closed_date") + " | " +
                    rs.getString("comments") + " | " +
                    rs.getDouble("final_bill")
                );
            }

        }catch(SQLException e){
            System.out.println("Error retrieving requests");
            e.printStackTrace();
        }
    }


    public static void customersLessThan100(Connection conn){
        try{
            String sql =
            "SELECT DISTINCT c.fname, c.lname " +
            "FROM customer c " +
            "JOIN car ca ON c.id = ca.customer_id " +
            "JOIN service_request sr ON ca.vin = sr.vin " +
            "WHERE sr.final_bill < 100 AND sr.is_closed = TRUE";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                System.out.println(
                    rs.getString("fname") + " " +
                    rs.getString("lname")
                );
            }

        }catch(SQLException e){
            System.out.println("Error retrieving customers");
            e.printStackTrace();
        }
    }


    public static void customersMoreThan20Cars(Connection conn){

        try{

            String sql =
            "SELECT c.fname, c.lname, COUNT(*) AS num_cars " +
            "FROM customer c " +
            "JOIN car ca ON c.id = ca.customer_id " +
            "GROUP BY c.id, c.fname, c.lname " +
            "HAVING COUNT(*) > 20";

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                System.out.println(
                    rs.getString("fname") + " " +
                    rs.getString("lname") +
                    " Cars: " +
                    rs.getInt("num_cars")
                );
            }

        }catch(SQLException e){
            System.out.println("Error retrieving customers");
            e.printStackTrace();
        }
    }
}
