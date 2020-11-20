package miniprojekti;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import static spark.Spark.get;
import spark.template.velocity.VelocityTemplateEngine;

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
        
        // Etusivu
        get("/", (req, res) -> {
            
            HashMap<String, Object> map = new HashMap<>();
            map.put("test1", "test2");
            
            ModelAndView mod = new ModelAndView(map, "templates/index.html");
            return mod;
        }, new VelocityTemplateEngine());
    }
}
