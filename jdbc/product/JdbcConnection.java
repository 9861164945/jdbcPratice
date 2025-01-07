package com.jdbc.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcConnection 
{
	public static void main(String[] args) 
{
		Scanner sc=new Scanner(System.in);
        try(sc;) {
            Class.forName("oracle.jdbc.driver.OracleDriver");//Loading Driver
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "advjava", "advjava");//Conntecting to java with database

            Statement smt = con.createStatement();//creating jdbc statement


            while(true) 
            {
            	System.out.println("_______Operation choice________");
            	System.out.println("\t1.Add Product"+"\n\t2.View All Products"+"\n\t3.viewProductsById"+"\n\t4.Exit");
     
            	System.out.println("ENter your chpoice:");
            	int choice=Integer.parseInt(sc.nextLine()); 
            	switch(choice) 
            	{
            	case 1:
            		System.out.println("Enter Product Id:");
            		int id=Integer.parseInt(sc.nextLine());
            		System.out.println("Enter Product Name:");
            		String name=sc.nextLine();
            		System.out.println("Enter Product Price:");
            	    int price=Integer.parseInt(sc.nextLine());
            	    System.out.println("Enter qty For my order");
            	    int qty=Integer.parseInt(sc.nextLine());
            	    int k=smt.executeUpdate("Insert into product70 values("+id+",'"+name+"',"+price+","+qty+")");
            		if(k>0) {
            			System.out.println("😂😂😂😂😂😂😂😂😂😂Product Data Inserted Successfully😂😂😂😂😂😂😂😂😂😂");
            			}
            		break;
            	case 2:
            		ResultSet rs = smt.executeQuery("select * from product70");
            		System.out.println("------------Product Details--------------");
            		 while(rs.next()) 
                     {
                     	System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getInt(4));
                     }
            		break;
            	case 3:
            		System.out.println("Enter productId:");
            		int pid=Integer.parseInt(sc.nextLine());
            		ResultSet rs1=smt.executeQuery("Select * from Product70 where  PRODUCT_ID="+pid+"");
            		while(rs1.next()) 
                    {
                    	System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getInt(3)+"\t"+rs1.getInt(4));
                    }
            		
            		break;
            	case 4:
            		System.out.println("😍😍😍Operation ended Thank you 😍😍😍");
            		con.close();
            		System.exit(0);
            		
            		
            	default:
            		System.out.println("😟😟😟😟😟😟😟Invalid Choice please try again 😟😟😟😟😟😟😟");
            		
            	
            	}
            	
            	
               

            	
            }
        
        }
        catch (Exception e) {
        	e.printStackTrace();
        	
        }
	
		
	}

}
