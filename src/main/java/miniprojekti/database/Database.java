package miniprojekti.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.net.URI; 
import java.net.URISyntaxException;
import java.sql.Statement;

public class Database {
    
    private String databaseAddress;
    private boolean isLocal;
    
    public Database(String databaseAddress) {
        this.databaseAddress = databaseAddress;
        try (Connection connection = this.getConnection()) {
            Statement s = connection.createStatement();
            // PSQL needs SERIAL for autoincrementing but SQLITE doesn't recognize it
            if (isLocal) {
                s.execute("CREATE TABLE IF NOT EXISTS readingtip " +
                    "(id INTEGER PRIMARY KEY, " +
                    "author VARCHAR(255), " +
                    "title VARCHAR(255), " +
                    "url VARCHAR(255))");
            } else {
                s.execute("CREATE TABLE IF NOT EXISTS readingtip " +
                    "(id SERIAL PRIMARY KEY, " +
                    "author VARCHAR(255), " +
                    "title VARCHAR(255), " +
                    "url VARCHAR(255))");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Connection getConnection() throws Exception {

        // If we are in Heroku, use the environment variable for database URI
        // Otherwise use the set local address
        if (System.getenv("DATABASE_URL") != null) {
            Class.forName("org.postgresql.Driver");
            // Parse URI for credentials
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";
            
            System.out.println(dbUrl + " : " + username + " : " + password);
            this.isLocal = false;
            return DriverManager.getConnection(dbUrl, username, password);
        }
        this.isLocal = true;
        return DriverManager.getConnection(databaseAddress);
    }
}
