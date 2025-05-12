package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
	
	private static String bd = "hotel";
    private static String parametros = "?useSSL=false&serverTimezone=UTC";
    private static String user = "root";
    private static String password = "trebujena";
    private static String url = "jdbc:mysql://localhost:3306/" + bd + parametros;
    
    private DbConnection () {
    	
    }
    
    public static Connection getConnection() throws SQLException {
    	/* try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } */
    	return DriverManager.getConnection(url, user, password);
    }
    

}
