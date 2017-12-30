package tana.daithanh.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import  tana.daithanh.frame.MyFrame;
import java.util.Calendar;
import java.util.Date;

public class FrameAdapter extends FragmentPagerAdapter {

    int mCount;
    Calendar c;
  //  Date date = new Date();
    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public FrameAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub


    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub





            return new MyFrame(arg0);



    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mCount;
    }
}
