/*
 * Sql connection class
 */
import java.sql.*;
import javax.swing.*;


public class sqlConnection {
	Connection conn = null ;
	
	public static Connection dbConnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ozant\\Documents\\GitHub\\SoftwareEngineering\\SoftwareProject\\database\\OSGB.sqlite");
			JOptionPane.showMessageDialog(null, "Succesfully Connected");
			
			return conn;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	
}
