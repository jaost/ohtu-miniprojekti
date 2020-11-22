package miniprojekti.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import miniprojekti.database.Database;
import miniprojekti.domain.ReadingTip;

public class ReadingTipDao {
    
    private Database database;
    
    public ReadingTipDao(Database database) {
        this.database = database;
    }
    
    public List<ReadingTip> findAll() {
        List<ReadingTip> readingTips = new ArrayList<>();
        
        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, author, title, url FROM readingtip").executeQuery()) {
            
            while (result.next()) {
                readingTips.add(new ReadingTip(
                        result.getInt("id"),
                        result.getString("author"),
                        result.getString("title"),
                        result.getString("url")
                    )
                );
            }
        } catch (Exception e) {
            
        }
        return readingTips;
    }
    
    public void save(String author, String title, String url) {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO readingtip (author, title, url) "
                    + "VALUES (?, ?, ?)");
            stmt.setString(1, author);
            stmt.setString(2, title);
            stmt.setString(3, url);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
