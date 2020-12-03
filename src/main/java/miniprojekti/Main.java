package miniprojekti;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import miniprojekti.data_access.ReadingTipDao;
import miniprojekti.database.Database;
import miniprojekti.domain.BlogpostTip;
import miniprojekti.domain.BookTip;
import miniprojekti.domain.Logic;
import miniprojekti.domain.PodcastTip;
import miniprojekti.domain.ReadingTip;
import miniprojekti.domain.Tip;
import miniprojekti.domain.TipFactory;
import miniprojekti.domain.VideoTip;
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
        editTip();
    }

    private static void getIndexPage() {
        get("/", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            String types = "All";
            try {
                String query = req.queryParams("typesToShow");
                if (!query.isEmpty()) {
                    types = query;
                }
            } catch (Exception e) {
            }

            model.put("template", "templates/index.html");
            model.put("tips", appLogic.retrieveAllTipsByType(types));
            
            return new ModelAndView(model, LAYOUT);
        },new VelocityTemplateEngine());

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

    private static void editTip() {
        post("/tips/:id", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            model.put("template", "templates/index.html");

            String type = req.queryParams("type");
            int id = Integer.parseInt(req.params(":id"));
            Tip tip;
            switch (type) {
                case "Book":
                    tip = new BookTip(id, req.queryParams("title"), req.queryParams("note"), 0,
                            req.queryParams("author"), req.queryParams("isbn"), req.queryParams("url"));
                    appLogic.updateTip(tip);
                    break;
                case "Video":
                    tip = new VideoTip(id, req.queryParams("title"), req.queryParams("note"), 0,
                            req.queryParams("url"));
                    appLogic.updateTip(tip);
                    break;
                case "Podcast":
                    tip = new PodcastTip(id, req.queryParams("title"), req.queryParams("note"), 0,
                            req.queryParams("author"), req.queryParams("description"), req.queryParams("url"));
                    appLogic.updateTip(tip);
                    break;
                case "Blogpost":
                    tip = new BlogpostTip(id, req.queryParams("title"), req.queryParams("note"), 0,
                            req.queryParams("url"));
                    appLogic.updateTip(tip);
                    break;
                default:
                    break;
            }

            model.put("editedTip", "Tip updated!");
            model.put("tips", appLogic.retrieveAllTips());
            return new ModelAndView(model, LAYOUT);
        }, new VelocityTemplateEngine());
    }

    private static void postReadingTip() {
        post("/add/:type", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();

            Map<String, String> paramMap = getQueryParams(req);
            paramMap.put("type", req.params("type"));

            appLogic.saveNewTip(paramMap);

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

            String id = req.params(":id");
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

    private static Map<String, String> getQueryParams(Request request) {
        final Map<String, String> paramMap = new HashMap<>();

        request.queryMap().toMap().forEach((key, value) -> {
            paramMap.put(key, value[0]);
        });

        return paramMap;
    }
}