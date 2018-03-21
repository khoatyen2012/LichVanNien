package tana.daithanh.lichvannien;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import tana.daithanh.adapter.HopAdapter;
import tana.daithanh.mode.QuestionHop;

public class PhongHop extends Activity {

    ViewFlipper viewFlipper;
    EditText etCode;
    ListView lvHop;
    ArrayList<QuestionHop> lst;
    HopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_hop);
        lvHop=(ListView)findViewById(R.id.lvHop);

        viewFlipper=(ViewFlipper)findViewById(R.id.vfHopKin);

        etCode=(EditText)findViewById(R.id.etCode);

        lst=new ArrayList<QuestionHop>();


      for(int i=0;i<10;i++)
      {
          QuestionHop qa=new QuestionHop("Xem quảng cáo ủng hộ chúng tôi. Ứng dụng này hoàn toàn miễn phí.","");
          lst.add(qa);
      }
       adapter=new HopAdapter(this,lst);
        lvHop.setAdapter(adapter);


//        etCode.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (i == KeyEvent.KEYCODE_ENTER)) {
//                    // Perform action on key press
////hideKeyboard(etCode);
//                    return true;
//                }
//                return false;
//            }
//        });



    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public  void  onClickCode(View view)
    {
        viewFlipper.setDisplayedChild(1);
    }
}
