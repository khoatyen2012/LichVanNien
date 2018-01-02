package tana.daithanh.lichvannien;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ViewFlipper;

import tana.daithanh.adapter.FrameAdapter;
import tana.daithanh.thaotac.AmDuong;

public class MainActivity extends FragmentActivity {

   // private TextView mTextMessage;
    ViewPager viewpager;
    FrameAdapter adapter;

    ViewFlipper vfHome;

   //AmDuong amDuong;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    vfHome.setDisplayedChild(0);
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    vfHome.setDisplayedChild(1);
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    vfHome.setDisplayedChild(2);
                    return true;
            }
            return false;
        }
    };

    public void OnClick_HomNay(View view)
    {

        viewpager.setCurrentItem(183);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vfHome=(ViewFlipper) findViewById(R.id.viewFliper);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new FrameAdapter(getSupportFragmentManager());
        adapter.setmCount(360);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(183);



//        amDuong=new AmDuong();
//
//        int a[]= amDuong.convertSolar2Lunar(9,9,1989,7);
//        Log.e("xam",""+a[0]+"--"+a[1]+"--"+a[2]);

       // mTextMessage = (TextView) findViewById(R.id.message);



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
