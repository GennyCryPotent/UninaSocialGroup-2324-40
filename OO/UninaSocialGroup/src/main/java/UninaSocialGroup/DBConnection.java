package UninaSocialGroup;
import java.sql.*;

public class DBConnection {

    public Connection connection;

    private String driver;
    private String url;
    private String user;
    private String psw;

    public DBConnection(String uRL, String user, String psw) {
        
    	driver = "oracle.jdbc.OracleDriver";
    	this.url = uRL;
    	this.user = user;
    	this.psw = psw;
    	
    }
    
    public Connection getC() {
        return connection;
    }

    public void connect() {
        try {
            // REGISTRAZIONE DEL DRIVER
            Class.forName(driver);    
            
            // CONNESSIONE        
            connection = DriverManager.getConnection(url, user, psw);
            System.out.println("Connessione riuscita");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trovato");
            e.printStackTrace();
        } catch (SQLException se) {
            System.out.println("Connessione fallita");
            se.printStackTrace();
        }
    }

    public ResultSet ExeQuery(String query) {
        try {
            // ESEGUIRE UNA QUERY
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (SQLException se) {
            System.out.println("Query fallita");
            se.printStackTrace();
            return null;
        }
    }

    public void ExeUpdate(String comando) {
        try {
            // ESEGUIRE UN UPDATE
            PreparedStatement update = connection.prepareStatement(comando); 
            update.executeUpdate(comando); 
            update.close();
        } catch (SQLException se) {
            System.out.println("Update fallito");
            se.printStackTrace();
        }
    }

    public void ExeUpdatePreparedeStatement(String comando) {
        try {
            PreparedStatement update = connection.prepareStatement(comando); 
            update.setString(1, "giuseppe");
            update.executeUpdate(); 
            update.close();
        } catch (SQLException e) {
            System.out.println("Update fallito");
            e.printStackTrace();
        } 
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connessione fallita");
            e.printStackTrace();
        }
    }

}
