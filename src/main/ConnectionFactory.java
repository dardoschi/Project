package main;

import java.sql.*;

//get connection restituisce un oggetto Connection		   come usare:Connection connection = ConnectionFactory.getConnection();
public class ConnectionFactory {
	
	Controller ctrl;
	public static final String url = "jdbc:postgresql://localhost/StudentModifier1";
    public static final String username = "postgres";
    public static final String password = "password";
    
    
    //costruttore con controller
    public ConnectionFactory(Controller c) {
    	ctrl = c;
    }
    
    
    //metodo per creare una singola connessione riutilizzabile
    public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			}
		catch(ClassNotFoundException e1) {
			System.err.println("Classe non trovata");
		}
			Connection conn = null;
		try {
			conn = DriverManager.getConnection(url,username, password);
			}
		catch(SQLException e2) {
			System.err.println("Errore di connessione");
		}
		return conn;
      }
}
