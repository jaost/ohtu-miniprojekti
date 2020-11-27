package miniprojekti.domain;

public class BookTip extends Tip{
    
    private int id;
    private String author;
    private String isbn;
    private String url;
    
    public BookTip(int tipId, String title, String type, String note, int id, String author, String isbn, String url) {
        super(tipId, title, type, note);
        this.id = id;
        this.author = author;
        this.isbn = isbn;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getUrl() {
        return url;
    }
    
}
