package miniprojekti;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miniprojekti.data_access.ReadingTipDao;
import miniprojekti.database.Database;
import miniprojekti.domain.Logic;
import miniprojekti.domain.ReadingTip;
import spark.ModelAndView;
import spark.Redirect;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import spark.template.velocity.VelocityTemplateEngine;

// Main tulee toimimaan Controllerina
public class Main {

    private final static Logic appLogic = new Logic();
    private static final String LAYOUT = "templates/layout.html";

    public static void main(String[] args) {
        Spark.port(portSelection());

        getIndexPage();
        postReadingTip();
        getReadingTipsPage();
        addReadingTipPage();
        singleTipPage();
        deleteTip();
        // editTip();
    }

    private static void getIndexPage() {
        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            model.put("template", "templates/index.html");
            model.put("tips", appLogic.retrieveAllTips());

            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());
    }

    private static void deleteTip() {
        get("/tips/delete/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.html");
            String s = req.params(":id");
            int id = Integer.parseInt(s);
            appLogic.deleteTipByID(id);
            model.put("deleted", "Tip deleted!");
            res.redirect("/");
            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());
    }

    /*
     * private static void editTip() { put("/tips/:id", (req, res) -> {
     * HashMap<String, Object> model = new HashMap<>(); model.put("template",
     * "templates/index.html"); String type = req.queryParams("type"); String title
     * = req.queryParams("title"); String note = req.queryParams("note"); String url
     * = req.queryParams("url"); String author = ""; int id =
     * Integer.parseInt(req.queryParams("id"));
     * 
     * switch (type) { case "Book": author = req.queryParams("author"); String isbn
     * = req.queryParams("isbn"); model.put("editedTip", appLogic.updateTip(id,
     * type, title, note, url, author, isbn)); break;
     * 
     * case "Video": model.put("editedTip", appLogic.updateTip(id, type, title,
     * note, url)); break;
     * 
     * case "Podcast": author = req.queryParams("author"); model.put("editedTip",
     * appLogic.updateTip(id, type, title, note, url, author)); break; case
     * "Blogpost": model.put("editedTip", appLogic.updateTip(id, type, title, note,
     * url)); break; default: break; }
     * 
     * return new ModelAndView(model, LAYOUT); }, new VelocityTemplateEngine()); }
     */
    private static void postReadingTip() {
        post("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            // Ei toimi viel�
            // appLogic.saveNewTip(req.queryParams("author"), req.queryParams("title"),
            // req.queryParams("url"));

            model.put("template", "templates/index.html");
            model.put("tipAdded", "New tip added succesfully");
            model.put("tips", appLogic.retrieveAllTips());

            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());

    }

    private static void getReadingTipsPage() {
        get("/tips", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            model.put("tips", appLogic.retrieveAllTips());
            model.put("template", "templates/tips.html");

            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());
    }

    private static void singleTipPage() {
        get("/tips/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            String id = req.params("id");

            model.put("tips", appLogic.retrieveTip(id));
            model.put("template", "templates/tip.html");

            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());
    }

    private static void addReadingTipPage() {
        get("/add", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            String tipType = req.queryParams("tipTypes");

            model.put("type", tipType);
            model.put("template", "templates/add.html");

            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());
    }

    private static Integer portSelection() {
        ProcessBuilder process = new ProcessBuilder();
        if (process.environment().get("PORT") != null) {
            return Integer.parseInt(process.environment().get("PORT"));
        }
        return 4567;
    }
}