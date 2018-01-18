package tana.daithanh.lichvannien;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Calendar;

import tana.daithanh.adapter.FrameAdapter;

import tana.daithanh.adapter.FrameAdapterMonth;
import tana.daithanh.database.DanhNgon;
import tana.daithanh.database.DataSourceDanhNgon;
import tana.daithanh.thaotac.AmDuong;
import tana.daithanh.thaotac.LunarYearTools;

public class MainActivity extends FragmentActivity {

   // private TextView mTextMessage;
    ViewPager viewpagerNgay;
    FrameAdapter adapterNgay;

    ViewPager viewpagerThang;
    FrameAdapterMonth adapterThang;

    ViewFlipper vfHome;
    private DataSourceDanhNgon datasource;
   //AmDuong amDuong;
   private ArrayList<DanhNgon> lstVN;

   DatePicker dpDuong;
   DatePicker dpAm;
   LunarYearTools amDuong=new LunarYearTools();

Boolean ok=true;
public  String ThongBaoSV="";

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


//    public  void  OnClick_DoiNgay(View view)
//    {
//        vfHome.setDisplayedChild(3);
//    }

    public void OnClick_HomNayThang(View view)
    {

        viewpagerThang.setCurrentItem(36);
    }

    public void OnClick_HomNay(View view)
    {

        viewpagerNgay.setCurrentItem(183);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewpagerThang.setCurrentItem(36);
        viewpagerNgay.setCurrentItem(183);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        datasource = new DataSourceDanhNgon(this);
        datasource.open();
        lstVN = new ArrayList<DanhNgon>();

        vfHome=(ViewFlipper) findViewById(R.id.viewFliper);

        viewpagerNgay = (ViewPager) findViewById(R.id.viewpagerNgay);
        adapterNgay = new FrameAdapter(getSupportFragmentManager(),lstVN);
        adapterNgay.setmCount(360);
        viewpagerNgay.setAdapter(adapterNgay);
        viewpagerNgay.setCurrentItem(183);

        viewpagerThang = (ViewPager) findViewById(R.id.viewpagerThang);
        adapterThang = new FrameAdapterMonth(getSupportFragmentManager());
        adapterThang.setmCount(72);
        viewpagerThang.setAdapter(adapterThang);
        viewpagerThang.setCurrentItem(36);

        dpDuong=(DatePicker)findViewById(R.id.dpDuong);
        dpAm=(DatePicker)findViewById(R.id.dpCDAm);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        int am[]= amDuong.convertSolar2Lunar(calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.YEAR),7);
        dpAm.init(
                am[2],
                am[1]-1,
               am[0],
                new DatePicker.OnDateChangedListener(){

                    @Override
                    public void onDateChanged(DatePicker view,
                                              int year, int monthOfYear,int dayOfMonth) {

if(ok) {
    ok=false;
    int tam[] = amDuong.convertLunar2Solar(dayOfMonth, monthOfYear + 1, year, 0, 7);
    dpDuong.updateDate(tam[2], tam[1] - 1, tam[0]);
    ok = true;
}


                    }});

        dpDuong.init(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener(){

                    @Override
                    public void onDateChanged(DatePicker view,
                                              int year, int monthOfYear,int dayOfMonth) {

                        if(ok) {
                            ok = false;
                          int tam[]=amDuong.convertSolar2Lunar(dayOfMonth,monthOfYear+1,year,7);
                        dpAm.updateDate(tam[2],tam[1]-1,tam[0]);
                            ok = true;
                        }

                    }});


        threadLoadData();

//        amDuong=new AmDuong();
//
//        int a[]= amDuong.convertSolar2Lunar(9,9,1989,7);
//        Log.e("xam",""+a[0]+"--"+a[1]+"--"+a[2]);

       // mTextMessage = (TextView) findViewById(R.id.message);

                if(getIntent().getExtras()!=null){
            //do your stuff

                    Intent intent = getIntent();
                    String msg = intent.getStringExtra("content");
                   if(msg!=null)
                   {
                       ThongBaoSV=""+msg;
                   }
        }






        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    /*
Lay du lieu do vao arraylist
*/
    private void threadLoadData() {


        new Thread(new Runnable() {
            public void run() {
                try {


                    getDefaultDataOffline();


                } catch (Exception e) {

                }

            }
        }).start();
    }

    /*
Load du lieu vao arraylist
*/
    public void getDefaultDataOffline() {
        try {
            ArrayList<Object> values = datasource.getAllNews();

            for (Object item : values) {
                DanhNgon n1 = new DanhNgon();


                n1.setId(((DanhNgon) item).getId().trim());
                n1.setContent(((DanhNgon) item).getContent().trim());
                n1.setAuthor(((DanhNgon) item).getAuthor().trim());


                lstVN.add(n1);

            }

            datasource.close();
            viewpagerNgay.getAdapter().notifyDataSetChanged();
        } catch (Exception ex) {

        }


    }

}
