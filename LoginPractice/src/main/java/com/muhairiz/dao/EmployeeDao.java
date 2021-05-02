package com.muhairiz.dao;
import java.sql.*;

import com.muhairiz.model.Employee;

public class EmployeeDao {
	
	public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost/test","root","");
        }catch(Exception e){System.out.println(e);}
        return con;
    }

 public Employee checkLogin(String username,String password) throws SQLException {
		 
		 Connection con=EmployeeDao.getConnection();
		 String sql ="select * from employee where username=? and password=?";
		 PreparedStatement ps=con.prepareStatement(sql);
		 
		 ps.setString(1,username);
		 ps.setString(2,password);
		 ResultSet result =ps.executeQuery();
		 
		 Employee emp=null;
		
		if(result.next()) {
			emp= new Employee();
			emp.setName(result.getString("name"));
			emp.setUsername(result.getString("username"));
			emp.setPassword(result.getString("password"));
			emp.setRoll(result.getString("roll"));
		}
		con.close();
		return emp;
		 
	 }

	
}
