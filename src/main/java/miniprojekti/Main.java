package miniprojekti;

import java.sql.SQLException;
import java.util.List;
import miniprojekti.data_access.ReadingTipDao;
import miniprojekti.database.Database;
import miniprojekti.domain.ReadingTip;


// Main tulee toimimaan Controllerina
public class Main {
    public static void main(String[] args) {
        System.out.println("Test");
        Database db = new Database("jdbc:sqlite:readingtips.db");
        ReadingTipDao readingtipdao = new ReadingTipDao(db);
        
        ReadingTip tip = new ReadingTip(0, "Ensio Esimerkki", "Testi Titteli", "Kuvaava Kuvaus", "https://ohjelmistotuotanto-hy.github.io/speksi/", "Esimerkki", "Tämä on esimerkki");
        try {
            readingtipdao.save(tip);
            for (ReadingTip rt : readingtipdao.findAll()) {
                System.out.println(
                        "Author: \t" + rt.getAuthor() + "\n"
                        + "Title: \t" + rt.getTitle() + "\n"
                        + "Description: \t" + rt.getDescription() + "\n"
                        + "Url: \t" + rt.getUrl() + "\n"
                        + "Type: \t" + rt.getType() + "\n"
                        + "Note: \t" + rt.getNote() + "\n"
                );
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
