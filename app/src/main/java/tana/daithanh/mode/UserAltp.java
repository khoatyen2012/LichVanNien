package tana.daithanh.mode;

/**
 * Created by Manh on 2/15/2018.
 */

public class UserAltp {




    String mac="";
    String name="";
    Integer level=0;
    Integer time=81;
    Integer year=2018;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public UserAltp(String mac,String name,Integer level,Integer time,Integer year)
    {
        this.mac=mac;
        this.name=name;
        this.level=level;
        this.time=time;
        this.year=year;
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
