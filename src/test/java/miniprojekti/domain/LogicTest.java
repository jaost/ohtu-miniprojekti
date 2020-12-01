package miniprojekti.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import miniprojekti.data_access.*;
import miniprojekti.domain.TipFactory;
import org.junit.*;
import org.mockito.Matchers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import org.mockito.stubbing.OngoingStubbing;

public class LogicTest {
    ReadingTipDao testDao;
    TipFactory tipFactory;

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
            testTips.add(new ReadingTip(0, author, title, url)); // poistettu random-viite, tähän voi keksiä
                                                                 // testiviitteen
        }

        @Override
        public int deleteByID(int id) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int deleteByTitle(String title) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int updateTip(Tip tip) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int deleteTip(Tip tip) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        

    };

    public LogicTest() {
    }

    @Before
    public void setUp() {
        testDao = mock(ReadingTipDao.class);
        tipFactory = mock(TipFactory.class);
    }
    
    @After
    public void validate() {
        validateMockitoUsage();
    }

    @Test
    public void newBookTipIsSaved() {
        Logic l = new Logic(testDao);
        HashMap<String, String> tipMap = new HashMap<>();
        tipMap.put("id", "0");
        tipMap.put("title", "title");
        tipMap.put("type", "book");
        tipMap.put("note", "note");
        tipMap.put("author", "author");
        tipMap.put("isbn", "isbn");
        tipMap.put("url", "url");
        l.saveNewTip(tipMap);
        verify(testDao).save(any(BookTip.class));
    }

    
   /* @Test
    public void tipsAreListedDao() {
        List list = tipDao.findAll();
        assertEquals("testTitle by testAuthor: https://www.google.com", list.get(0).toString());
    }
    
    @Test
    public void tipIsDeletedByID() {
        assertEquals(1, tipDao.deleteByID(0));
    }
    
    @Test
    public void tipIsDeletedByTitle() {
        assertEquals(1, tipDao.deleteByTitle("testAuthor"));
    }
    
    @Test
    public void tipIsUpdated() {
        assertEquals(1, tipDao.updateTip(1, "testAuthor", "testTitle", "https://www.google.com"));
    }*/
    
}
