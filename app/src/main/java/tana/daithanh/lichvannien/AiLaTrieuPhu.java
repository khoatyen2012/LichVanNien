package tana.daithanh.lichvannien;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ViewFlipper;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_la_trieu_phu);

        viewFlipper =(ViewFlipper) findViewById(R.id.vfALTP);

        datasource = new DataSourceALTP(this);
        datasource.open();
        lstVN = new ArrayList<Question>();
        player = new MediaPlayer();

        threadLoadData();

    }

    /*
run loai bo
*/
    Runnable myLoadSha = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(myLoadSha);
            onClickShowBackRight(1);
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
