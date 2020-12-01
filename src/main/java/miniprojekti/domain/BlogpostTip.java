package miniprojekti.domain;

import java.util.Map;

public class BlogpostTip extends Tip{
    
    private int id;
    private String url;
    
    public BlogpostTip(int tipId, String title, String note, int id, String url) {
        super(tipId, title, "Blogpost", note);
        this.id = id;
        this.url = url;
    }
    
    public static BlogpostTip createBlogpostTip(Map<String, String> attr) {
        return new BlogpostTip(0, attr.get("title"), attr.get("note"), 0, attr.get("url"));
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
    
}
