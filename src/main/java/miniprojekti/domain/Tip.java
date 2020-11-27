package miniprojekti.domain;

public abstract class Tip {
    
    protected int tipId;
    protected String title;
    protected String type;
    protected String note;
    
    public Tip(int tipId, String title, String type, String note) {
        this.tipId = tipId;
        this.title = title;
        this.type = type;
        this.note = note;
    }

    public int getTipId() {
        return tipId;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getNote() {
        return note;
    }
}
