package com.jdbc.customerLoginRegistration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLInvalidAuthorizationSpecException;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerLoginRegisterDemo 
{
    public static void main(String[] args) 
    {
        //main try
        try (Scanner sc = new Scanner(System.in)) {
            // Create connection
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "advjava", "advjava");

            Statement smt = con.createStatement();
            
            // SQL query for creating the CustomerInfo table
            String createTable = "CREATE TABLE CustomerInfo (" +
                    "Custid NUMBER(10) PRIMARY KEY, " +
                    "Custname VARCHAR2(30) NOT NULL, " +
                    "CustPassword VARCHAR2(20), " +
                    "CustFname VARCHAR2(30), " +
                    "CustLname VARCHAR2(20), " +
                    "custEmail VARCHAR2(20), " +
                    "custMob NUMBER(10))";
            
            // Try block for creating the table
            try {
                smt.executeUpdate(createTable);
                System.out.println("Table created successfully.");
            } catch (SQLException e) {
                // Catch block for table creation (checking for table already exists error)
                if (e.getErrorCode() == 955) { // ORA-00955: table already exists
                    System.out.println("Table already exists, you are not allowed to create a duplicate table");
                } else {
                    throw e;
                }
            }
            //while for main
 while(true) {
            // Main menu
            System.out.println("What do you want?");
            System.out.println("\t1. Login");
            System.out.println("\t2. Register");
            System.out.println("\t3. Exit");
            
            System.out.println("Enter choice:");
            int mchoice = Integer.parseInt(sc.nextLine());

            // Switch for handling user choice between login and registration
            switch (mchoice) {
                case 1:
                    // Login operation
                    System.out.println("Login");
                    System.out.println("Enter Customer Name:");
                    String cName = sc.nextLine();
                    System.out.println("Enter Customer Password:");
                    String cPassword = sc.nextLine();

                    // Try block for login
                    try {
                        // SQL query for login authentication
                        ResultSet rsl = smt.executeQuery("select * from CustomerInfo WHERE Custname='"+cName+"' AND CustPassword='"+cPassword +"'");
                        if (rsl.next()) {
                            System.out.println("Logged In Successfully");
                            while (true) {
                                // Customer operation options after successful login
                                System.out.println("---Customer Operations-------");
                                System.out.println("\t1. Show all the customers");
                                System.out.println("\t2. View Customer By ID");
                                System.out.println("\t3. Update Customer By ID");
                                System.out.println("\t4. Delete Customer By name Stating with  Any Character");
                                System.out.println("\t5. Show customer Whose custID is prime:");
                                System.out.println("\t6. Logout");
                                System.out.println("Enter your choice:");
                                int lchoice = Integer.parseInt(sc.nextLine());
                                
                                // Switch block for customer operations after Login
                                switch (lchoice) {
                                    case 1:
                                        System.out.println("-------View all the customers-----");
                                        ResultSet rs = smt.executeQuery("select * from CustomerInfo");
                		                System.out.println("--------------Customer Details:----------------- ");
                		                while(rs.next()) 
                		                {
                		                	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getLong(7));
                		                }

                                        break;
                                    case 2:
                                        System.out.println("---View Customer By ID--");
                                        System.out.println("Enter The CustomerId:");
                		        		int cuid=Integer.parseInt(sc.nextLine());
                		        		ResultSet rs1=smt.executeQuery("Select * from Customerinfo where custid="+cuid+"");
                		        		if(rs1.next()) 
                		        		{
                		        			System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getString(4)+"\t"+rs1.getString(5)+"\t"+rs1.getString(6)+"\t"+rs1.getLong(7));		        			
                		        		}

                                        break;
                                    case 3:
                                        System.out.println("Update Customer By ID");
                                        System.out.println("Enter Customer id");
                                        int ucid=Integer.parseInt(sc.nextLine());
                                        
                                        ResultSet rsu=smt.executeQuery("select * from customerInfo  custid="+ucid+"");
                                        
                                       if(rsu.next()) 
                                       {
                                    	   System.out.println("previous mail id:"+rsu.getString(6));
                                    	   System.out.println("Enter new Email For Above Customer");
                                    	   String uemil=sc.nextLine();
                                    	   System.out.println("previous Mobile Number is"+rsu.getLong(7));
                                    	   System.out.println("ENter new Mobile number:");
                                    	   long umob=Long.parseLong(sc.nextLine());
                                    	   int uquery=smt.executeUpdate("update customerinfo set custemail='"+uemil+"',custmob="+umob+"");
                                    	   if(uquery>0) 
                                    	   {
                                    		   System.out.println("Customer Updated Successfully");
                                    		   
                                    	   }
                                    	   else 
                                    	   {
                                    		   System.out.println("Customer Not avaliable");
                                    		   
                                    	   }
                                    	   
                                       }
                                        break;
                                    case 4:
                                    	System.out.println("Delete By Name Starting with a Specified Character");
                                    	System.out.println("Enter the starting character of the customer name you want to delete:");
                                    	char dcust = sc.nextLine().charAt(0);
                                    	int rsdl = smt.executeUpdate("DELETE FROM CustomerInfo WHERE Custname LIKE '"+dcust+"%'");
                                    	if (rsdl > 0) {
                                    	    System.out.println("User Deleted Successfully");
                                    	} else {
                                    	    System.out.println("No User found whose name starts with '" + dcust + "'");
                                    	}

                                    	break;
                                    case 5:
                                        ResultSet se = smt.executeQuery("SELECT Custid FROM customerinfo");

                                        while (se.next()) {
                                            int custId = se.getInt("Custid");
                                            boolean isPrime = true;

                                            // Check if custId is prime
                                            if (custId <= 1) {
                                                isPrime = false;
                                            } else {
                                                for (int i = 2; i <= Math.sqrt(custId); i++) {
                                                    if (custId % i == 0) {
                                                        isPrime = false;
                                                        break;
                                                    }
                                                }
                                            }

                                            if (isPrime) {
                                                System.out.println("Customer ID " + custId + " is a prime number.");

                                                // Get and display the customer details for prime Custid
                                                ResultSet pid = smt.executeQuery("SELECT * FROM customerInfo WHERE Custid=" + custId);
                                                if (pid.next()) {
                                                    System.out.println(pid.getInt(1) + "\t" + pid.getString(2) + "\t" + pid.getString(3) + "\t" 
                                                                       + pid.getString(4) + "\t" + pid.getString(5) + "\t" + pid.getString(6) + "\t" 
                                                                       + pid.getLong(7));
                                                } else {
                                                    System.err.println("Customer with ID " + custId + " not found.");
                                                }
                                            } else {
                                                System.err.println("Customer ID " + custId + " is not a prime number.");
                                            }
                                            
                                        }
                                      
                                        break;

                                    case 6:
                                        // Logout operation
                                        System.out.println("Logout Successfully");
                                        System.exit(0);
                                        break;
                                    default:
                                        System.out.println("Invalid Choice. Please choose a valid option.");
                                }
                            }
                        } else {
                            // If login fails
                            System.err.println("Login Failed. Invalid Customer name or password.");
                        }
                    } catch (SQLException e) {
                        // Catch block for login errors
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    // Registration operation
                    System.out.println("Register");
                    while (true) {
                        System.out.println("----Operations we have----------");
                        System.out.println("\t1. Add customer/Register");
                        System.out.println("\t2. Exit from register");
                        System.out.println("Enter choice:");
                        int rchoice = Integer.parseInt(sc.nextLine());
                        
                        // Switch block for  registration operations
                        switch (rchoice) {
                            case 1:
                                //Register
                                System.out.println("Enter CustomerId:");
                                int cid = Integer.parseInt(sc.nextLine());
                                System.out.println("Enter customer Name:");
                                String cname = sc.nextLine();
                                System.out.println("Enter customer Password:");
                                String cpass = sc.nextLine();
                                System.out.println("Enter Customer FirstName:");
                                String fname = sc.nextLine();
                                System.out.println("Enter Customer LastName:");
                                String lname = sc.nextLine();
                                System.out.println("Enter Customer Email id:");
                                String cemail = sc.nextLine();
                                System.out.println("Enter Customer Mobile Number:");
                                long cmob = Long.parseLong(sc.nextLine());

                                
                                int k = smt.executeUpdate("Insert into CustomerInfo values(" + cid + ",'" + cname + "','" + cpass + "','" + fname + "','" + lname + "','" + cemail + "'," + cmob + ")");
                                if (k > 0) {
                                    
                                    System.out.println("😂😂😂😂 Customer Data Inserted Successfully 😂😂😂😂");
                                }
                                break;
                            case 2:
                            	System.out.println("You r registred Succesfully please Login");
                            	System.exit(0);
                            default:
                               
                                System.out.println("Please enter a valid option");
                        }
                    }

                case 3:
                	System.out.println("Operation Ended");
                	System.exit(0);
                default:
                   
                    System.out.println("Invalid choice");
            }//End of main switch
 } //While for mainmenu
        } // end of main try block
        // Main catch block for handling errors in the connection or any other exceptions
        catch (Exception e) {
            System.out.println("Error while connecting to database");
            e.printStackTrace();
        }
    } // end of main method
    
    
}
