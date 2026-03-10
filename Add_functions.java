import java.sql.*;
import java.util.Scanner;

public class Add_functions {

    static Scanner sc = new Scanner(System.in);

    public static void addCustomer(Connection conn){
    try{

        System.out.println("Customer ID:");
        int id = sc.nextInt();
        sc.nextLine();

        if(id <= 0){
            System.out.println("Error: ID must be positive");
            return;
        }

        // check duplicate ID
        String check = "SELECT id FROM customer WHERE id = ?";
        PreparedStatement stmtCheck = conn.prepareStatement(check);
        stmtCheck.setInt(1,id);

        ResultSet rs = stmtCheck.executeQuery();

        if(rs.next()){
            System.out.println("Error: Customer ID already exists");
            return;
        }

        System.out.println("First Name:");
        String fname = sc.nextLine();
        if(fname.isEmpty()){
            System.out.println("Error: Invalid first name");
            return;
        }

        System.out.println("Last Name:");
        String lname = sc.nextLine();
        if(lname.isEmpty()){
            System.out.println("Error: Invalid last name");
            return;
        }

        System.out.println("Phone:");
        String phone = sc.nextLine();
        if(phone.length() < 10){
            System.out.println("Error: Invalid phone number");
            return;
        }

        System.out.println("Address:");
        String address = sc.nextLine();
        if(address.isEmpty()){
            System.out.println("Error: Invalid address");
            return;
        }

        String sql = "INSERT INTO customer VALUES(?,?,?,?,?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.setString(2,fname);
        stmt.setString(3,lname);
        stmt.setString(4,phone);
        stmt.setString(5,address);

        stmt.executeUpdate();

        System.out.println("Customer Added");

    }catch(Exception e){
        System.out.println("Error adding customer");
    }
}
    public static void addMechanic(Connection conn){

        try{

            System.out.println("Employee ID:");
            int id = sc.nextInt();
            sc.nextLine();

            if(id <= 0){
                System.out.println("Error: ID must be positive");
                return;
            }

            String check = "SELECT employee_id FROM mechanic WHERE employee_id = ?";
            PreparedStatement stmtCheck = conn.prepareStatement(check);
            stmtCheck.setInt(1,id);

            ResultSet rs = stmtCheck.executeQuery();

            if(rs.next()){
                System.out.println("Error: Employee ID already exists");
                return;
            }

            System.out.println("First Name:");
            String fname = sc.nextLine();
            if(fname.isEmpty()){
                System.out.println("Error: Invalid first name");
                return;
            }

            System.out.println("Last Name:");
            String lname = sc.nextLine();
            if(lname.isEmpty()){
                System.out.println("Error: Invalid last name");
                return;
            }

            System.out.println("Years Experience:");
            int exp = sc.nextInt();

            if(exp < 0){
                System.out.println("Error: Experience cannot be negative");
                return;
            }

            System.out.println("VIN of car they specialize in:");
            String VIN = sc.nextLine();

            String sqlCheck = "SELECT * FROM car WHERE vin = ?";
            PreparedStatement stmtCustomer = conn.prepareStatement(sqlCheck);
            stmtCustomer.setString(1, VIN);

            ResultSet rsCustomer = stmtCustomer.executeQuery();

            if(!rsCustomer.next()){
                System.out.println("Error: Car does not exist");
                return;
            }

            String sql = "INSERT INTO mechanic(employee_id,fname,lname,years_of_experience,vin) VALUES(?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1,id);
            stmt.setString(2,fname);
            stmt.setString(3,lname);
            stmt.setInt(4,exp);
            stmt.setString(5,VIN);

            stmt.executeUpdate();

            System.out.println("Mechanic Added");

        }catch(Exception e){
            System.out.println("Error adding mechanic");
        }
    }
    public static void addCar(Connection conn){

        try{

            System.out.println("VIN:");
            String vin = sc.nextLine();

            if(vin.isEmpty()){
                System.out.println("Error: Invalid VIN");
                return;
            }

            String check = "SELECT vin FROM car WHERE vin = ?";
            PreparedStatement stmtCheck = conn.prepareStatement(check);
            stmtCheck.setString(1,vin);

            ResultSet rs = stmtCheck.executeQuery();

            if(rs.next()){
                System.out.println("Error: VIN already exists");
                return;
            }

            System.out.println("Make:");
            String make = sc.nextLine();
            if(make.isEmpty()){
                System.out.println("Error: Invalid make");
                return;
            }

            System.out.println("Model:");
            String model = sc.nextLine();
            if(model.isEmpty()){
                System.out.println("Error: Invalid model");
                return;
            }

            System.out.println("Year:");
            int year = sc.nextInt();
            sc.nextLine();

            if(year < 0 || year > 2026){
                System.out.println("Error: Invalid year");
                return;
            }

            System.out.println("Customer ID:");
            int customer_id = sc.nextInt();
            sc.nextLine();

            String sqlCheck = "SELECT * FROM customer WHERE id = ?";
            PreparedStatement stmtCustomer = conn.prepareStatement(sqlCheck);
            stmtCustomer.setInt(1, customer_id);

            ResultSet rsCustomer = stmtCustomer.executeQuery();

            if(!rsCustomer.next()){
                System.out.println("Error: Customer does not exist");
                return;
            }

            String sql = "INSERT INTO car VALUES(?,?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,vin);
            stmt.setString(2,make);
            stmt.setString(3,model);
            stmt.setInt(4,customer_id);
            stmt.setInt(5,year);

            stmt.executeUpdate();

            System.out.println("Car Added");

        }catch(Exception e){
            System.out.println("Error adding car");
        }
    }
}
