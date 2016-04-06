package main.com.database;

/**
 * Created by andreykazakov on 22.03.16.
 */
public class testQRwithDB {

    private int id;
    private String link;

    public testQRwithDB() {}

    public testQRwithDB(int id, String link) {
        this.id = id;
        this.link = link;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id: "+ id +
                ", link: " + link +"}";
    }
}
