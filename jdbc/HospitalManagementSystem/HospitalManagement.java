package com.jdbc.HospitalManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.stream.Gatherer.Integrator;

public class HospitalManagement 
{
	public static void main(String[] args) 
	{
	Scanner sc=new Scanner(System.in);
	try(sc;)
	{
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl","advjava","advjava");
	//UserTable Operation:
	PreparedStatement psUser=con.prepareStatement("insert into user_details values(?,?,?,?)");
	PreparedStatement psUser1=con.prepareStatement("Select * from user_details");
	PreparedStatement psUser2=con.prepareStatement("select * from user_details where userid=?");
	PreparedStatement psUser3=con.prepareStatement("update user_details set email=?,name=?,password=? where userid=?");
	PreparedStatement psUser4=con.prepareStatement("delete from user_details where userid=?");
	
	
	
	
	
	while(true) 
	{
		System.out.println("-------------------------Hospital management System Operation choice ---------------------------");
		System.out.println("1.User_details Operation:");
		System.out.println("2.Doctor operations");
		System.out.println("3.Patient Operations");
		System.out.println("4.Medicine Store Operations");
		System.out.println("5.Medicine operations");
		System.out.println("6.exit");
		System.out.println("Enter your choice:");
		int mainchoice=Integer.parseInt(sc.nextLine());
		//main switch
		switch(mainchoice) 
		{
		case 1:
			System.out.println("User_details Operations");
			System.out.println("1.Insert ");
			System.out.println("2.View Users");
			System.out.println("3.View By Id:");
			System.out.println("4.Update email and  password by Id");
			System.out.println("5.Delete By Id");
			System.out.println("6.Exit");
			System.out.println("Enter Your choice ");
			int userchoice=Integer.parseInt(sc.nextLine());
			//user-details switch case
			switch(userchoice) 
			{
			
			case 1:
				System.out.println("-------Insertion Operation-----");
				System.out.println("Enter UserId");
				int id=Integer.parseInt(sc.nextLine());
				System.out.println("ENter user Email:");
				String email=sc.nextLine();
				System.out.println("ENter username:");
				String name=sc.nextLine();
				System.out.println("ENter your password");
				String pass=sc.nextLine();
				psUser.setInt(1, id);
				psUser.setString(2, email);
				psUser.setString(3, name);
				psUser.setString(4, pass);
				int k=psUser.executeUpdate();
				if(k>0)
				{
					System.out.println("User_details Inserted Successfully:");
					
				}
				else
				{
					throw new Exception("something Went Wrong While Insertion");
					
				}
				
				break;
			case 2:
				System.out.println("---------------------View all users at a time------------");
				ResultSet rs=psUser1.executeQuery();
				while(rs.next())
				{
					
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4));
					
				}
			
				break;
			case 3:
				System.out.println("View user By their Id");
				System.out.println("Enter User Id:");
				int sid=Integer.parseInt(sc.nextLine());
				psUser2.setInt(1, sid);
				ResultSet rs1=psUser2.executeQuery();
				if(rs1.next()) 
				{
					

					System.out.println(rs1.getInt(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getString(4));
					
				}
				else
				{
					System.err.println("User with this id does not exists in the database");
					
				}
				
				break;
			case 4:
				System.out.println("Update User_details By their Id");
				System.out.println("ENter User id:");
				int upid=Integer.parseInt(sc.nextLine());
				psUser2.setInt(1, upid);
				ResultSet rs2=psUser2.executeQuery();
				if(rs2.next()) 
				{
					System.out.println("Previous EmailId: "+rs2.getString(2));
					System.out.println("Enter New Email for Updation:");
					String uemail=sc.nextLine();
					System.out.println("Prvious Username is:"+rs2.getString(3));
					System.out.println("ENter new Name for updation");
					String uname=sc.nextLine();
					System.out.println("Previous Password is"+rs2.getString(4));
					System.out.println("ENter new Password For updation");
					String upass=sc.nextLine();
					psUser3.setString(1, uemail);
					psUser3.setString(2, uname);
					psUser3.setString(3, upass);
					psUser3.setInt(4, upid);
					int updateSuccess=psUser3.executeUpdate();
					if(updateSuccess>0)
					{
						System.out.println("User details updated successfully");
						
					}
					else
					{
						System.err.println("Something went Wrong while updation");
					}
						}
				else {
					System.err.println("User is not avaliable in the database");
				}
				break;
			case 5:
				System.out.println("--------------Delation Operation----------");
				System.out.println("Enter UserId  to delate that user");
				int did=Integer.parseInt(sc.nextLine());
				psUser2.setInt(1, did);
				ResultSet rs3=psUser2.executeQuery();
				if(rs3.next()) 
				{
					psUser4.setInt(1, did);
					int dlt=psUser4.executeUpdate();
					if(dlt>0)
					{
						System.out.println("User details Deleted successfully");
						
						
					}
					else 
					{
						System.out.println("Something went Wrong while deletion ");
						
						
					}
				}
				else
				{
					System.err.println("User is not avaliable in the database");
					
					
				}
				break;
			case 6:
				System.out.println("Operation Ended ");
				break;
				default:
					System.out.println("Invlaid choice try again!");
			
			}//user _details switch end
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5 :
			break;
		case 6:
			System.out.println("---------Operation Ended-----");
			System.exit(0);
			default:
				System.out.println("Invalid choice Try again");
		
		
		
		}//end of main switch
		
		
	}
	
	}
	catch(Exception e) {
		e.printStackTrace();
	}
		
	}

}
