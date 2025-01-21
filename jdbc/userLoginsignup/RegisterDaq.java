package com.jdbc.userLoginsignup;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterDaq {  // Class name should be RegisterDao for consistency
    public int k = 0;  // Variable to return the result (0 for failure, 1 for success)

    public int register(UserBean ub) {
        try {
            // Establish connection
            Connection con = dbonnection.getCon();  // Fix typo: 'dbonnection' should be 'dbConnection'

            // Prepare SQL query
            PreparedStatement ps = con.prepareStatement("INSERT INTO userreg69 (uName, uPass, fname, lName, city, eMid, phno) VALUES (?, ?, ?, ?, ?, ?, ?)");

            // Set parameters from the UserBean object
            ps.setString(1, ub.getuName());
            ps.setString(2, ub.getuPass());
            ps.setString(3, ub.getFname());
            ps.setString(4, ub.getlName());
            ps.setString(5, ub.getCity());
            ps.setString(6, ub.geteMid());
            ps.setLong(7, ub.getPhno());

            // Execute the update (insert) query
            k = ps.executeUpdate();  // If 1 row is affected, k will be set to 1

            if (k > 0) {
                System.out.println("Registration successful");
            } else {
                System.out.println("Registration failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return k;  // Return the result: 1 if successful, 0 if failed
    }
}
