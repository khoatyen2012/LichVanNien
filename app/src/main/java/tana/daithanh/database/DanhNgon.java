package tana.daithanh.database;

import java.io.Serializable;

/**
 * Created by Administrator on 03/01/2018.
 */

public class DanhNgon implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    String author;

}
