package jdbcprgm;

import java.sql.*;

public class Java {

    public static void main(String[] args) throws Exception {
    	readRecords();   
    	insertRecord();
    }
    
    public static void readRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3306/empdetails";
        String userName = "root";
        String password = "SQL@10";
        String query = "select * from EmployeeDetails"+ ""; 
        
        try (Connection con = DriverManager.getConnection(url, userName, password);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            
            while (rs.next()) {
                System.out.println("id is" + rs.getInt(1));
                System.out.println("Name" + rs.getString(2));
                System.out.println("department" + rs.getString(3));
                System.out.println("position" + rs.getString(4));
                System.out.println("salary" + rs.getFloat(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }      
            
    public static void insertRecord() throws Exception{
    	String url = "jdbc:mysql://localhost:3306/empdetails";
        String userName = "root";
        String password = "SQL@10";
        String query = "INSERT INTO EmployeeDetails (EmployeeID, Name, Department, Position, Salary_INR) VALUES (27, 'priya', 'sales', 'HR', 25000)";
        
        try{Connection con = DriverManager.getConnection(url,userName,password);
        Statement st=con.createStatement();
        int rs=st.executeUpdate(query);
        
        System.out.println("number of rows affected:"+rs);
        con.close();
        
    }catch(SQLException e) {
        e.printStackTrace();
    }
        
        
        
       
   
}}








