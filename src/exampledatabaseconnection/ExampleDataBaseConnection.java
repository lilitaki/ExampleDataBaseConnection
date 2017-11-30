/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exampledatabaseconnection;
import java.sql.*;
/**
 *
 * @author Vasiliki
 */
public class ExampleDataBaseConnection {

    /**
     * @param args the command line arguments
     */
    
       static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
       static final String DB_URL = "jdbc:mysql://localhost/katalogos";
       static final String USER = "root";
       static final String PASS = "pitsakos14";

    public static void main(String[] args) {
        
        Connection conn = null;
        Statement stmt = null; 
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connecting to database");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Creating statement...");
            stmt = conn.createStatement(); 
            String sql;
            
            sql = "SELECT * FROM members";
            ResultSet rs = stmt.executeQuery(sql); 
            sql = "insert into members (LName, FName, Tel1, Tel2) values ('FOUNTOUKIDOU', 'IOANNA', '6982545793', '2145698726')";
            stmt = conn.createStatement(); // to vazoume ksana. ekleise to apo panw molis ektelestike h select *
            int i = stmt.executeUpdate(sql);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Give me your last name: ");
            String lastname = scanner.next();
            System.out.println("Give me your first name: ");
            String firstname = scanner.next();
            sql = "SELECT * FROM members WHERE LName= '" + lastname + "' and FName = '" + firstname+ "'";
            rs = stmt.executeQuery(sql);
          
            
            sql = "CREATE TABLE `katalogos`.`birthdays` ("
                    + "`usersid` int(11) not null auto_increment," +
                    "`bd` varchar(45) not null," + "foreign key (`usersid`) references members(`ID`))" + 
                    "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8";
            stmt = conn.createStatement();
            int i = stmt.executeUpdate(sql);
            
            while (rs.next()) { 
                int id = rs.getInt("id");
                String LName = rs.getString ("LName");
                String FName = rs.getString("FName");
                String Tel1 = rs.getString("Tel1");
                String Tel2 = rs.getString("Tel2");
                
                System.out.println("ID " + id);
                System.out.println(", Last Name: " + LName);
                System.out.println(", First Name: " + FName);
                System.out.println(", Tel1: " + Tel1);
                System.out.println(", Tel2: " + Tel2);
                
            }
           
              
            
        }
        catch(SQLException se) {
            
            se.printStackTrace();
            
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            
            
            try {
                if (stmt!=null)
                    stmt.close();
                
            }
            catch(SQLException se2){}
            
            try {
                if (conn!=null)
                    conn.close();
                
            }
            catch(SQLException se) {
                se.printStackTrace();
            }
        }
        
        System.out.println("Finished");
    }      
}
