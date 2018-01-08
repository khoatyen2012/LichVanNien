package tana.daithanh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;

import tana.daithanh.database.DanhNgon;
import tana.daithanh.frame.MyFrame;
import tana.daithanh.frame.MyFrameMonth;

/**
 * Created by Manh on 1/6/2018.
 */

public class FrameAdapterMonth extends FragmentPagerAdapter {

    int mCount;




    //  Date date = new Date();
    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public FrameAdapterMonth(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub

    }

    @Override
    public int getItemPosition(Object object) {


        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub




        return new MyFrameMonth(arg0);



    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mCount;
    }
}
