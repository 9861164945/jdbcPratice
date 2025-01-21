package com.jdbc.Employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class Emp{
	int id;
	String name;
	int age;
	double sal;
	public Emp(int id, String name, int age, double sal) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sal = sal;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", age=" + age + ", sal=" + sal + "]";
	}
	
	
}


public class EmployeeDemo2 
{
		public static void main(String[] args) 
	{
		try {

    		Class.forName("oracle.jdbc.driver.OracleDriver");//Create connection
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "advjava", "advjava");//Conntecting to java with database
	        
            PreparedStatement ps= con.prepareStatement("insert into Employee2 values(?,?,?,?)");
            PreparedStatement ps1=con.prepareStatement("Select * from Employee2");
            PreparedStatement ps2 = con.prepareStatement("SELECT * FROM Employee2 WHERE age > 25");
            while(true) {
            	Scanner sc=new Scanner(System.in);
            	System.out.println("---Options are:-----");
            	System.out.println("1.Insert");
            	System.out.println("2.Retrieve");
            	System.out.println("3.List of emp older than 25 years");
            	System.out.println("ENter choice:");
            	int choice=sc.nextInt();
            	switch(choice)
            	{
            	case 1:
            		System.out.println("Data Insertion");
            		System.out.println("ENter EmpId:");
            		int eid=sc.nextInt();
            		sc.nextLine();
            		System.out.println("ENter employee Name:");
            		String name=sc.nextLine();
            		System.out.println("Enter Employee Age:");
            		int age=sc.nextInt();
            		System.out.println("ENter Employee Salary:");
            		double sal=sc.nextDouble();
            		ps.setInt(1, eid);
            		ps.setString(2, name);
            		ps.setInt(3, age);
            		ps.setDouble(4, sal);
            		int k=ps.executeUpdate();
            		if(k>0) {
            			System.out.println("Data Inserted Successfully");
            			
            		}
         
            		break;
            	case 2:
            		System.out.println("Data Retrivation");
            		ResultSet rs=ps1.executeQuery();
            		while(rs.next()) 
            		{
            		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getDouble(4));
            		
            		}
            		
            		
            		break;
            		
            	case 3:
            		
            		System.out.println("List of employees older than 25 years:");
            	   
            	    ResultSet rs2 = ps2.executeQuery();
            	    List<Emp> employees = new ArrayList<>();
            	    while (rs2.next()) {
            	        Emp emp = new Emp(rs2.getInt(1), rs2.getString(2), rs2.getInt(3), rs2.getDouble(4));
            	        employees.add(emp);
            	    }
            	    for (Emp emp : employees) {
            	        System.out.println(emp);
            	    }
            	    break;
            
            		default:
            			System.out.println("Invalid Choice Try Again");
            	
            	}
            }
            		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
	}

}
