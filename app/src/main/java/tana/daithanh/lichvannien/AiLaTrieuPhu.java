package tana.daithanh.lichvannien;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Random;

import tana.daithanh.database.DataSourceALTP;
import tana.daithanh.database.Question;

public class AiLaTrieuPhu extends Activity {

    ViewFlipper viewFlipper;

    private DataSourceALTP datasource;
    private ArrayList<Question> lstVN;
    MediaPlayer player;
    boolean alowMP3 = false;
    Handler handler = new Handler();
    private int level = 1;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_la_trieu_phu);

        viewFlipper =(ViewFlipper) findViewById(R.id.vfALTP);
        tvQuestion=(TextView) findViewById(R.id.tvQuestion);
        btDaa=(TextView)findViewById(R.id.btA);
        btDab=(TextView)findViewById(R.id.btB);
        btDac=(TextView)findViewById(R.id.btC);
        btDad=(TextView)findViewById(R.id.btD);
        ivLaiVanSam=(ImageView)findViewById(R.id.ivLaiVanSam);
        ivNamMuoi=(ImageView) findViewById(R.id.ivNamMuoi);

        datasource = new DataSourceALTP(this);
        datasource.open();
        lstVN = new ArrayList<Question>();
        player = new MediaPlayer();

        animation.setDuration(500); // duration - half a second
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE);

        threadLoadData();

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
                handler.postDelayed(myDungNhapNhay, 4000);
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
                level = 1;
            }
            handler.postDelayed(myDungNhapNhay, 10000);
        }
    };

    Runnable myDungNhapNhay = new Runnable() {
        @Override
        public void run() {

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


        } catch (Exception ex) {

        }
    }

    private void doSetDefault() {
        mSelect = 0;
        level=1;
//        ivNamMuoi.setImageResource(R.drawable.nammuoi);
//        ivNamMuoi.setEnabled(true);
//        ivHoiKhanGia.setImageResource(R.drawable.hoikhangia);
//        ivHoiKhanGia.setEnabled(true);
//        ivNguoiThan.setImageResource(R.drawable.nguoithan);
//        ivNguoiThan.setEnabled(true);
//        ivDoiCauHoi.setImageResource(R.drawable.doicauhoi);
//        ivDoiCauHoi.setEnabled(true);

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

            startActivity(Intent.createChooser(share, "Share link!"));
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
            //showDemNguoc();

           // ivLaiVanSam.setImageResource(R.drawable.hoi);
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
//            mNamMuoi = 0;
//            doRank(level);
        } catch (Exception ex) {

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
        super.onBackPressed();
        finish();
    }



}
