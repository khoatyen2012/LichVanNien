package tana.daithanh.lichvannien;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import tana.daithanh.adapter.FrameAdapter;

import tana.daithanh.adapter.FrameAdapterMonth;
import tana.daithanh.database.DanhNgon;
import tana.daithanh.database.DataSourceALTP;
import tana.daithanh.database.DataSourceDanhNgon;
import tana.daithanh.thaotac.LunarYearTools;

public class MainActivity extends FragmentActivity {

   // private TextView mTextMessage;
    ViewPager viewpagerNgay;
    FrameAdapter adapterNgay;

    ViewPager viewpagerThang;
    FrameAdapterMonth adapterThang;

    ViewFlipper vfHome;
    private DataSourceDanhNgon datasource;
    private DataSourceALTP dataaltp;
   //AmDuong amDuong;
   private ArrayList<DanhNgon> lstVN;

   DatePicker dpDuong;
   DatePicker dpAm;
   LunarYearTools amDuong=new LunarYearTools();

Boolean ok=true;
public  String ThongBaoSV="";
    Boolean doubleBackToExitPressedOnce = false;

    private DatabaseReference mDatabase;
    String linkad="";

    private AdView mAdView;
    private AdView mAdView1;
    private InterstitialAd mInterstitialAd;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    vfHome.setDisplayedChild(0);
                    viewpagerNgay.setCurrentItem(183);
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    vfHome.setDisplayedChild(1);
                    viewpagerThang.setCurrentItem(36);
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    vfHome.setDisplayedChild(2);
                    return true;
            }
            return false;
        }
    };

    public void onClickUngHoNgay(View view) {

        Random r = new Random();
        int i1 = r.nextInt(10);
        if(i1>0)
        {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }else {
            if (!linkad.equals("")) {
                doRate(linkad);
            }
        }
    }

    public void onClickViewAd(View view)
    {
        if(linkad.equals("")) {
            mDatabase.child("ads").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    linkad = dataSnapshot.getValue().toString().trim();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
        vfHome.setDisplayedChild(4);
    }

    void doRate(String pRate)
    {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
                .parse(""+pRate));
        startActivity(myIntent);
    }

    public void onClickRate(View view)
    {
        doRate("https://play.google.com/store/apps/details?id="+ getApplicationContext().getPackageName());
    }
    /**
     * Chia sẻ dữ liệu qua facebook
     * @param view
     */

    public void onClickShare(View view)
    {
        try {

                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                // Add data to the intent, the receiving app will decide
                // what to do with it.
                share.putExtra(Intent.EXTRA_SUBJECT, "Lịch Vạn Niên");
                share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());

                startActivity(Intent.createChooser(share, "Giới thiệu ứng dụng này cho bạn bè !"));

        } catch (Exception e) {
            // TODO: handle exception
        }

    }


    public  void  OnClick_About(View view)
    {
        vfHome.setDisplayedChild(3);
    }

    public void OnClick_HomNayThang(View view)
    {

        viewpagerThang.setCurrentItem(36);
    }

    public void OnClick_HomNay(View view)
    {

        viewpagerNgay.setCurrentItem(183);
    }

    public  void  onClickALTP(View view)
    {
        try
        {
            Intent myIten=new Intent(MainActivity.this,AiLaTrieuPhu.class);
            startActivity(myIten);
           // finish();
        }catch (Exception ex)
        {

        }



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



      try {
          MobileAds.initialize(this, ""+getResources().getString(R.string.app_id));
          mAdView = findViewById(R.id.adView);
          mAdView1 = findViewById(R.id.adView1);
          AdRequest adRequest = new AdRequest.Builder().build();
          mAdView.loadAd(adRequest);
          mAdView1.loadAd(adRequest);

          mInterstitialAd = new InterstitialAd(this);
          mInterstitialAd.setAdUnitId(""+getString(R.string.inapp_id));
          mInterstitialAd.loadAd(new AdRequest.Builder().build());

          datasource = new DataSourceDanhNgon(this);
          datasource.open();
          dataaltp=new DataSourceALTP(this);


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

          mDatabase = FirebaseDatabase.getInstance().getReference();

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



      }catch (Exception ex)
      {

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

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.doubleclick),
                Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
