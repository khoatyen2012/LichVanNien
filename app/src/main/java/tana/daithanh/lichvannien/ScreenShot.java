package tana.daithanh.lichvannien;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.Random;


import tana.daithanh.database.DataSourceDanhNgon;

public class ScreenShot extends AppCompatActivity {

    private DataSourceDanhNgon datasource;

   // int trangthai=0;
    Handler handler = new Handler();
    ImageView ivKySu;
    Random rd=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_shot);
        try
        {
        ivKySu=(ImageView)findViewById(R.id.ivKySu);

        Integer chon=rd.nextInt(2);
        if(chon==1)
        {
            ivKySu.setImageResource(R.drawable.nambetrap);
        }

        datasource = new DataSourceDanhNgon(this);





        handler.postDelayed(myLoad, 1300);
        }catch (Exception ex)
        {

        }


    }

    /*
  Run khan gia
   */
    Runnable myLoad = new Runnable() {
        @Override
        public void run() {

            Intent myIten=new Intent(ScreenShot.this,MainActivity.class);
            startActivity(myIten);
            finish();
        }
    };

    @Override
    protected void onResume() {
//        if(trangthai==0)
//        {
//            trangthai++;
//        }else
//        {
//            finish();
//        }
        super.onResume();
    }
}
