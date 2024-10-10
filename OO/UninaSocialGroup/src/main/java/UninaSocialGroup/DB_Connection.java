package UninaSocialGroup;
import java.sql.*;

public class DB_Connection {

    public Connection c;

    private String driver;
    private String URL;
    private String User;
    private String PSW;

    public DB_Connection(String uRL, String user, String psw) {
        
    	driver = "oracle.jdbc.OracleDriver";
    	URL = uRL;
    	User = user;
    	PSW = psw;
    	
    }
    
    public Connection getC() {
        return c;
    }

    public void connect() {
        try {
            // REGISTRAZIONE DEL DRIVER
            Class.forName(driver);    
            
            // CONNESSIONE        
            c = DriverManager.getConnection(URL, User, PSW);
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
            Statement st = c.createStatement();
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
            PreparedStatement update = c.prepareStatement(comando); 
            update.executeUpdate(comando); 
            update.close();
        } catch (SQLException se) {
            System.out.println("Update fallito");
            se.printStackTrace();
        }
    }

    public void ExeUpdatePreparedeStatement(String comando) {
        try {
            PreparedStatement update = c.prepareStatement(comando); 
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
            c.close();
        } catch (SQLException e) {
            System.out.println("Connessione fallita");
            e.printStackTrace();
        }
    }

}
