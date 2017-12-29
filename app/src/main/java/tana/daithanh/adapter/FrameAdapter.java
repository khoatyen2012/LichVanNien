package tana.daithanh.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import  tana.daithanh.frame.MyFrame;
import java.util.Calendar;

public class FrameAdapter extends FragmentPagerAdapter {

    int mCount;
    Calendar c;

    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public FrameAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
        c = Calendar.getInstance();

    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub


        for (int i = 0; i < mCount; i++) {
            if(i==arg0)
            {
                if(i==30)
                {
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int month = c.get(Calendar.MONTH);
                    int year = c.get(Calendar.YEAR);
                    return new MyFrame(day,month,year);
                }else {
                    return new MyFrame(1,1,1);
                }
            }
        }
        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mCount;
    }
}
