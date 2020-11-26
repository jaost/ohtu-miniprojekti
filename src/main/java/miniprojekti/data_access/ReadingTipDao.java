package miniprojekti.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import miniprojekti.database.Database;
import miniprojekti.domain.ReadingTip;

public class ReadingTipDao implements Dao {
    
    private Database database;
    
    public ReadingTipDao(Database database) {
        this.database = database;
    }
    
    @Override
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
    
    @Override
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
    
    @Override
    public int deleteByID(int id) {
        int rowsDeleted = 0;
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM readingtip WHERE id=?");
            stmt.setInt(1, id);
            rowsDeleted = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
        return rowsDeleted;
    }
    
    @Override
    public int deleteByTitle(String title) {
        int rowsDeleted = 0;
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM readingtip WHERE title=?");
            stmt.setString(1, title);
            rowsDeleted = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
        return rowsDeleted;
    }
    
    @Override
    public int updateTip(int id, String newAuthor, String newTitle, String newUrl) {
        int rowsUpdated = 0;
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE readingtip SET author=?, title=?, url=? WHERE id=?");
            stmt.setString(1, newAuthor);
            stmt.setString(2, newTitle);
            stmt.setString(3, newUrl);
            stmt.setInt(4, id);
            rowsUpdated = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
        return rowsUpdated;
    }
    
    @Override
    public int updateTip(ReadingTip tip) {
        int rowsUpdated = 0;
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE readingtip SET author=?, title=?, url=? WHERE id=?");
            stmt.setString(1, tip.getAuthor());
            stmt.setString(2, tip.getTitle());
            stmt.setString(3, tip.getUrl());
            stmt.setInt(4, tip.getId());
            rowsUpdated = stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
        return rowsUpdated;
    }
    
    
}
