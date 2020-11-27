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
                s.execute("CREATE TABLE IF NOT EXISTS tip ("
                        + "	id INTEGER NOT NULL,"
                        + "	otsikko VARCHAR(144),"
                        + "	type VARCHAR(144),"
                        + "	note VARCHAR(144),"
                        + "	PRIMARY KEY (id)"
                        + ")");
                s.execute("CREATE TABLE IF NOT EXISTS book ("
                        + "	id INTEGER NOT NULL,"
                        + "	tip_id INTEGER NOT NULL,"
                        + "	author VARCHAR(144),"
                        + "	isbn VARCHAR(144),"
                        + "	url VARCHAR(144),"
                        + "	PRIMARY KEY (id),"
                        + "	FOREIGN KEY(tip_id) REFERENCES tip (id)"
                        + ")");
                s.execute("CREATE TABLE IF NOT EXISTS video ("
                        + "	id INTEGER NOT NULL,"
                        + "	tip_id INTEGER NOT NULL,"
                        + "	url VARCHAR(144),"
                        + "	PRIMARY KEY (id),"
                        + "	FOREIGN KEY(tip_id) REFERENCES tip (id)"
                        + ")");
                s.execute("CREATE TABLE IF NOT EXISTS blogpost ("
                        + "	id INTEGER NOT NULL,"
                        + "	tip_id INTEGER NOT NULL,"
                        + "	url VARCHAR(144),"
                        + "	PRIMARY KEY (id),"
                        + "	FOREIGN KEY(tip_id) REFERENCES tip (id)"
                        + ")");
                s.execute("CREATE TABLE IF NOT EXISTS podcast ("
                        + "	id INTEGER NOT NULL,"
                        + "	tip_id INTEGER NOT NULL,"
                        + "	author VARCHAR(144),"
                        + "	description VARCHAR(144),"
                        + "	url VARCHAR(144),"
                        + "	PRIMARY KEY (id),"
                        + "	FOREIGN KEY(tip_id) REFERENCES tip (id)"
                        + ")");
            } else {
                s.execute("CREATE TABLE IF NOT EXISTS readingtip "
                        + "(id SERIAL PRIMARY KEY, "
                        + "author VARCHAR(255), "
                        + "title VARCHAR(255), "
                        + "url VARCHAR(255))");
                s.execute("CREATE TABLE IF NOT EXISTS tip ("
                        + "	id SERIAL PRIMARY KEY,"
                        + "	otsikko VARCHAR(255),"
                        + "	type VARCHAR(255),"
                        + "	note VARCHAR(255)"
                        + ")");
                s.execute("CREATE TABLE IF NOT EXISTS book ("
                        + "	id SERIAL PRIMARY KEY,"
                        + "	tip_id INT NOT NULL,"
                        + "	author VARCHAR(255),"
                        + "	isbn VARCHAR(255),"
                        + "	url VARCHAR(255),"
                        + "	FOREIGN KEY(tip_id) REFERENCES tip (id)"
                        + ")");
                s.execute("CREATE TABLE IF NOT EXISTS video ("
                        + "	id SERIAL PRIMARY KEY,"
                        + "	tip_id INT NOT NULL,"
                        + "	url VARCHAR(255),"
                        + "	FOREIGN KEY(tip_id) REFERENCES tip (id)"
                        + ")");
                s.execute("CREATE TABLE IF NOT EXISTS blogpost ("
                        + "	id SERIAL PRIMARY KEY,"
                        + "	tip_id INT NOT NULL,"
                        + "	url VARCHAR(255),"
                        + "	FOREIGN KEY(tip_id) REFERENCES tip (id)"
                        + ")");
                s.execute("CREATE TABLE IF NOT EXISTS podcast ("
                        + "	id SERIAL PRIMARY KEY,"
                        + "	tip_id INT NOT NULL,"
                        + "	author VARCHAR(255),"
                        + "	description VARCHAR(255),"
                        + "	url VARCHAR(255),"
                        + "	FOREIGN KEY(tip_id) REFERENCES tip (id)"
                        + ")");
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
