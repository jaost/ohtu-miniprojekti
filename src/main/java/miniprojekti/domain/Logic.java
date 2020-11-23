package miniprojekti.domain;

// Voitaisiin sijoittaa Mainin ja Dao:n v�linen sovelluslogiikka ainakin aluksi t�nne

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miniprojekti.data_access.ReadingTipDao;
import miniprojekti.database.Database;

public class Logic {
    
    private static ReadingTipDao readingTipDao = new ReadingTipDao(new Database("jdbc:sqlite:readingtips.db"));
    
    public Logic() {
    }
    
    public Logic(ReadingTipDao testiTipDao) {
        this.readingTipDao = testiTipDao;
    }
    
    public List<HashMap<String, String>> retrieveAllTips() {
        List<ReadingTip> tips = readingTipDao.findAll();
        ArrayList<HashMap<String, String>> modelTips = new ArrayList<>();
        
        for (ReadingTip tip : tips) {
            HashMap<String, String> tipMap = new HashMap<>();

            tipMap.put("author", tip.getAuthor());
            tipMap.put("title", tip.getTitle());
            tipMap.put("url", tip.getUrl());

            modelTips.add(tipMap);
        }
        
        return modelTips;
    }
    
    public void saveNewTip(String author, String title, String url) {
        readingTipDao.save(author, title, url);
    }
}
