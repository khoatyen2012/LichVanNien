package tana.daithanh.lichvannien;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class AiLaTrieuPhu extends Activity {

    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ai_la_trieu_phu);

        viewFlipper =(ViewFlipper) findViewById(R.id.vfALTP);
    }

    public  void  onClickPlay(View view)
    {
        viewFlipper.setDisplayedChild(1);
    }
}
