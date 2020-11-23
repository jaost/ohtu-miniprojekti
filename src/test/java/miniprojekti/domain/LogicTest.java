package miniprojekti.domain;

import java.util.ArrayList;
import java.util.List;
import miniprojekti.data_access.Dao;
import miniprojekti.data_access.ReadingTipDao;
import miniprojekti.domain.Logic;
import org.junit.*;
import static org.mockito.Mockito.*;

public class LogicTest {
    ReadingTipDao testDao;
    
    
    // Esimerkki stubi testausta varten -> Minna ei käytä tätä, TatuC käytä halutessasi tai poista
    Dao tipDao = new Dao() {
        
        public List<ReadingTip> testTips = new ArrayList<>();
        
        @Override
        public List<ReadingTip> findAll() {

            testTips.add(new ReadingTip(1, "testAuthor", "testTitle", "https://www.google.com"));
            testTips.add(new ReadingTip(2, "testAuthor2", "testTitle2", "https://www.wikipedia.org"));
            testTips.add(new ReadingTip(3, "testAuthor3", "testTitle3", "https://www.ohjelmistotuotanto-hy.github.io"));
            
            return testTips;
        }

        @Override
        public void save(String author, String title, String url) {
            testTips.add(new ReadingTip(0, author, title, url)); // poistettu random-viite, tähän voi keksiä testiviitteen
        }
        
    };
    
    public LogicTest() {
    }
    
    @Before
    public void setUp() {
        testDao = mock(ReadingTipDao.class);
    }

    // Testit -----------
    
    @Test
    public void newTipIsSaved() {
        Logic l = new Logic(testDao);
        l.saveNewTip("testAuthor", "testTitle", "testUrl");
        
        verify(testDao).save(eq("testAuthor"), eq("testTitle"), eq("testUrl"));
    }
}
