package tana.daithanh.frame;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.TextView;

import java.util.Calendar;

import tana.daithanh.lichvannien.R;
import tana.daithanh.thaotac.AmDuong;

/**
 * Created by Administrator on 23/12/2017.
 */

public class MyFrame extends Fragment {

    Calendar c;
    Integer ngayduong;
    Integer thangduong;
    Integer namduong;
    Integer ViTri;
    AmDuong amDuong=new AmDuong();
    public MyFrame()
    {
        super();
    }

    @SuppressLint("ValidFragment")
    public MyFrame(Integer ViTri) {
        super();

          this.ViTri=ViTri;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frm_layout, container, false);

        c = Calendar.getInstance();
        Log.e("Gio:",":"+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));
        if(ViTri==183)
        {

        }else
        {
            int tam=ViTri-183;
            c.add(Calendar.DATE,tam);
        }
        ngayduong= c.get(Calendar.DAY_OF_MONTH);
        thangduong= c.get(Calendar.MONTH)+1;
        namduong = c.get(Calendar.YEAR);

        TextView txtNgayDuong=(TextView)view.findViewById(R.id.tvDay);
        txtNgayDuong.setText(""+ngayduong);
        TextView txtThangDuong=(TextView)view.findViewById(R.id.tvMonthYear);
        txtThangDuong.setText("Tháng "+thangduong+" Năm "+namduong);

        TextView tvNgayAm =(TextView) view.findViewById(R.id.tvNgayAm);
        TextView tvThangAm =(TextView) view.findViewById(R.id.tvThangAm);
        int am[]= amDuong.convertSolar2Lunar(ngayduong,thangduong,namduong,7);
        tvNgayAm.setText(""+am[0]);
        tvThangAm.setText(am[1]+"/"+am[2]);

        return view;
    }

}
