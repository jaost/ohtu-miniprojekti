package miniprojekti.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import miniprojekti.data_access.Dao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class LogicTest {
    
    // Esimerkki stubi testausta varten
    Dao tipDao = new Dao() {
        
        public List<ReadingTip> testTips = new ArrayList<>();
        
        @Override
        public List<ReadingTip> findAll() {
            // Näitä pitää muokata / lisätä jos tai kun aletaan validoida käyttäjäsyötteitä
            testTips.add(new ReadingTip(1, "testAuthor", "testTitle", "https://www.google.com"));
            testTips.add(new ReadingTip(2, "testAuthor2", "testTitle2", "https://www.wikipedia.org"));
            testTips.add(new ReadingTip(3, "testAuthor3", "testTitle3", "https://www.ohjelmistotuotanto-hy.github.io"));
            
            return testTips;
        }

        @Override
        public void save(String author, String title, String url) {
            Random r = new Random();
            testTips.add(new ReadingTip(r.nextInt(1000000), author, title, url));
        }
        
    };
    
    public LogicTest() {
    }
    
    @Before
    public void setUp() {
    }

    // Testit -----------
    
}
