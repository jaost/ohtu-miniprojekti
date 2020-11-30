package miniprojekti.domain;

import java.util.Map;

public class VideoTip extends Tip{
    
    private int id;
    private String url;
    
    
    public VideoTip(int tipId, String title, String type, String note, int id, String url) {
        super(tipId, title, type, note);
        this.id = id;
        this.url = url;
    }
    
    public static VideoTip createVideoTip(Map<String, String> attr) {
        return new VideoTip(0, attr.get("title"), attr.get("type"), attr.get("note"), 0, attr.get("url"));
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
    
}
