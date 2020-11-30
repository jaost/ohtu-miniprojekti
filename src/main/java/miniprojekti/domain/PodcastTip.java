package miniprojekti.domain;

import java.util.Map;

public class PodcastTip extends Tip{
    
    private int id;
    private String author;
    private String description;
    private String url;
    
    public PodcastTip(int tipId, String title, String type, String note, int id, String author, String description, String url) {
        super(tipId, title, type, note);
        this.id = id;
        this.author = author;
        this.description = description;
        this.url = url;
    }
    
    public static PodcastTip createPodcastTip(Map<String, String> attr) {
        return new PodcastTip(0, attr.get("title"), attr.get("type"), attr.get("note"), 0, attr.get("author"), attr.get("description"), attr.get("url"));
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
    
}
