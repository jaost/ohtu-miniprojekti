package miniprojekti.domain;

// Voitaisiin sijoittaa Mainin ja Dao:n v�linen sovelluslogiikka ainakin aluksi t�nne

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        List<Tip> tips = readingTipDao.findAll_();
        ArrayList<HashMap<String, String>> modelTips = new ArrayList<>();

        for (Tip tip : tips) {
            HashMap<String, String> tipMap = new HashMap<>();
            tipMap.put("id", String.valueOf(tip.getTipId()));
            tipMap.put("title", tip.getTitle());
            tipMap.put("type", tip.getType());
            tipMap.put("note", tip.getNote());

            modelTips.add(tipMap);
        }

        return modelTips;
    }

    public List<HashMap<String, String>> retrieveTip(String s) {
        int id = Integer.parseInt(s) - 1;
        Tip tip = readingTipDao.findAll_().get(id);
        ArrayList<HashMap<String, String>> modelTips = new ArrayList<>();
        HashMap<String, String> tipMap = new HashMap<>();

        switch (tip.getType()) {
            case "Book":
                BookTip booktip = (BookTip) tip;
                tipMap.put("id", String.valueOf(booktip.getTipId()));
                tipMap.put("title", booktip.getTitle());
                tipMap.put("type", booktip.getType());
                tipMap.put("note", booktip.getNote());
                tipMap.put("author", booktip.getAuthor());
                tipMap.put("isbn", booktip.getIsbn());
                tipMap.put("url", booktip.getUrl());
                break;
                case "Video":
                VideoTip videotip = (VideoTip) tip;
                tipMap.put("id", String.valueOf(videotip.getTipId()));
                tipMap.put("title", videotip.getTitle());
                tipMap.put("type", videotip.getType());
                tipMap.put("note", videotip.getNote());
                tipMap.put("url", videotip.getUrl());
                break;
                case "Blogpost":
                BlogpostTip blogposttip = (BlogpostTip) tip;
                tipMap.put("id", String.valueOf(blogposttip.getTipId()));
                tipMap.put("title", blogposttip.getTitle());
                tipMap.put("type", blogposttip.getType());
                tipMap.put("note", blogposttip.getNote());
                tipMap.put("url", blogposttip.getUrl());
                break;
                case "Podcast":
                PodcastTip podcasttip = (PodcastTip) tip;
                tipMap.put("id", String.valueOf(podcasttip.getTipId()));
                tipMap.put("title", podcasttip.getTitle());
                tipMap.put("type", podcasttip.getType());
                tipMap.put("note", podcasttip.getNote());
                tipMap.put("author", podcasttip.getAuthor());
                tipMap.put("description", podcasttip.getDescription());
                tipMap.put("url", podcasttip.getUrl());
                break;
            default:
                break;
        }

        modelTips.add(tipMap);

        return modelTips;
    }

    public List<HashMap<String, String>> retrieveAllTipsByAuthor(String author) {

        List<ReadingTip> tips = readingTipDao.findAll();
        ArrayList<HashMap<String, String>> modelTips = new ArrayList<>();

        for (ReadingTip tip : tips) {

            if (tip.getAuthor().equals(author)) {
                HashMap<String, String> tipMap = new HashMap<>();

                tipMap.put("id", String.valueOf(tip.getId()));
                tipMap.put("author", tip.getAuthor());
                tipMap.put("title", tip.getTitle());
                tipMap.put("url", tip.getUrl());

                modelTips.add(tipMap);
            }
        }

        return modelTips;
    }

    /*
     * public void saveNewTip(String author, String title, String url) {
     * readingTipDao.save(author, title, url); }
     */

    public void saveNewTip(Map<String, String> tipAttributes) {
        // T�m� talletetaan daossa
        Tip toSave = TipFactory.createTip(tipAttributes);

        readingTipDao.save(toSave);
    }

    public void deleteTipByID(int id) {
        int rowsDeleted = readingTipDao.deleteByID(id);
        System.out.println("Deleted " + rowsDeleted + " rows.");
    }

    public void deleteTipByTitle(String title) {
        int rowsDeleted = readingTipDao.deleteByTitle(title);
        System.out.println("Deleted " + rowsDeleted + " rows.");
    }

    public void updateTip(Tip tip) {
        int rowsDeleted = readingTipDao.updateTip(tip);
        System.out.println("Updated " + rowsDeleted + " rows.");
    }

}
