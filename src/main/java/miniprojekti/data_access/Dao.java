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
    
}
