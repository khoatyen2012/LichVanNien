package tana.daithanh.frame;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
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
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import tana.daithanh.database.DanhNgon;
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
    String content="Phồn vinh cuộc sống việt";
    String author="Tân Á Đại Thành";
    AmDuong amDuong=new AmDuong();
    ArrayList<DanhNgon> lstVN;

    TextView txtGio;
    TextView tvSangChieu;
    TextView txtNgayDuong;
    TextView txtThangDuong;
    TextView tvNgayAm;
    TextView tvThangAm;
    ImageButton ibToDay;
    TextView tvTitleVannien;
    TextView tvCadao;
    TextView tvAuthor;
    TextView tvThu;
    RelativeLayout rlTitleTop;



    public MyFrame()
    {
        super();
    }

    @SuppressLint("ValidFragment")
    public MyFrame(Integer ViTri,ArrayList<DanhNgon> lstVN) {
        super();

          this.ViTri=ViTri;
        this.lstVN=lstVN;


    }

    @Override
    public void onResume() {


        c = Calendar.getInstance();
        // Log.e("Gio:",":"+c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE));
        Integer dGio=c.get(Calendar.HOUR_OF_DAY);
        String dPhut=""+c.get(Calendar.MINUTE);
        String dQuyTime="";

        if(dGio<=10)
        {
            dQuyTime="Giờ Sáng";
        }else  if(dGio<=13)
        {
            dQuyTime="Giờ Chưa";
        }else  if(dGio<=17)
        {
            dQuyTime="Giờ Chiều";
        }else {
            dQuyTime="Giờ Tối";
        }

        if(dGio>12)
        {
            dGio=dGio-12;
        }

        if(dPhut.length()==1)
        {
            dPhut="0"+dPhut;
        }


        if(dGio<10) {
            txtGio.setText("0" + dGio + ":" + dPhut);
        }else
        {
            txtGio.setText("" + dGio + ":" + dPhut);
        }


        tvSangChieu.setText(""+dQuyTime);



        if(ViTri==183)
        {
            ibToDay.setVisibility(View.GONE);
            tvTitleVannien.setText("Lịch Vạn Niên");
        }else
        {
            ibToDay.setVisibility(View.VISIBLE);

            int tam=ViTri-183;
            c.add(Calendar.DATE,tam);
            tvTitleVannien.setText("Tân Á Đại Thành");
        }


        int thumay=c.get(Calendar.DAY_OF_WEEK);
       if(thumay==1)
       {
           tvThu.setText("Chủ Nhật");
           tvThu.setTextColor(Color.parseColor("#FF0000"));
           txtNgayDuong.setTextColor(Color.parseColor("#FF0000"));
           txtThangDuong.setTextColor(Color.parseColor("#FF0000"));
           rlTitleTop.setBackgroundColor(Color.parseColor("#FF0000"));
       }else
       {
           tvThu.setText("Thứ "+thumay);
           if(thumay==2)
           {
               tvThu.setTextColor(Color.parseColor("#00AA00"));
               txtNgayDuong.setTextColor(Color.parseColor("#00AA00"));
               txtThangDuong.setTextColor(Color.parseColor("#00AA00"));
               rlTitleTop.setBackgroundColor(Color.parseColor("#00AA00"));
           }else if(thumay==3)
           {
               tvThu.setTextColor(Color.parseColor("#0099FF"));
               txtNgayDuong.setTextColor(Color.parseColor("#0099FF"));
               txtThangDuong.setTextColor(Color.parseColor("#0099FF"));
               rlTitleTop.setBackgroundColor(Color.parseColor("#0099FF"));
           }else if(thumay==4)
           {
               tvThu.setTextColor(Color.parseColor("#009966"));
               txtNgayDuong.setTextColor(Color.parseColor("#009966"));
               txtThangDuong.setTextColor(Color.parseColor("#009966"));
               rlTitleTop.setBackgroundColor(Color.parseColor("#009966"));
           }else if(thumay==5)
           {
               tvThu.setTextColor(Color.parseColor("#CC0099"));
               txtNgayDuong.setTextColor(Color.parseColor("#CC0099"));
               txtThangDuong.setTextColor(Color.parseColor("#CC0099"));
               rlTitleTop.setBackgroundColor(Color.parseColor("#CC0099"));
           }else if(thumay==6)
           {
               tvThu.setTextColor(Color.parseColor("#CD853F"));
               txtNgayDuong.setTextColor(Color.parseColor("#CD853F"));
               txtThangDuong.setTextColor(Color.parseColor("#CD853F"));
               rlTitleTop.setBackgroundColor(Color.parseColor("#CD853F"));
           }else if(thumay==7)
           {
               tvThu.setTextColor(Color.parseColor("#FF6A6A"));
               txtNgayDuong.setTextColor(Color.parseColor("#FF6A6A"));
               txtThangDuong.setTextColor(Color.parseColor("#FF6A6A"));
               rlTitleTop.setBackgroundColor(Color.parseColor("#FF6A6A"));
           }

       }


        ngayduong= c.get(Calendar.DAY_OF_MONTH);
        thangduong= c.get(Calendar.MONTH)+1;
        namduong = c.get(Calendar.YEAR);


        txtNgayDuong.setText(""+ngayduong);
        txtThangDuong.setText("Tháng "+thangduong+" Năm "+namduong);


        int am[]= amDuong.convertSolar2Lunar(ngayduong,thangduong,namduong,7);
        tvNgayAm.setText(""+am[0]);
        tvThangAm.setText(am[1]+"/"+am[2]);
        if(lstVN.size()>0)
        {


            Integer chon=am[0]*am[1];
            content=""+lstVN.get(chon).getContent();
            author=""+lstVN.get(chon).getAuthor();
        }


        tvCadao.setText(""+content);
        tvAuthor.setText(""+author);


        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frm_layout, container, false);


         txtGio=(TextView)view.findViewById(R.id.tvGio);
         tvSangChieu=(TextView)view.findViewById(R.id.tvSangChieu);
         txtNgayDuong=(TextView)view.findViewById(R.id.tvDay);
         txtThangDuong=(TextView)view.findViewById(R.id.tvMonthYear);
         tvNgayAm =(TextView) view.findViewById(R.id.tvNgayAm);
         tvThangAm =(TextView) view.findViewById(R.id.tvThangAm);
         ibToDay=(ImageButton)view.findViewById(R.id.ibToDay);
        tvTitleVannien=(TextView)view.findViewById(R.id.tvTitleVanNien);
        tvCadao=(TextView)view.findViewById(R.id.tvCadao);
        tvAuthor=(TextView)view.findViewById(R.id.tvAuthor);
        tvThu=(TextView)view.findViewById(R.id.tvThuMay);
        rlTitleTop=(RelativeLayout)view.findViewById(R.id.rlTitleTop);


        return view;
    }

}
