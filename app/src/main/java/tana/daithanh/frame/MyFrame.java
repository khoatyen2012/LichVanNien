package tana.daithanh.frame;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.TextView;

import tana.daithanh.lichvannien.R;

/**
 * Created by Administrator on 23/12/2017.
 */

public class MyFrame extends Fragment {


    Integer ngayduong;
    Integer thangduong;
    Integer namduong;
    public MyFrame()
    {
        super();
    }

    @SuppressLint("ValidFragment")
    public MyFrame(Integer ngayduong,Integer thangduong,Integer namduong) {
        super();
        this.ngayduong = ngayduong;
        this.thangduong=thangduong;
        this.namduong=namduong;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frm_layout, container, false);

        TextView txtNgayDuong=(TextView)view.findViewById(R.id.tvDay);
        txtNgayDuong.setText(""+ngayduong);
        TextView txtThangDuong=(TextView)view.findViewById(R.id.tvMonthYear);
        txtThangDuong.setText("Tháng "+thangduong+" Năm "+namduong);

        return view;
    }

}
