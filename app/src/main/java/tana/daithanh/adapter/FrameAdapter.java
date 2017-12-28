package tana.daithanh.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import  tana.daithanh.frame.MyFrame;

public class FrameAdapter extends FragmentPagerAdapter {

    int mCount;


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


        for (int i = 0; i < mCount; i++) {
            if(i==arg0)
            {
                return new MyFrame(""+i);
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
