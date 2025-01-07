package com.employeeWithCreateTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;
public class employeeTest2 
{
	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");//Create connection
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "advjava", "advjava");//Conntecting to java with database

	        Statement smt = con.createStatement();//creating jdbc statement
	        //creating a table Schema With java
	        String createTableSQL = "CREATE TABLE TestEmp ("
                    + "empid NUMBER(10) PRIMARY KEY, "
                    + "ename VARCHAR2(30) NOT NULL, "
                    + "edesg VARCHAR2(20), "
                    + "ebasic NUMBER(10,2) CHECK (ebasic >= 12000), "
                    + "ehra NUMBER(10,2), "
                    + "eda NUMBER(10,2), "
                    + "total NUMBER(10,2))";
	        try {
	        	//execution
                smt.executeUpdate(createTableSQL);
                System.out.println("Table  created successfully.");
            } catch (SQLException e) {
                if (e.getErrorCode() == 955) { // ORA-00955: name is already used by an existing object
                    System.out.println("Table already exists. Skipping creation.");
                } else {
                    throw e;
                }
            }

	        
	        while(true) 
	        {
	        	System.out.println("_______Operation choice________");
	        	System.out.println("\t1.Add Employee"+"\n\t2.View All Employee"+"\n\t3.EmployeesById"+"\n\t4.Exit");
	 
	        	System.out.println("ENter your choice:");
	        	int choice=Integer.parseInt(sc.nextLine()); 
	        	switch(choice) 
	        	{
	        	case 1:
	        		System.out.println("Enter EmployeeId:");
	        		int id=Integer.parseInt(sc.nextLine());
	        		System.out.println("Enter Employee Name:");
	        		String name=sc.nextLine();
	        		System.out.println("ENter Employee designation");
	        		String job=sc.nextLine();
	        		System.out.println("Enter Employee Basic Salary");
	        		double basic=Double.parseDouble(sc.nextLine());
	        		 if (basic < 12000) {
                         throw new Exception("Basic Salary must be greater than or equal to 12000.");
                     }

	        		 double hra = basic * 0.96;
                     double da = basic * 0.61;
                     double gross = basic + hra + da;

	        		int k=smt.executeUpdate("Insert into testemp values("+id+",'"+name+"','"+job+"','"+basic+"',"+hra+","+da+","+gross+")");
	        		if(k>0) {
	        			System.out.println("😂😂😂😂😂😂😂😂😂😂Employee Data Inserted Successfully😂😂😂😂😂😂😂😂😂😂");
	        			}
	        		break;
	        	case 2:
	                ResultSet rs = smt.executeQuery("select * from testemp");
	                System.out.println("--------------Employee  Details:----------------- ");
	                while(rs.next()) 
	                {
	                	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getDouble(4)+"\t"+rs.getDouble(5)+"\t"+rs.getDouble(6)+"\t"+rs.getDouble(7));
	                }
	        		break;
	        	case 3:
	        		System.out.println("Enter The EmployeeId:");
	        		int eid=Integer.parseInt(sc.nextLine());
	        		ResultSet rs1=smt.executeQuery("Select * from Testemp where empid="+eid+"");
	        		if(rs1.next()) 
	        		{
	        			System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getDouble(4)+"\t"+rs1.getDouble(5)+"\t"+rs1.getDouble(6)+"\t"+rs1.getDouble(7));
	        		}
	        		break;
	        	case 4:
	        		System.out.println("😍😍😍Operation ended Thank You😍😍😍");
	        		con.close();
	        		System.exit(0);
	        		
	        	default:
	        			System.out.println("😟😟😟😟😟😟😟Invalid Choice please try again 😟😟😟😟😟😟😟");
	        			
	        	}
	        
	        	
	        }
		}

		catch(SQLIntegrityConstraintViolationException sq) 
		{ 
			System.out.println("😟😟😟Multiple Values with same empid not allowed Here!😟😟😟");
		
			sq.printStackTrace();
			
		}
		catch(Exception e) 
		{
			
			System.out.println("Connection Failed Due to");
			e.printStackTrace();
			
		}
	}
		
	
}
