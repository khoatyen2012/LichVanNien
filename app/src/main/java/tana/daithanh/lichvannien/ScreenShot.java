package tana.daithanh.lichvannien;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import tana.daithanh.database.DataSourceDanhNgon;

public class ScreenShot extends AppCompatActivity {

    private DataSourceDanhNgon datasource;
    int trangthai=0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);

        datasource = new DataSourceDanhNgon(this);



        handler.postDelayed(myLoad, 3100);


    }

    /*
  Run khan gia
   */
    Runnable myLoad = new Runnable() {
        @Override
        public void run() {

            Intent myIten=new Intent(ScreenShot.this,MainActivity.class);
            startActivity(myIten);
        }
    };

    @Override
    protected void onResume() {
        if(trangthai==0)
        {
            trangthai++;
        }else
        {
            finish();
        }
        super.onResume();
    }
}
