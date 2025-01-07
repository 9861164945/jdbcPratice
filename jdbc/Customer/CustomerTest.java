package com.jdbc.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class CustomerTest
{
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
	
	try(sc;)
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");//Create connection
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "advjava", "advjava");//Conntecting to java with database

        Statement smt = con.createStatement();//creating jdbc statement
        while(true) 
        {
        	System.out.println("_______Operation choice________");
        	System.out.println("\t1.Add Customer"+"\n\t2.View All Customers"+"\n\t3.viewCustomersById"+"\n\t4.Exit");
 
        	System.out.println("ENter your chpoice:");
        	int choice=Integer.parseInt(sc.nextLine()); 
        	switch(choice) 
        	{
        	case 1:
        		System.out.println("Enter CustomerId:");
        		int id=Integer.parseInt(sc.nextLine());
        		System.out.println("Enter customer Name:");
        		String name=sc.nextLine();
        		System.out.println("ENter customer City:");
        		String city=sc.nextLine();
        		System.out.println("ENter Customer mailId:");
        		String eid=sc.nextLine();
        		System.out.println("Enter Customer Mobike number:");
        		long mob=Long.parseLong(sc.nextLine());
        		int k=smt.executeUpdate("Insert into Customer70 values("+id+",'"+name+"','"+city+"','"+eid+"',"+mob+")");
        		if(k>0) {
        			System.out.println("😂😂😂😂😂😂😂😂😂😂Customer Data Inserted Successfully😂😂😂😂😂😂😂😂😂😂");
        			}
        		break;
        	case 2:
                ResultSet rs = smt.executeQuery("select * from customer70");
                System.out.println("--------------Customer Details:----------------- ");
                while(rs.next()) 
                {
                	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getLong(5));
                }
        		break;
        	case 3:
        		System.out.println("Enter The CustomerId:");
        		int cid=Integer.parseInt(sc.nextLine());
        		ResultSet rs1=smt.executeQuery("Select * from Customer70 where cid="+cid+"");
        		if(rs1.next()) 
        		{
        			System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getString(4)+"\t"+rs1.getLong(5));
        			
        		}
        		break;
        	case 4:
        		System.out.println("😍😍😍Operation ended Thank You😍😍😍");
        		System.exit(0);
        	default:
        			System.out.println("😟😟😟😟😟😟😟Invalid Choice please try again 😟😟😟😟😟😟😟");
        			
        	}
        	con.close();
        	
        }
	}

	catch(Exception e) 
	{
		
		System.out.println("Connection Failed Due to");
		e.printStackTrace();
		
	}
}
}