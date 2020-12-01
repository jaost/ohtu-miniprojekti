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
    Logic l;

    public LogicTest() {
    }

    @Before
    public void setUp() {
        testDao = mock(ReadingTipDao.class);
        l = new Logic(testDao);
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
    /*
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
        
    } */
    
}
