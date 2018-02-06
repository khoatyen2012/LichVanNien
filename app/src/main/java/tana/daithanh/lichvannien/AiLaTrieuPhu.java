package tana.daithanh.lichvannien;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

        datasource = new DataSourceALTP(this);
        datasource.open();
        lstVN = new ArrayList<Question>();
        player = new MediaPlayer();

        threadLoadData();

    }


    public  void  onClickBtA(View view)
    {
        try {
            if (mSelect == 0) {
                mSelect=1;
                doCallSound("da.mp3");
                btDaa.setBackgroundResource(R.drawable.answer_panel_select);
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
            }
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



}
