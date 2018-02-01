package tana.daithanh.database;

/**
 * Created by Administrator on 01/02/2018.
 */

import java.io.Serializable;

/**
 * Created by trangtay on 3/3/2015.
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;


    String question;
    String _id;
    String level;
    String casea;
    String caseb;
    String casec;
    String cased;
    String truecase;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCasea() {
        return casea;
    }

    public void setCasea(String casea) {
        this.casea = casea;
    }

    public String getCaseb() {
        return caseb;
    }

    public void setCaseb(String caseb) {
        this.caseb = caseb;
    }

    public String getCasec() {
        return casec;
    }

    public void setCasec(String casec) {
        this.casec = casec;
    }

    public String getCased() {
        return cased;
    }

    public void setCased(String cased) {
        this.cased = cased;
    }

    public String getTruecase() {
        return truecase;
    }

    public void setTruecase(String truecase) {
        this.truecase = truecase;
    }
}
