package tana.daithanh.mode;

/**
 * Created by Manh on 2/15/2018.
 */

public class UserAltp {


    String id="";
    String name="";
    Integer level;
    Integer time;
    Integer year;

    public UserAltp(String id,String name,Integer level,Integer time,Integer year)
    {
        this.id=id;
        this.name=name;
        this.level=level;
        this.time=time;
        this.year=year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
