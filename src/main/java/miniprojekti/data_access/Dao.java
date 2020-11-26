package miniprojekti.data_access;

import java.util.List;
import miniprojekti.domain.ReadingTip;

/**
 *
 * @author Tatu
 */
public interface Dao {

    List<ReadingTip> findAll();

    void save(String author, String title, String url);
    
    int deleteByID(int id);
    
    int deleteByTitle(String title);
    
    int updateTip(int id, String newAuthor, String newTitle, String newUrl);
    
    int updateTip(ReadingTip tip);
    
}
