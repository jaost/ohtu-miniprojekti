package miniprojekti.domain;

import java.util.Map;

public class TipFactory {
    
    public static Tip createTip(Map<String, String> attr) {
        switch (attr.get("type")) {
            case "book":
                return BookTip.createBook(attr);
            case "blogpost":
                return BlogpostTip.createBlogpostTip(attr);
            case "video":
                return VideoTip.createVideoTip(attr);
            case "podcast":
                return PodcastTip.createPodcastTip(attr);
            default:
                return null;
        }
    }
    
}
