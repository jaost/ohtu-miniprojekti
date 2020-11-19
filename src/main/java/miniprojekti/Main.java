package miniprojekti;

import java.sql.SQLException;
import java.util.List;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

// Main tulee toimimaan Controllerina
public class Main {
    public static void main(String[] args) {
        System.out.println("Test");
        
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        
        if (process.environment().get("PORT") != null) {
        	port = Integer.parseInt(process.environment().get("PORT"));
        } else {
        	port = 4567;
        }
        
        Spark.port(port);
        
        Spark.get("/helloworld", (req, res) -> "Hello World");
    }
}
