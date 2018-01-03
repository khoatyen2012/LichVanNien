package tana.daithanh.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import tana.daithanh.database.DanhNgon;
import  tana.daithanh.frame.MyFrame;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class FrameAdapter extends FragmentPagerAdapter {

    int mCount;
    ArrayList<DanhNgon> lstVN;



  //  Date date = new Date();
    public int getmCount() {
        return mCount;
    }

    public void setmCount(int mCount) {
        this.mCount = mCount;
    }

    public FrameAdapter(FragmentManager fm,ArrayList<DanhNgon> lstVN) {
        super(fm);
        // TODO Auto-generated constructor stub
        this.lstVN=lstVN;

    }

    @Override
    public int getItemPosition(Object object) {

        Log.e("666","Vao Day Khong");
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub




            return new MyFrame(arg0,lstVN);



    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mCount;
    }
}
