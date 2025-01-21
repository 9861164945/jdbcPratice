package com.jdbc.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProductTest
{

    public static void main(String[] args)
    {
    	try 
    	{
    		
    		Class.forName("oracle.jdbc.driver.OracleDriver");//Create connection
	        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "advjava", "advjava");//Conntecting to java with database

    		PreparedStatement ps=con.prepareStatement("Insert into product values(?,?,?,?)");
    		PreparedStatement ps1=con.prepareStatement("Select * from product");
    		PreparedStatement ps2=con.prepareStatement("select * from product",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    		while(true)
    		{
    			System.out.println("-----------Options are:---------");
    			System.out.println("1. Insert product details into product table.");
    			System.out.println("2. Retrieve product details in forward direction.");
    			System.out.println("3. Retrieve product details in reverse direction.");
    			System.out.println("4. Retrieve 3rd record from top.");
    			System.out.println("5. Retrieve 3rd record from bottom.");
    			System.out.println("6. Retrieve last three records from product table.");

    			Scanner sc=new Scanner(System.in);
    			System.out.println("----------Enter Your choice:----------------");
    			int choice=sc.nextInt();
    			switch(choice)
    			{
    			case 1:
    				System.out.println("---------Inserting Details------------");
    				System.out.println("ENter ProductId:");
    				int pid=sc.nextInt();
    				sc.nextLine();
    				System.out.println("Enter Product Name:");
    				String pname=sc.nextLine();
    				System.out.println("ENter produuct price:");
    				double pPrice=sc.nextDouble();
    				System.out.println("ENter Product qty:");
    				int pQty=sc.nextInt();
    				ps.setInt(1, pid);
    				ps.setString(2, pname);
    				ps.setDouble(3, pPrice);
    				ps.setInt(4, pQty);
    				int k=ps.executeUpdate();
				if(k>0) 
				{
					System.out.println("Succesfully Data Inserted !");
					
				}
					break;
    			case 2:
    				System.out.println("---------Retrieving Product Details In forward Direction---------");
    				ResultSet rs=ps1.executeQuery();
    				while(rs.next()) 
    				{
    					
    					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));

    					
    				}
    				break;
    				
    			case 3:
    				System.out.println("-------------Retrieving ProductDetails in forward Direction-------------");
    				ResultSet rs1=ps2.executeQuery();
    				while(rs1.next()) 
    				{

    					System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getDouble(3)+"\t"+rs1.getInt(4));	
    				}
    				System.out.println("----------Retrieving product detais reverse order-----------");
    				
    				while(rs1.previous())
    				{

    				System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getDouble(3)+"\t"+rs1.getInt(4));	
    			
    				}
    				break;
    			case 4:
    				System.out.println("-------------3rd Record From Top----------");
    				ResultSet rs3=ps2.executeQuery();
    				rs3.next();
    				if(rs3.absolute(3))
    				{

        				System.out.println(rs3.getInt(1)+"\t"+rs3.getString(2)+"\t"+rs3.getDouble(3)+"\t"+rs3.getInt(4));	
        			
    					
    				}
    				break;
    			case 5:
    				System.out.println("-------------3rd record from bottom------------");
    		    	  ResultSet rs4 = ps2.executeQuery();
    		    	  rs4.afterLast();
    		    	  if(rs4.absolute(-3))
    		    	  {
    		    		  System.out.println(rs4.getInt(1)+"\t"+rs4.getString(2)+"\t"+rs4.getDouble(3)+"\t"+rs4.getInt(4));  
    		    	  }
    		    	  break;
    			case 6:
    				System.out.println("--------------Last Three Record  From peoduct Table--------------");
    				ResultSet rs5=ps2.executeQuery();
    				rs5.afterLast();
    				int count=0;
    				while(rs5.previous()) 
    				
    				{
    					count++;
    		    		  System.out.println(rs5.getInt(1)+"\t"+rs5.getString(2)+"\t"+rs5.getDouble(3)+"\t"+rs5.getInt(4));  
    		    	      if(count==3)
    		    	    	  break;

    					
    					
    				}
    				break;
    				
    				default:
    					System.out.println("----------------------Invalid Choice please  try Again------------");
    			}
    			sc.close();
    			
    		}
    		
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    		
    	}
    }
    }

