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
    
    @Test
    public void tipsRetrievedByAuthor(){
        l.retrieveAllTipsByAuthor("author");
        verify(testDao).findAll();
    }
    
    @Test
    public void allTipsRetrieved(){
        l.retrieveAllTips();
        verify(testDao).findAll_();
    }
    
    @Test
    public void newPodcastTipIsSaved() {
        HashMap<String, String> tipMap = new HashMap<>();
        tipMap.put("id", "0");
        tipMap.put("title", "title");
        tipMap.put("note", "note");
        tipMap.put("author", "author");
        tipMap.put("type", "podcast");
        tipMap.put("description", "description");
        tipMap.put("url", "url");
        l.saveNewTip(tipMap);
        verify(testDao).save(any(PodcastTip.class));
    }

    @Test
    public void newVideoTipIsSaved() { 
        HashMap<String, String> tipMap = new HashMap<>();
        tipMap.put("id", "1");
        tipMap.put("title", "title");
        tipMap.put("note", "note");
        tipMap.put("type", "video");
        tipMap.put("url", "url");
        l.saveNewTip(tipMap);
        verify(testDao).save(any(VideoTip.class));
    }

    @Test
    public void newBlopostTipIsSaved() {
        HashMap<String, String> tipMap = new HashMap<>();
        tipMap.put("id", "1");
        tipMap.put("title", "title");
        tipMap.put("type", "blogpost");
        tipMap.put("note", "note");
        tipMap.put("url", "url");
        l.saveNewTip(tipMap);
        verify(testDao).save(any(BlogpostTip.class));
    }
   

    @Test
    public void tipIsDeletedByID() {
        l.deleteTipByID(0);
        verify(testDao).deleteByID(0);
    }

    @Test
    public void tipIsDeletedByTitle() {
        String title = "title";
        l.deleteTipByTitle(title);
        verify(testDao).deleteByTitle(title);
    }

    
}
