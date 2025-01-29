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
	
	
	//Doctor table Operation:
	PreparedStatement psDoct=con.prepareStatement("Insert into doctor values(?,?,?,?,?,?,?,?)");
	
	
	
	while(true) 
	{
		System.out.println("-------------------------Hospital management System Operation choice ---------------------------");
		System.out.println("1.User_details Operation:");
		System.out.println("2.Doctor operations");
		System.out.println("3.Patient Operations");
		System.out.println("4.Medicine Store Operations");
		System.out.println("5.Medicine operations");
		System.out.println("6.Hospital Operation");
		System.out.println("7.exit");
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
			//Doctor
		case 2:
			System.out.println("Doctor Operation");
			
			System.out.println("1.Insert doctor");
			System.out.println("2.View all Doctors");
			System.out.println("3.View Doctor by Id");
			System.out.println("4.Update Doctor By Id(dmob,demail,dfees,department)");
			System.out.println("5.Delete By id");
			System.out.println("6.Exit");
			System.out.println("Enter choice:");
			int dchoice=Integer.parseInt(sc.nextLine());
			switch(dchoice)
			{
			case 1:
				System.out.println("Insertion");
				System.out.println("ENter Name of the doctor");
				String dname=sc.nextLine();
				System.out.println("Enter Doctor Id");
				int doid=Integer.parseInt(sc.nextLine());
				System.out.println("Enter Doctor Designation:");
				String ddesg=sc.nextLine();
				System.out.println("Enter Doctor Qualification");
				String dqual=sc.nextLine();
				System.out.println("Enter Doctor mobile number:");
				long dmob=Integer.parseInt(sc.nextLine());
				System.out.println("Enter Doctor Email:");
				String demail=sc.nextLine();
				System.out.println("Enter Doctor Fees:");
				double fees=Double.parseDouble(sc.nextLine());
				System.out.println("Enter Doctor Department:");
				String ddpt=sc.nextLine();
				psDoct.setString(1, dname);
				psDoct.setInt(2, doid);
				psDoct.setString(3, ddesg);
				psDoct.setString(4, dqual);
				psDoct.setLong(5,dmob);
				psDoct.setString(6, demail);
				psDoct.setDouble(7, fees);
				psDoct.setString(8, ddpt);
				int k=psDoct.executeUpdate();
				if(k>0)
				{
					System.out.println("Doctor Data Inserted Successfully");
					
				}
				else
				{
					System.err.println("Some thing went wrong while Inserting data");
					
				}
				
				break;
			case 2:
				System.out.println("View  Doctors");
				break;
			case 3:
				System.out.println("View Doctor by Id");
				break;
			case 4:
				System.out.println("Update by Id (dmob,demail,dfees,department)");
				break;
			case 5:
				System.out.println("delete by id");
				break;
			case 6:
				System.out.println("----Operation ended---");
				System.exit(0);
				break;
				default:
					System.out.println("Invalid choice Pla try again:");
				
			}
			break;
			//Patient
		case 3:
			System.out.println("Patient Operation");
			break;
			//Medicine Store
		case 4:
			System.out.println("Medicine store operation");
			break;
			//Medicine
		case 5 :
			System.out.println("Medicine Operation");
			break;
			//Hospital
		case 6:
			System.out.println("Hospital Operation:");
			System.out.println("Operations Are:");
			System.out.println("1.Insert Hospital");
			System.out.println("2.View all Hospital:");
			System.out.println("3.View By Department Name(d_name)");
			System.out.println("4.Update Name By department name(id,name ,address):");
			System.out.println("5.Delete By name:");
			System.out.println("6.Exit:");
			System.out.println("Enter Your Choice:");
			int hChoice=Integer.parseInt(sc.nextLine());
			switch(hChoice) 
			{
			case 1:
				System.out.println("Insertion Operation");
				System.out.println("Enter name of the hospital:");
				String hname=sc.nextLine();
				
					
				break;
			case 2:
				System.out.println("View all Doctor");
				
				break;
			case 3:
				System.out.println("View by Department");
			
				break;
			case 4:
				System.out.println("Update By Department name ");
				break;
			case 5:
				System.out.println("Delete by Department name");
				break;
			case 6:
				System.out.println("operation Ended");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Operation");
			}
			break;
			
			//Exit main
		case 7:
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
