package tana.daithanh.lichvannien;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
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

    private InterstitialAd mInterstitialAd;

    MediaPlayer player;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                   // mTextMessage.setText(R.string.title_home);
                    try {
                        vfHome.setDisplayedChild(0);
                        viewpagerNgay.setCurrentItem(183);
                        doCallSound("chamnuoc.ogg");
                    }catch (Exception ex)
                    {

                    }
                    return true;
                case R.id.navigation_dashboard:
                   // mTextMessage.setText(R.string.title_dashboard);
                    try
                    {
                    vfHome.setDisplayedChild(1);
                    viewpagerThang.setCurrentItem(36);
                    doCallSound("chamnuoc.ogg");
                    }catch (Exception ex)
                    {

                    }
                    return true;
                case R.id.navigation_notifications:
                   // mTextMessage.setText(R.string.title_notifications);
                    try
                    {
                    vfHome.setDisplayedChild(2);
                    doCallSound("chamnuoc.ogg");
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    }catch (Exception ex)
                    {

                    }
                    return true;
            }
            return false;
        }
    };

    public void onClickUngHoNgay(View view) {

try
{
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
}catch (Exception ex) {

}
    }

    public void onClickViewAd(View view)
    {
        try
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
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        }catch (Exception ex)
        {

        }
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {

        player.stop();
        player.reset();

        super.onPause();
    }

    /*
Phat am thanh
*/
    void doCallSound(String mp3) {


            AssetFileDescriptor afd;

            try {
                afd = getAssets().openFd("" + mp3);
                player.stop();
                player.reset();
                player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                afd.close();
                player.prepare();


            } catch (Exception ex) {

            }

            player.start();


    }


    void doRate(String pRate)
    {
        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri
                .parse(""+pRate));
        startActivity(myIntent);
    }



    public void onClickRate(View view)
    {
        try
        {
        doRate("https://play.google.com/store/apps/details?id="+ getApplicationContext().getPackageName());
        }catch (Exception ex)
        {

        }
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
        try
        {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }
        vfHome.setDisplayedChild(3);
        }catch (Exception ex)
        {

        }

    }

    public void OnClick_HomNayThang(View view)
    {

        try
        {
        viewpagerThang.setCurrentItem(36);
        }catch (Exception ex)
        {

        }
    }

    public void OnClick_HomNay(View view)
    {
try
{
        viewpagerNgay.setCurrentItem(183);
}catch (Exception ex)
{

}
    }

    public  void  onClickALTP(View view)
    {
        try
        {
            //Intent myIten=new Intent(MainActivity.this,AiLaTrieuPhu.class);
            //startActivity(myIten);
            doRate("https://play.google.com/store/apps/details?id=ai.la.trieu.phu.altp.tieng");

        }catch (Exception ex)
        {

        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        try
        {
        viewpagerThang.setCurrentItem(36);
        viewpagerNgay.setCurrentItem(183);
        }catch (Exception ex)
        {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



      try {
          MobileAds.initialize(this, ""+getResources().getString(R.string.app_id));
          mAdView = findViewById(R.id.adView);

          AdRequest adRequest = new AdRequest.Builder().build();
          mAdView.loadAd(adRequest);


          mInterstitialAd = new InterstitialAd(this);
          mInterstitialAd.setAdUnitId(""+getString(R.string.inapp_id));
          mInterstitialAd.loadAd(new AdRequest.Builder().build());

          datasource = new DataSourceDanhNgon(this);
          datasource.open();



          lstVN = new ArrayList<DanhNgon>();

          vfHome=(ViewFlipper) findViewById(R.id.viewFliper);
          player = new MediaPlayer();

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
        try
        {

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, getString(R.string.doubleclick),
                Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
        }catch (Exception ex)
        {

        }
    }

}
