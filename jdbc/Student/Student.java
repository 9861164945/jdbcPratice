package com.jdbc.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Student {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        try (sc;) 
       
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "advjava", "advjava");
            PreparedStatement ps1 = con.prepareStatement("insert into studentdb values(?,?,?,?,?)"); 
            PreparedStatement ps2=con.prepareStatement("Select * from Studentdb");
            PreparedStatement ps3=con.prepareStatement("select * from studentdb where sroll_number=?");
            PreparedStatement ps4=con.prepareStatement("update Studentdb set SEMAIL=?,SMOB=? where sroll_number=? ");
            PreparedStatement ps5=con.prepareStatement("delete from studentdb where sroll_number=?");
          while(true) 
          {
        	  System.out.println("-------Operation choice------------");
        	  System.out.println("1.Add student ");
        	  System.out.println("2.View All Student:");
        	  System.out.println("3.View Student By Roll:");
        	  System.out.println("4.Update Student by Roll:(Email,mob)");
        	  System.out.println("5.Delete Student By Roll:");
        	  System.out.println("6.Exit");
        	  System.out.println("Enter Your Choice :");
        	  int choice=Integer.parseInt(sc.nextLine());
        	  switch(choice) 
        	  {
        	  case 1:
        		  System.out.println("Enter Student Roll Number");
        		  int id=Integer.parseInt(sc.nextLine());
        		  System.out.println("Enter Student Name:");
        		  String name=sc.nextLine();
        		  System.out.println("Enter Student Email");
        		  String email=sc.nextLine();
        		  System.out.println("Enter Student Mobile Number:");
        		  long mob=Long.parseLong(sc.nextLine());
        		  System.out.println("ENter Student Branch");
        		  String branch=sc.nextLine();
        		  
        		  
        	  ps1.setInt(1, id);
        	  ps1.setString(2, name);
        	  ps1.setString(3, email);
        	  ps1.setLong(4, mob);
        	  ps1.setString(5, branch);
        	  int k=ps1.executeUpdate();
        	  if(k>0) 
        	  {
        		System.out.println("Student Details Added Successfully");
        		
        	  }
        	  else 
        	  {
        		  System.err.println("Incvalid data ");
        		  
        	  }
        	  break;
        	  case 2:
        		  ResultSet rs1=ps2.executeQuery();
        		  System.out.println("------Student Details-----");
        		  while(rs1.next()) 
        		  {
        			  System.out.println(rs1.getInt(1)+"\t"
        		  +rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getLong(4)+"\t"+rs1.getString(5));
        			  
        			  
        		  }
        		  break;
        	  case 3:
        		  System.out.println("Enter Roll number To fetch Details of student:");
        		  int stid=Integer.parseInt(sc.nextLine());
        		  ps3.setInt(1, stid);
        		  ResultSet rs2=ps3.executeQuery();
        		  while(rs2.next()) 
        		  {
        			  System.out.println(rs2.getInt(1)+"\t"
        	        		  +rs2.getString(2)+"\t"+rs2.getString(3)+"\t"+rs2.getLong(4)+"\t"+rs2.getString(5)); 			           			  
        		  }
        		  break;
        	  case 4:
        		  System.out.println("Enter Student Roll number for updation ");
        		  int sid=Integer.parseInt(sc.nextLine());
        		 
        		  ps3.setInt(1, sid);
        		  ResultSet rs4=ps3.executeQuery();
        		  if(rs4.next()) 
        		  {
        			 System.out.println("The existing Email is :"+rs4.getString(3));
         			 System.out.println("Enter a New Email Id:");
         			 String nemail=sc.nextLine();
        			 System.out.println("The Existing Mobile number is"+rs4.getLong(4));
        			 System.out.println("Enter a new Mobile Number:");
        			 long nmob=Long.parseLong(sc.nextLine());
        			 ps4.setString(1, nemail);  // Setting the new email (index 1)
        		     ps4.setLong(2, nmob);       // Setting the new mobile number (index 2)
        		     ps4.setInt(3, sid);
        			 int success=ps4.executeUpdate();
        		 if(success>0)
        		 {
        			 System.out.println("Updated SuccessFully:");
        			 
        		 }
        		 else
        		 {
        			 System.err.println("Something Went Wrong While updation");
        			 
        		 }
        							   
        		 }
        		  else
        		  {
        			  System.err.println("Error While Updating by Roll number");
        		  }
        		  		
        		  
        		  
        		  break;
        	  case 5:
        		  System.out.println("Enter Student roll number u want to delete:");
        		  int did=Integer.parseInt(sc.nextLine());
        		  
        		  ps3.setInt(1,did);
        		  ResultSet rs5=ps3.executeQuery();
        		  if(rs5.next())
        		  {
        			  ps5.setInt(1, did);
        			  
        			  int dlt=ps5.executeUpdate();
        			  if(dlt>0)
        			  {
        				  
        				  System.out.println("Student Details Deleted Succesfully");
        				  
        			  }
        			  else 
        			  {
        				  System.err.println("Data Deletion Not Successfull");
        				  
        			  }
        		  }
        		  
        		  
        		  break;
        	  case 6:
        		  System.out.println("Operation Ended");
        		  System.exit(0);
        		  break;
        		  default:
        			  System.out.println("Invalid Chooice Try again");
        			  
        		  
        	  }
        	  
        	  
          }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}