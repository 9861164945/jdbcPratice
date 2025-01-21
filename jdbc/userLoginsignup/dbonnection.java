package com.jdbc.userLoginsignup;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbonnection
{
	private static Connection con=null;

	private void dbConnection() {}
	static {
		try {
			Class.forName(Dfinfo.driver);
			con=DriverManager.getConnection(Dfinfo.dbUrl,Dfinfo.dbName,Dfinfo.dbPass);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static  Connection getCon() {
		return con;
	}
}
