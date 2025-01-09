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
	        String createTable = "CREATE TABLE EmpInfo (" + "empid NUMBER(10) PRIMARY KEY, "
                    + "empname VARCHAR2(30) NOT NULL, "
                    + "empSalary VARCHAR2(20), "
                    + "empAddress varchar2(30), "
                    + "empEmail varchar2(20), " + "empMobNumber number(10)"+")";
	        try {
	        	//execution
                smt.executeUpdate(createTable);
                System.out.println("Table  created successfully.");
            } catch (SQLException e) {
                if (e.getErrorCode() == 955) { // ORA-00955: name is already used by an existing object
                    System.out.println("Table already exists  u r not allow to Creating a duplicate Table");
                } else {
                    throw e;
                }
            }

	        
	        while(true) 
	        {
	        	System.out.println("_______Operation choice________");
	        	System.out.println("\t1.Add Employee"+"\n\t2.View All Employee"+"\n\t3.ViewEmployeesById"+"\n\t4.UpdateEmployeeById"+"\n\t5.FindEmployeeStartsWith'A':"+"\n\t6.CountEmployee"+"\n\t7.DeleteEmployeeBySalary"+"\n\t8.Exit");
	 
	        	System.out.println("ENter your choice:");
	        	int choice=Integer.parseInt(sc.nextLine()); 
	        	switch(choice) 
	        	{
	        	case 1:
	        		System.out.println("Enter EmployeeId:");
	        		int id=Integer.parseInt(sc.nextLine());
	        		System.out.println("Enter Employee Name:");
	        		String name=sc.nextLine();
	        		System.out.println("Enter Employee Salary:");
	        		double salary=Double.parseDouble(sc.nextLine());
	        		System.out.println("ENter Employee  Address:");
	        		String address=sc.nextLine();
	        		System.out.println("Enter Employee Mail Id");
	        		String email=sc.nextLine();
	        		System.out.println("Enter Employee PhoneNumber:");
	        		long mob=Long.parseLong(sc.nextLine());
	        		
	        		 

	        		int k=smt.executeUpdate("Insert into EmpInfo values("+id+",'"+name+"',"+salary+",'"+address+"','"+email+"',"+mob+")");
	        		if(k>0) {
	        			System.out.println("😂😂😂😂😂😂😂😂😂😂Employee Data Inserted Successfully😂😂😂😂😂😂😂😂😂😂");
	        			}
	        		break;
	        	case 2:
	                ResultSet rs = smt.executeQuery("select * from EmpInfo");
	                System.out.println("--------------Employee  Details:----------------- ");
	                while(rs.next()) 
	                {
	                	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5)+"\t"+rs.getLong(6));
	                }
	        		break;
	        	case 3:
	        		System.out.println("Enter The EmployeeId:");
	        		int eid=Integer.parseInt(sc.nextLine());
	        		ResultSet rs1=smt.executeQuery("Select * from EmpInfo where empid="+eid+"");
	        		if(rs1.next()) 
	        		{
	        			System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getDouble(3)+"\t"+rs1.getString(4)+"\t"+rs1.getString(5)+"\t"+rs1.getLong(6));
	        		}
	        		break;
	        		
	        	case 4:
	        		System.out.println("Enter EmpId U  by which Y can update Employee Details:");
	        		int uid=Integer.parseInt(sc.nextLine());
	        		ResultSet rs3=smt.executeQuery("Select * from EmpInfo Where empid="+uid+"");
	        		if(rs3.next()) 
	        		{
	        			System.out.println("Previous salary Of the Employeee"+rs3.getDouble(3));
	        			System.out.println("Enter New Salary:");
	        			double sal=Double.parseDouble(sc.nextLine());
	        			System.out.println("Previous Email Id:"+rs3.getString(5));
	        			System.out.println("ENter New Email Id:");
	        			String nemid=sc.nextLine();
	        			System.out.println("Previous Mobile Number:"+rs3.getLong(6));
	        			System.out.println("Enter new Mobile number for updation:");
	        			long nmob=Long.parseLong(sc.nextLine());
	        			int uquery=smt.executeUpdate("Update Empinfo set Empsalary="+sal+",EmpEmail='"+nemid+"',EmpMobNumber="+nmob+"");
	        			if(uquery>0)
	        			{
	        			System.out.println("😂😂😂😂😂😂😂😂😂😂Employee Updated Successfully😂😂😂😂😂😂😂😂😂😂");	
	        			}
	        			else {
	        				System.out.println("😟😟😟😟😟😟😟Invlaid EMployee Id 😟😟😟😟😟😟😟");
	        			}
	        			
	        		}
	        		
	        		break;
	        	case 5:
	        		System.out.println("Find EmployeeStarts With'A'");
	        		ResultSet rs4=smt.executeQuery("Select * from EMpinfo where empname like 'a%' ");
	        		if(rs4.next()) 
	        		{
	        			
	        			System.out.println(rs4.getInt(1)+"\t"+rs4.getString(2)+"\t"+rs4.getDouble(3)+"\t"+rs4.getString(4)+"\t"+rs4.getString(5)+"\t"+rs4.getLong(6));
	
	        		}
	        
	        		
	        		
	        		break;
	        	case 6:
	        		
	        		ResultSet rs5 = smt.executeQuery("SELECT count(*) AS total_no_of_emp FROM empinfo");

	        		if (rs5.next()) {
	        		    
	        		    int noofEmp = rs5.getInt("total_no_of_emp");  
	        		    System.out.println("Total No of Employees = "+noofEmp);
	        		}
	        		
	        		
	        		break;
	        	case 7:
	        		System.out.println("Delete Employee By maxSalary:");
	        		ResultSet rs6 = smt.executeQuery(" delete from empinfo where empsalary=(select max(empsalary) from empinfo)");
	        		if(rs6.next()) 	
	        		{
	        			System.out.println("Employee With Highest Salary Deleted ");
	        			
	        		
	        		}
	        		else {
	        			System.err.println("Employee With highest salary Not avaliable");
	        		}
	        		break;
	        	case 8:
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
