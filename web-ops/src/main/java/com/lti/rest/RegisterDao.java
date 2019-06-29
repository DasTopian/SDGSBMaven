package com.lti.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.lti.entity.Register;

public class RegisterDao {
	
	public void save(Register register) {
		Connection conn = null; //manages the connection between the app and db
		PreparedStatement stmt = null; //helps us to execute SQL statements
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "hr", "hr");
			
			String sql = "insert into TBL_REG values(?,?,?,?,cust_seq.nextval)";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, register.getEmail());
			stmt.setString(2, register.getName());
			
			stmt.setString(3, register.getUsername());
			stmt.setString(4, register.getPassword());
			stmt.executeUpdate(); //any DML statement
		}
		catch(ClassNotFoundException e) {
			System.out.println("JDBC driver not found");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}	

	}
}
