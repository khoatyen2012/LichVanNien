package tana.daithanh.lichvannien;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import tana.daithanh.adapter.RankAdapter;
import tana.daithanh.database.DataSourceALTP;
import tana.daithanh.database.Question;
import tana.daithanh.mode.UserAltp;


public class AiLaTrieuPhu extends Activity {

    ViewFlipper viewFlipper;

    private DataSourceALTP datasource;
    private ArrayList<Question> lstVN;
    MediaPlayer player;
    boolean alowMP3 = false;
    Handler handler = new Handler();
    private int level = 1;
    private int nextlevel = 1;
    TextView tvQuestion;
    TextView btDaa;
    TextView btDab;
    TextView btDac;
    TextView btDad;
    private int mCaseTrue = 0;
    private int mSelect = 0;
    ImageView ivLaiVanSam;
    final Animation animation = new AlphaAnimation(1, 0);

    private int mNamMuoi = 0;
    ImageView ivNamMuoi;
    ImageView ivKhanGia;

    TextView tvPA;
    TextView tvPB;
    TextView tvPC;
    TextView tvPD;

    Button btPA;
    Button btPB;
    Button btPC;
    Button btPD;

    private ImageView ivNguoiThan;
    TextView tvDetailCall;
    ImageView ivDoiCauHoi;
    TextView tvTime;

    private DemNguocRunnable mDemnguocRun;
    private Handler mDemnguocHandler;
    private int mTime = 81;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    int mMax = 0;
    int mMaxTime = 0;
    String mName="";
    String mac;

    TextView tvan1;
    TextView tvan2;
    TextView tvan3;
    TextView tvan4;
    TextView tvan5;
    TextView tvan6;
    TextView tvan7;
    TextView tvan8;
    TextView tvan9;
    TextView tvan10;
    TextView tvan11;
    TextView tvan12;
    TextView tvan13;
    TextView tvan14;
    TextView tvan15;
    EditText etMyName;
    TextView tvMyName;
    TextView tvMyLevel;
    TextView tvMyTop;
    ProgressBar progressBar;
    Integer year=2018;




    ListView lvRank;
    RankAdapter adapter;
    private ArrayList<UserAltp> lstClickUser;


    private DatabaseReference mDatabase;


    private AdView mAdView;
    private AdView mAdView1;
    private InterstitialAd mInterstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_la_trieu_phu);

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

            viewFlipper =(ViewFlipper) findViewById(R.id.vfALTP);
            tvQuestion=(TextView) findViewById(R.id.tvQuestion);
            btDaa=(TextView)findViewById(R.id.btA);
            btDab=(TextView)findViewById(R.id.btB);
            btDac=(TextView)findViewById(R.id.btC);
            btDad=(TextView)findViewById(R.id.btD);
            ivLaiVanSam=(ImageView)findViewById(R.id.ivLaiVanSam);
            ivNamMuoi=(ImageView) findViewById(R.id.ivNamMuoi);
            ivKhanGia=(ImageView)findViewById(R.id.ivKhanGia);
            ivNguoiThan = (ImageView) findViewById(R.id.ivNguoiThan);

            tvPA = (TextView) findViewById(R.id.tvPA);
            tvPB = (TextView) findViewById(R.id.tvPB);
            tvPC = (TextView) findViewById(R.id.tvPC);
            tvPD = (TextView) findViewById(R.id.tvPD);

            btPA = (Button) findViewById(R.id.btPA);
            btPB = (Button) findViewById(R.id.btPB);
            btPC = (Button) findViewById(R.id.btPC);
            btPD = (Button) findViewById(R.id.btPD);

            tvDetailCall = (TextView) findViewById(R.id.tvDetailCall);
            ivDoiCauHoi = (ImageView) findViewById(R.id.ivDoiCauHoi);
            tvTime=(TextView) findViewById(R.id.tvTimes);

            tvan1 = (TextView) findViewById(R.id.tvan1);
            tvan2 = (TextView) findViewById(R.id.tvan2);
            tvan3 = (TextView) findViewById(R.id.tvan3);
            tvan4 = (TextView) findViewById(R.id.tvan4);
            tvan5 = (TextView) findViewById(R.id.tvan5);
            tvan6 = (TextView) findViewById(R.id.tvan6);
            tvan7 = (TextView) findViewById(R.id.tvan7);
            tvan8 = (TextView) findViewById(R.id.tvan8);
            tvan9 = (TextView) findViewById(R.id.tvan9);
            tvan10 = (TextView) findViewById(R.id.tvan10);
            tvan11 = (TextView) findViewById(R.id.tvan11);
            tvan12 = (TextView) findViewById(R.id.tvan12);
            tvan13 = (TextView) findViewById(R.id.tvan13);
            tvan14 = (TextView) findViewById(R.id.tvan14);
            tvan15 = (TextView) findViewById(R.id.tvan15);

            etMyName=(EditText)findViewById(R.id.etName);
            tvMyName=(TextView)findViewById(R.id.tvMyName);
            tvMyLevel=(TextView)findViewById(R.id.tvLevel);
            tvMyTop=(TextView)findViewById(R.id.tvTop);
            progressBar=(ProgressBar) findViewById(R.id.progressBar);

            lvRank=(ListView)findViewById(R.id.lvRank);
            lstClickUser=new ArrayList<UserAltp>();

            adapter=new RankAdapter(this,R.id.list_item,lstClickUser);
            lvRank.setAdapter(adapter);



            pref = getApplicationContext().getSharedPreferences("trieuphumobile", 0);// 0 - là chế độ private
            editor = pref.edit();
            mac=pref.getString("macclick","");
            if(mac.equals(""))
            {
                String android_id = Settings.Secure.getString(this.getContentResolver(),
                        Settings.Secure.ANDROID_ID);
                editor.putString("macclick", android_id);
                editor.commit();
            }

            Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);


            mDemnguocHandler = new Handler();
            mDemnguocRun = new DemNguocRunnable();



            datasource = new DataSourceALTP(this);
            datasource.open();
            lstVN = new ArrayList<Question>();
            player = new MediaPlayer();

            animation.setDuration(500); // duration - half a second
            animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
            animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
            animation.setRepeatMode(Animation.REVERSE);





            mDatabase = FirebaseDatabase.getInstance().getReference();





            threadLoadData();

        }catch (Exception ex)
        {

        }



    }

    public void onClickMyName(View view)
    {
        onClickShowBackNull(7);
        mName=pref.getString("myname","");
        if(!mName.equals(""))
        {
            etMyName.setText(""+mName);
        }

    }
    public void onClickCancel(View view)
    {
        onClickShowBackNull(0);
    }

    public void onClickOk(View view)
    {

        mName=""+etMyName.getText().toString().trim();
        if(!mName.equals("")) {



            mMax = pref.getInt("maxlevel", 0);
            mMaxTime=pref.getInt("maxtime",81);



            editor.putString("myname", mName);
            editor.commit();
            tvMyName.setText(""+mName);
            tvMyLevel.setText("Câu : "+mMax);
            writeNewUser();

            onClickShowBackNull(8);

            if(lstClickUser.size()<=0) {

                mDatabase.child("users").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        lstClickUser.clear();
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {


                            int myYear = Integer.parseInt(postSnapshot.child("year").getValue().toString());
                            if (myYear == year) {
                                UserAltp userAltp = new UserAltp("" + postSnapshot.getKey(), "" + postSnapshot.child("name").getValue(), Integer.parseInt(postSnapshot.child("level").getValue().toString()), Integer.parseInt(postSnapshot.child("time").getValue().toString()), year);

                                lstClickUser.add(userAltp);
                                if (lstClickUser.size() > 1) {
                                    Collections.sort(lstClickUser, new Comparator<UserAltp>() {
                                        public int compare(UserAltp s1, UserAltp s2) {
                                            if (s2.getLevel() == s1.getLevel()) {
                                                return s1.getTime() - s2.getTime();
                                            } else {
                                                return s2.getLevel() - s1.getLevel();
                                            }
                                        }
                                    });


                                }
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                        if (lstClickUser.size() > 0) {
                            for (int i = 0; i < lstClickUser.size(); i++) {
                                if (lstClickUser.get(i).getMac().equals("" + mac)) {
                                    tvMyTop.setText("Top " + (i + 1));
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }


    private void writeNewUser() {
        UserAltp user=new UserAltp(mac,mName,mMax,mMaxTime,year);

        mDatabase.child("users").child(mac).setValue(user);
    }

    /*
    Show dem nguoc
     */
    private void showDemNguoc() {
        try {
            mTime = 81;
            mDemnguocHandler.removeCallbacks(mDemnguocRun);
            mDemnguocHandler.postDelayed(mDemnguocRun, 1000);
        } catch (Exception ex) {

        }
    }

    /*
    Tao class dem nguoc
     */
    private class DemNguocRunnable implements Runnable {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            handleDemnguoc();
        }
    }

    /*
    Tao hander dem nguoc
     */
    private void handleDemnguoc() {

        try {
            if(mSelect==0||mSelect == 5) {
                mTime--;
            }else
            {
                mDemnguocHandler.removeCallbacks(mDemnguocRun);
            }

            if (mTime < 0) {


                mDemnguocHandler.removeCallbacks(mDemnguocRun);
                tvTime.setTextColor(Color.parseColor("#FFFFFF"));
                doCallSound("hetgio.mp3");
level=1;
                doRestartGame();


            } else {
                if (mTime <= 10) {
                    tvTime.setTextColor(Color.RED);
                } else {
                    tvTime.setTextColor(Color.parseColor("#FFFFFF"));
                }
                tvTime.setText(mTime + "'");

                mDemnguocHandler.postDelayed(mDemnguocRun, 1000);
            }
        } catch (Exception ex) {

        }
    }

    public void onClickDoiCauHoi(View view) {
        try {
            if (mSelect == 0) {
                ivDoiCauHoi.setImageResource(R.drawable.doicauhoi2);
                ivDoiCauHoi.setEnabled(false);
                subget();
            }
        } catch (Exception ex) {

        }
    }

    /*
  show hoi nguoi than
   */
    public void onClickHoiNguoiThan(View view) {
        try {
            if (mSelect == 0) {
                mSelect = 5;
                doCallSound("goinguoithan.mp3");
                ivNguoiThan.setImageResource(R.drawable.nguoithan2);
                ivNguoiThan.setEnabled(false);
                handler.postDelayed(myNguoiThan, 3000);
            }
        } catch (Exception ex) {

        }
    }

    /*
    run nguoi than
     */
    Runnable myNguoiThan = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(myNguoiThan);
            onClickShowBackNull(4);
        }
    };


    public void onClickCall1(View view) {
        try {
            doNguoiThan("" + view.getContentDescription());
        } catch (Exception ex) {

        }

    }

    /*
Nguoi than phan hoi
*/
    void doNguoiThan(String name) {
        String dnt = "";
        Random rd = new Random();
        int chon;
        if (level <= 5) {
            chon = rd.nextInt(10);
        } else if (level <= 10) {
            chon = rd.nextInt(6);
        } else {
            chon = rd.nextInt(4);
        }
        if (chon != 0) {
            if (mCaseTrue == 1) {
                dnt = "A";
            } else if (mCaseTrue == 2) {
                dnt = "B";
            } else if (mCaseTrue == 3) {
                dnt = "C";
            } else {
                dnt = "D";
            }
        } else {

            if (mNamMuoi == 0) {
                int fRd = rd.nextInt(4);
                if (fRd == 0) {
                    dnt = "A";
                } else if (fRd == 1) {
                    dnt = "B";
                } else if (fRd == 2) {
                    dnt = "C";
                } else {
                    dnt = "D";
                }
            } else {
                if (mNamMuoi == 1) {
                    dnt = "A";
                } else if (mNamMuoi == 2) {
                    dnt = "B";
                } else if (mNamMuoi == 3) {
                    dnt = "C";
                } else {
                    dnt = "D";
                }
            }
        }
        String mCall = "";
        int callType = rd.nextInt(4);
        if (callType == 0) {
            mCall = "" + getString(R.string.call1);
        } else if (callType == 1) {
            mCall = "" + getString(R.string.call2);
        } else if (callType == 2) {
            mCall = "" + getString(R.string.call3);
        } else {
            mCall = "" + getString(R.string.call4);
        }
        tvDetailCall.setText(name + ":" + mCall + " " + dnt);
        onClickShowBackNull(5);
    }

    /*
  show play game
   */
    public void onClickShowPlayGame(View view) {
        try {
            onClickShowBackNull(1);
            mSelect = 0;
        } catch (Exception ex) {

        }
    }


    /*
View hoi y kien khan gia
 */
    public void onClickHoiYKien(View view) {
        try {
            if (mSelect == 0) {
                doCallSound("khangia.mp3");
                mSelect = 5;
                ivKhanGia.setImageResource(R.drawable.hoikhangia1);
                ivKhanGia.setEnabled(false);
                handler.postDelayed(myKhanGia, 6100);
            }
        } catch (Exception ex) {

        }
    }

    /*
    Run khan gia
     */
    Runnable myKhanGia = new Runnable() {
        @Override
        public void run() {

            doKhanGia();
        }
    };

    /*
Khan gia
 */
    void doKhanGia() {
        handler.removeCallbacks(myKhanGia);
        onClickShowBackNull(3);
        int a = 0, b = 0, c = 0, d = 0;
        Random rd = new Random();
        int chon;
        if (level <= 5) {
            chon = rd.nextInt(10);
        } else if (level <= 10) {
            chon = rd.nextInt(7);
        } else {
            chon = rd.nextInt(5);
        }

        if (mNamMuoi == 0) {
            if (mCaseTrue == 1) {
                if (chon != 0) {
                    a = rd.nextInt(35) + 40;
                    b = rd.nextInt(101 - a);
                    c = rd.nextInt(101 - (a + b));
                    d = 100 - (a + b + c);

                } else {
                    a = rd.nextInt(101);
                    b = rd.nextInt(101 - a);
                    c = rd.nextInt(101 - (a + b));
                    d = 100 - (a + b + c);
                }
            } else if (mCaseTrue == 2) {
                if (chon != 0) {
                    b = rd.nextInt(35) + 40;
                    a = rd.nextInt(101 - b);
                    c = rd.nextInt(101 - (a + b));
                    d = 101 - (a + b + c);

                } else {
                    a = rd.nextInt(101);
                    b = rd.nextInt(101 - a);
                    c = rd.nextInt(101 - (a + b));
                    d = 101 - (a + b + c);
                }
            } else if (mCaseTrue == 3) {
                if (chon != 0) {
                    c = rd.nextInt(35) + 40;
                    b = rd.nextInt(101 - c);
                    a = rd.nextInt(101 - (c + b));
                    d = 101 - (a + b + c);

                } else {
                    a = rd.nextInt(101);
                    b = rd.nextInt(101 - a);
                    c = rd.nextInt(101 - (a + b));
                    d = 101 - (a + b + c);
                }
            } else if (mCaseTrue == 4) {
                if (chon != 0) {
                    d = rd.nextInt(35) + 40;
                    b = rd.nextInt(101 - d);
                    c = rd.nextInt(101 - (d + b));
                    a = 101 - (d + b + c);

                } else {
                    a = rd.nextInt(101);
                    b = rd.nextInt(101 - a);
                    c = rd.nextInt(101 - (a + b));
                    d = 101 - (a + b + c);
                }
            }
        } else {

            switch (mCaseTrue) {
                case 1: {
                    if (chon != 0) {
                        a = rd.nextInt(35) + 60;
                    } else {
                        a = rd.nextInt(55);
                    }


                    if (mNamMuoi == 2) {
                        b = 101 - a;
                    } else if (mNamMuoi == 3) {
                        c = 101 - a;
                    } else {
                        d = 101 - a;
                    }
                    break;
                }
                case 2: {
                    if (chon != 0) {
                        b = rd.nextInt(35) + 60;
                    } else {
                        b = rd.nextInt(55);
                    }

                    if (mNamMuoi == 1) {
                        a = 101 - b;
                    } else if (mNamMuoi == 3) {
                        c = 101 - b;
                    } else {
                        d = 101 - b;
                    }
                    break;
                }
                case 3: {
                    if (chon != 0) {
                        c = rd.nextInt(35) + 60;
                    } else {
                        c = rd.nextInt(55);
                    }


                    if (mNamMuoi == 2) {
                        b = 101 - c;
                    } else if (mNamMuoi == 1) {
                        a = 101 - c;
                    } else {
                        d = 101 - c;
                    }
                    break;
                }
                case 4: {
                    if (chon != 0) {
                        d = rd.nextInt(35) + 60;
                    } else {
                        d = rd.nextInt(55);
                    }


                    if (mNamMuoi == 2) {
                        b = 101 - d;
                    } else if (mNamMuoi == 3) {
                        c = 101 - d;
                    } else if (mNamMuoi == 1) {
                        a = 101 - d;
                    }
                    break;
                }
            }
        }
        tvPA.setText("" + a + "%");
        tvPB.setText("" + b + "%");
        tvPC.setText("" + c + "%");
        tvPD.setText("" + d + "%");


        int tmgW = a * 7;

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(140, tmgW);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.rightMargin += 50;
        params.leftMargin += 100;
        params.bottomMargin += 60;
        btPA.setLayoutParams(params);

        tmgW = b * 7;
        RelativeLayout.LayoutParams paramsB = new RelativeLayout.LayoutParams(140, tmgW);
        paramsB.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        paramsB.leftMargin += 290;
        paramsB.bottomMargin += 60;
        btPB.setLayoutParams(paramsB);

        tmgW = c * 7;
        RelativeLayout.LayoutParams paramsC = new RelativeLayout.LayoutParams(140, tmgW);
        paramsC.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        paramsC.leftMargin += 480;
        paramsC.bottomMargin += 60;
        btPC.setLayoutParams(paramsC);


        tmgW = d * 7;
        RelativeLayout.LayoutParams paramsD = new RelativeLayout.LayoutParams(140, tmgW);
        paramsD.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        paramsD.leftMargin += 670;
        paramsD.bottomMargin += 60;
        btPD.setLayoutParams(paramsD);


    }

    /*
View 50/50
*/
    public void onClickNamMuoi(View view) {
        try {
            if (mSelect == 0) {
                doCallSound("nammuoi.mp3");
                mSelect = 5;
                ivNamMuoi.setImageResource(R.drawable.nammuoi2);
                ivNamMuoi.setEnabled(false);
                handler.postDelayed(myLoaiBo, 4000);
            }
        } catch (Exception ex) {

        }
    }

    /*
run loai bo
*/
    Runnable myLoaiBo = new Runnable() {
        @Override
        public void run() {

            myLoaiBo();
        }
    };

    /*
50/50
*/
    void myLoaiBo() {
        handler.removeCallbacks(myLoaiBo);
        ArrayList<Integer> lstTMG = new ArrayList<Integer>();
        if (mCaseTrue == 1) {
            lstTMG.add(2);
            lstTMG.add(3);
            lstTMG.add(4);
        } else if (mCaseTrue == 2) {
            lstTMG.add(1);
            lstTMG.add(3);
            lstTMG.add(4);
        } else if (mCaseTrue == 3) {
            lstTMG.add(2);
            lstTMG.add(1);
            lstTMG.add(4);
        } else if (mCaseTrue == 4) {
            lstTMG.add(2);
            lstTMG.add(3);
            lstTMG.add(1);
        }

        Random rd = new Random();
        int chon = rd.nextInt(lstTMG.size());
        int loai = lstTMG.get(chon);
        lstTMG.remove(chon);

        int chon1 = rd.nextInt(lstTMG.size());
        int loai1 = lstTMG.get(chon1);
        lstTMG.remove(chon1);


        switch (loai) {
            case 1: {
                btDaa.setText("");
                break;
            }
            case 2: {
                btDab.setText("");
                break;
            }
            case 3: {
                btDac.setText("");
                break;
            }
            case 4: {
                btDad.setText("");
                break;
            }
        }

        switch (loai1) {
            case 1: {
                btDaa.setText("");
                break;
            }
            case 2: {
                btDab.setText("");
                break;
            }
            case 3: {
                btDac.setText("");
                break;
            }
            case 4: {
                btDad.setText("");
                break;
            }
        }
        mSelect = 0;
        mNamMuoi = lstTMG.get(0);


    }


    public  void  onClickBtA(View view)
    {
        try {
            if (mSelect == 0) {
                mSelect=1;
                doCallSound("da.mp3");
                btDaa.setBackgroundResource(R.drawable.answer_panel_select);
                ivLaiVanSam.setImageResource(R.drawable.suyluan);
                handler.postDelayed(myDuaRa, 3700);
            }
        } catch (Exception ex) {

        }
    }

    public  void  onClickBtB(View view)
    {
        try {
            if (mSelect == 0) {
                mSelect=2;
                doCallSound("db.mp3");
                btDab.setBackgroundResource(R.drawable.answer_panel_select);
                ivLaiVanSam.setImageResource(R.drawable.suyluan);
                handler.postDelayed(myDuaRa, 3700);
            }
        } catch (Exception ex) {

        }
    }

    public  void  onClickBtC(View view)
    {
        try {
            if (mSelect == 0) {
                mSelect=3;
                doCallSound("dc.mp3");
                btDac.setBackgroundResource(R.drawable.answer_panel_select);
                ivLaiVanSam.setImageResource(R.drawable.suyluan);
                handler.postDelayed(myDuaRa, 3700);
            }
        } catch (Exception ex) {

        }
    }

    public  void  onClickBtD(View view)
    {
        try {
            if (mSelect == 0) {
                mSelect=4;
                doCallSound("dd.mp3");
                btDad.setBackgroundResource(R.drawable.answer_panel_select);
                ivLaiVanSam.setImageResource(R.drawable.suyluan);
                handler.postDelayed(myDuaRa, 3700);
            }
        } catch (Exception ex) {

        }
    }

    Runnable myDuaRa = new Runnable() {
        @Override
        public void run() {
            Random rd = new Random();
            int ckNow = rd.nextInt(4);
            if (ckNow != 0) {
                doCallSound("now1.mp3");
            } else {
                doCallSound("now2.mp3");
            }
            handler.postDelayed(myDapAn, 3000);
        }
    };

    Runnable myDapAn = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(myDuaRa);
            xuly();
        }
    };

    /*
   Xu ly dung hay sai
    */
    void xuly() {
        handler.removeCallbacks(myDapAn);
        if (mCaseTrue == mSelect) {

            ivLaiVanSam.setImageResource(R.drawable.traloidung);

            mMax = pref.getInt("maxlevel", 0);
            if (level > mMax) {

                editor.putInt("maxlevel", level);
                editor.commit();

                editor.putInt("maxtime",81-mTime);
                editor.commit();

            }

            level++;

            if (mSelect == 1) {
                doCallSound("true_a.mp3");
                btDaa.setBackgroundResource(R.drawable.answer_panel_true);
                btDaa.startAnimation(animation);


            } else if (mSelect == 2) {
                doCallSound("true_b.mp3");
                btDab.setBackgroundResource(R.drawable.answer_panel_true);
                btDab.startAnimation(animation);
            } else if (mSelect == 3) {
                doCallSound("true_c.mp3");
                btDac.setBackgroundResource(R.drawable.answer_panel_true);
                btDac.startAnimation(animation);
            } else if (mSelect == 4) {
                doCallSound("true_d.mp3");
                btDad.setBackgroundResource(R.drawable.answer_panel_true);
                btDad.startAnimation(animation);
            }
            if (level == 11 || level == 15 || level == 16) {
                handler.postDelayed(myBeforDungNhapNhay, 4000);
            } else {
                handler.postDelayed(myLevel, 4000);
            }


        } else {
            ivLaiVanSam.setImageResource(R.drawable.traloisai);


            if (mCaseTrue == 1) {
                doCallSound("lose_a.mp3");
                btDaa.setBackgroundResource(R.drawable.answer_panel_true);
                btDaa.startAnimation(animation);
            } else if (mCaseTrue == 2) {
                doCallSound("lose_b.mp3");
                btDab.setBackgroundResource(R.drawable.answer_panel_true);
                btDab.startAnimation(animation);
            } else if (mCaseTrue == 3) {
                doCallSound("lose_c.mp3");
                btDac.setBackgroundResource(R.drawable.answer_panel_true);
                btDac.startAnimation(animation);
            } else if (mCaseTrue == 4) {
                doCallSound("lose_d.mp3");
                btDad.setBackgroundResource(R.drawable.answer_panel_true);
                btDad.startAnimation(animation);
            }


            if (mSelect == 1) {

                btDaa.setBackgroundResource(R.drawable.answer_panel_false);
                btDaa.startAnimation(animation);
            } else if (mSelect == 2) {

                btDab.setBackgroundResource(R.drawable.answer_panel_false);
                btDab.startAnimation(animation);
            } else if (mSelect == 3) {

                btDac.setBackgroundResource(R.drawable.answer_panel_false);
                btDac.startAnimation(animation);
            } else if (mSelect == 4) {

                btDad.setBackgroundResource(R.drawable.answer_panel_false);
                btDad.startAnimation(animation);
            }

            handler.postDelayed(myDungNhapNhay, 4000);

        }
    }

    Runnable myBeforDungNhapNhay = new Runnable() {
        @Override
        public void run() {

            handler.removeCallbacks(myBeforDungNhapNhay);
            if (level == 11) {
                doCallSound("pass_good.mp3");
            } else if (level == 15) {
                doCallSound("pass_14.mp3");
            } else if (level == 16) {
                doCallSound("pass_15.mp3");
                mSelect = 0;

            }
            handler.postDelayed(myLevel, 10000);
        }
    };

    Runnable myDungNhapNhay = new Runnable() {
        @Override
        public void run() {
            onClickShowBackNull(1);
            doStopNhapNhay();
            if (mCaseTrue == mSelect) {
                subget();
            } else {
                if (mSelect == 0) {
                    doCallSound("pass_good.mp3");
                    handler.postDelayed(myKetThuc, 8000);
                } else {
                    doRestartGame();
                }
            }
            mSelect = 0;
        }
    };

    Runnable myLevel = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(myLevel);
            onClickShowBackNull(6);
            handler.postDelayed(nextlevl, 1000);
        }
    };

    Runnable nextlevl = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(nextlevl);
            doRank(level-1);
            handler.postDelayed(myDungNhapNhay, 1200);
        }
    };

    void doDefaultBackGround()
    {
        tvan1.setBackgroundResource(R.drawable.komau);
        tvan2.setBackgroundResource(R.drawable.komau);
        tvan3.setBackgroundResource(R.drawable.komau);
        tvan4.setBackgroundResource(R.drawable.komau);
        tvan5.setBackgroundColor(Color.parseColor("#339900"));
        tvan6.setBackgroundResource(R.drawable.komau);
        tvan7.setBackgroundResource(R.drawable.komau);
        tvan8.setBackgroundResource(R.drawable.komau);
        tvan9.setBackgroundResource(R.drawable.komau);
        tvan10.setBackgroundColor(Color.parseColor("#339900"));
        tvan11.setBackgroundResource(R.drawable.komau);
        tvan12.setBackgroundResource(R.drawable.komau);
        tvan13.setBackgroundResource(R.drawable.komau);
        tvan14.setBackgroundResource(R.drawable.komau);
        tvan15.setBackgroundColor(Color.parseColor("#339900"));
    }

    Runnable myKetThuc = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(myKetThuc);

            doRestartGame();
        }
    };



    /*
Thiet lap lai game
 */
    void doRestartGame() {
        try {





            AlertDialog.Builder  builder=    new AlertDialog.Builder(this)
                    .setTitle("" + getString(R.string.title_dungcuoc))
                    .setMessage("" + getString(R.string.content_dungcuoc))
                    .setIcon(R.drawable.thenao)


                    .setNegativeButton(R.string.chiase, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                            onClickShare();
                            onClickShowBackNull(0);
                        }
                    }).setNeutralButton(R.string.dung, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing


                            doCallSound("tambiet.mp3");
                            onClickShowBackLeft(0);

                            if (mInterstitialAd.isLoaded()) {
                                mInterstitialAd.show();
                            } else {
                                Log.d("TAG", "The interstitial wasn't loaded yet.");
                            }

                        }
                    })
                    .setCancelable(false)
                    ;


            AlertDialog alert = builder.create();
            alert.show();



            ((Button)alert.findViewById(android.R.id.button2)).setBackgroundResource(R.drawable.share);
            ((Button)alert.findViewById(android.R.id.button2)).setTextColor(getResources().getColor(android.R.color.white));

            ((Button)alert.findViewById(android.R.id.button3)).setBackgroundResource(R.drawable.dialog);
            ((Button)alert.findViewById(android.R.id.button3)).setTextColor(getResources().getColor(android.R.color.white));

            doSetDefault();
            doDefaultBackGround();
            mDemnguocHandler.removeCallbacks(mDemnguocRun);


        } catch (Exception ex) {

        }
    }

    private void doSetDefault() {
        mSelect = 0;
        level=1;
        ivNamMuoi.setImageResource(R.drawable.nammuoi);
        ivNamMuoi.setEnabled(true);
        ivKhanGia.setImageResource(R.drawable.hoikhangia);
        ivKhanGia.setEnabled(true);
        ivNguoiThan.setImageResource(R.drawable.nguoithan);
        ivNguoiThan.setEnabled(true);
        ivDoiCauHoi.setImageResource(R.drawable.doicauhoi);
        ivDoiCauHoi.setEnabled(true);


    }

    public void  onClickVanNien(View view)
    {
        try {
//            Intent myIten=new Intent(AiLaTrieuPhu.this,ScreenShot.class);
//            startActivity(myIten);
            finish();
        }catch (Exception ex)
        {

        }

    }

    public void onClickShare() {

        try {

            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

            // Add data to the intent, the receiving app will decide
            // what to do with it.
            share.putExtra(Intent.EXTRA_SUBJECT, "Bao Dan Tri voi Ai La Trieu Phu");
            share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + getApplicationContext().getPackageName());

            startActivity(Intent.createChooser(share, "Giới thiệu ứng dụng này cho bạn bè !"));
        } catch (Exception ex) {

        }
    }

    /*
Dung nhap nhay
*/
    void doStopNhapNhay() {
        try {
            handler.removeCallbacks(myDungNhapNhay);
            btDaa.clearAnimation();
            btDab.clearAnimation();
            btDac.clearAnimation();
            btDad.clearAnimation();
            btDaa.setBackgroundResource(R.drawable.answer_panel);
            btDab.setBackgroundResource(R.drawable.answer_panel);
            btDac.setBackgroundResource(R.drawable.answer_panel);
            btDad.setBackgroundResource(R.drawable.answer_panel);
//            mSelect = 0;
        } catch (Exception ex) {

        }
    }



         /*
    Tu dong chon dap an theo level
     */

    void subget() {

        try {
            showDemNguoc();

            ivLaiVanSam.setImageResource(R.drawable.hoi);
            if (level == 1) {

                doCallSound("hoi1.mp3");
               // handler.removeCallbacks(myStart);
            } else if (level == 2) {
                doCallSound("hoi2.mp3");
            } else if (level == 3) {
                doCallSound("hoi3.mp3");
            } else if (level == 4) {
                doCallSound("hoi4.mp3");
            } else if (level == 5) {
                doCallSound("hoi5.mp3");


            } else if (level == 6) {
                doCallSound("hoi6.mp3");
            } else if (level == 7) {
                doCallSound("hoi7.mp3");
            } else if (level == 8) {
                doCallSound("hoi8.mp3");
            } else if (level == 9) {
                doCallSound("hoi9.mp3");
            } else if (level == 10) {
                doCallSound("hoi10.mp3");

            } else if (level == 11) {
                doCallSound("hoi11.mp3");
            } else if (level == 12) {
                doCallSound("hoi12.mp3");
            } else if (level == 13) {
                doCallSound("hoi13.mp3");
            } else if (level == 14) {
                doCallSound("hoi14.mp3");
            } else if (level == 15) {
                doCallSound("hoi15.mp3");

            }

            ArrayList<Question> lstTMG = new ArrayList<Question>();
            for (int i = 0; i < lstVN.size(); i++) {
                if (Integer.parseInt(lstVN.get(i).getLevel()) != level) {
                    continue;
                }
                lstTMG.add(lstVN.get(i));
            }

            Random rd = new Random();
            int k = rd.nextInt(lstTMG.size());
            tvQuestion.setText("Câu " + level + ": " + lstTMG.get(k).getQuestion());
            btDaa.setText("A: " + lstTMG.get(k).getCasea());
            btDab.setText("B: " + lstTMG.get(k).getCaseb());
            btDac.setText("C: " + lstTMG.get(k).getCasec());
            btDad.setText("D: " + lstTMG.get(k).getCased());
            mCaseTrue = Integer.parseInt(lstTMG.get(k).getTruecase());
            mNamMuoi = 0;
//            doRank(level);
        } catch (Exception ex) {

        }

    }

    /*
Tang hang cau hoi sau moi lan tyra loi dung
*/
    void doRank(int lv) {
        doDefaultBackGround();
        switch (lv) {

            case 1: {
                tvan1.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 2: {
                tvan2.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 3: {
                tvan3.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 4: {
                tvan4.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 5: {
                tvan5.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 6: {
                tvan6.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 7: {
                tvan7.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 8: {
                tvan8.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 9: {
                tvan9.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 10: {
                tvan10.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 11: {
                tvan11.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 12: {
                tvan12.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 13: {
                tvan13.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 14: {
                tvan14.setBackgroundColor(Color.parseColor("#008080"));
                break;
            }
            case 15: {
                tvan15.setBackgroundColor(Color.parseColor("#008080"));
                level = 1;
                break;
            }
        }
    }


    /*
run loai bo
*/
    Runnable myLoadSha = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(myLoadSha);
            onClickShowBackRight(1);
            subget();
        }
    };


    public  void  onClickPlay(View view)
    {
        onClickShowBackRight(2);
        doCallSound("chungta.mp3");
        handler.postDelayed(myLoadSha, 3500);
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {

        player.stop();
        player.reset();
        alowMP3 = false;
        // finish();

        super.onPause();
    }



    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();

        alowMP3 = true;
        doCallSound("batdauvaogame.mp3");
    }



    /*
Phat am thanh
*/
    void doCallSound(String mp3) {

        if (alowMP3) {
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
                Question n1 = new Question();


                n1.setQuestion(((Question) item).getQuestion().trim());
                n1.set_id(((Question) item).get_id().trim());
                n1.setLevel(((Question) item).getLevel().trim());
                n1.setCasea(((Question) item).getCasea().trim());
                n1.setCaseb(((Question) item).getCaseb().trim());
                n1.setCasec(((Question) item).getCasec().trim());
                n1.setCased(((Question) item).getCased().trim());
                n1.setTruecase(((Question) item).getTruecase().trim());

                lstVN.add(n1);



            }


            datasource.close();
        } catch (Exception ex) {

        }


    }


    /*
Chuyen layout voi hieu ung tu ben phai bay sang
 */
    public void onClickShowBackRight(int index) {

        try {
            viewFlipper.setInAnimation(this, R.anim.inback);
            viewFlipper.setOutAnimation(this, R.anim.outback);
            viewFlipper.setDisplayedChild(index);
        } catch (Exception ex) {

        }
    }

    /*
Chuyen layout voi hieu ung tu ben trai bay sang
 */
    public void onClickShowBackLeft(int index) {

        try {
            viewFlipper.setInAnimation(this, R.anim.innext);
            viewFlipper.setOutAnimation(this, R.anim.outnext);
            viewFlipper.setDisplayedChild(index);
        } catch (Exception ex) {

        }
    }


    /*
    Chuyen layout khong hieu ung
     */
    public void onClickShowBackNull(int index) {

        try {
            viewFlipper.setInAnimation(null);
            viewFlipper.setOutAnimation(null);
            viewFlipper.setDisplayedChild(index);
        } catch (Exception ex) {

        }
    }

    @Override
    public void onBackPressed() {
//        try {
//            finish();
//        }catch (Exception ex)
//        {
//
//        }

        //super.onBackPressed();

    }



}
