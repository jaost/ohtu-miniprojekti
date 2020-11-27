package miniprojekti.domain;

public class BlogpostTip extends Tip{
    
    private int id;
    private String url;
    
    public BlogpostTip(int tipId, String title, String type, String note, int id, String url) {
        super(tipId, title, type, note);
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
    
}
