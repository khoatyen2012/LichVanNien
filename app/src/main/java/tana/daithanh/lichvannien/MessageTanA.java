package tana.daithanh.lichvannien;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MessageTanA extends Activity {

    TextView textMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_tan);
        textMessage=findViewById(R.id.tvMessage);

//        if(getIntent().getExtras()!=null){
//            //do your stuff
//            Log.e("khac","deo ");
//        }else{
//            //do that you normally do
//            Log.e("khac","null roi");
//        }

        Intent intent = getIntent();
        String msg = intent.getStringExtra("content");
        textMessage.setText(msg);

    }
}
