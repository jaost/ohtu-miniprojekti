package miniprojekti.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.net.URI; 
import java.net.URISyntaxException;

public class Database {
    
    private String databaseAddress;
    
    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
    }
    
    public Connection getConnection() throws Exception {

        // If we are in Heroku, use the environment variable for database URI
        // Otherwise use the set local address
        if (System.getenv("DATABASE_URL") != null) {
            // Parse URI for credentials
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
            
            System.out.println(dbUrl + " : " + username + " : " + password);
            return DriverManager.getConnection(dbUrl, username, password);
        }
        return DriverManager.getConnection(databaseAddress);
    }
}
