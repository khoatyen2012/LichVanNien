package tana.daithanh.mode;

/**
 * Created by Manh on 1/6/2018.
 */

public class MonthCalender {
    String monthDuong="";
    String monthAm="";

    public MonthCalender(String monthDuong,String monthAm)
    {
        this.monthDuong=monthDuong;
        this.monthAm=monthAm;
    }

    public String get_MonthDuong() {
        return monthDuong;
    }

    public void set_monthDuong(String _link) {
        this.monthDuong = _link;
    }

    public String get_monthAm() {
        return monthAm;
    }

    public void set_monthAm(String _link) {
        this.monthAm = _link;
    }

}
