package tana.daithanh.frame;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import tana.daithanh.database.DanhNgon;
import tana.daithanh.lichvannien.MainActivity;
import tana.daithanh.lichvannien.R;
import tana.daithanh.thaotac.LunarYearTools;

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
    //AmDuong amDuong=new AmDuong();
    LunarYearTools amDuong=new LunarYearTools();
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
    RelativeLayout rlBg;
    Random rd=new Random();
    ImageView ivNam;
    ImageView ivNu;
    String ThongBaoSV="";
    TextView tvTitleGio;
    TextView tvTitleNgay;
    TextView tvTitleThanh;



    public MyFrame()
    {
        super();
    }

    @SuppressLint("ValidFragment")
    public MyFrame(Integer ViTri,ArrayList<DanhNgon> lstVN) {
        super();

      try {

          this.ViTri=ViTri;
          this.lstVN=lstVN;

      }catch (Exception ex)
      {

      }


    }

    @Override
    public void onResume() {




        try
        {
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
                tvThu.setTextColor(Color.parseColor("#FF6A6A"));
                txtNgayDuong.setTextColor(Color.parseColor("#FF6A6A"));
                txtThangDuong.setTextColor(Color.parseColor("#FF6A6A"));
                rlTitleTop.setBackgroundColor(Color.parseColor("#FF6A6A"));
            }else
            {
                tvThu.setText("Thứ "+thumay);
                if(thumay==2)
                {
                    tvThu.setTextColor(Color.parseColor("#00AA00"));
                    txtNgayDuong.setTextColor(Color.parseColor("#00AA00"));
                    txtThangDuong.setTextColor(Color.parseColor("#00AA00"));
                    rlTitleTop.setBackgroundColor(Color.parseColor("#00AA00"));
                }else if(thumay==3||thumay==7)
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


            ivNam.setVisibility(View.VISIBLE);
            ivNu.setVisibility(View.VISIBLE);

            if(am[0]==1 && am[1]==1)
            {
                content="Chúc mừng năm mới. Ngày mùng 1 tết cố truyền dân tộc";
                author="Xuân đã về";

                ivNam.setImageResource(R.drawable.namtetdo);
                ivNu.setImageResource(R.drawable.namtet);

            }else  if(am[0]==2 && am[1]==1)
            {
                content="Mùng 1 tết cha mùng 2 tết mẹ mùng 3 tết thầy";
                author="Mùng 2 tết";
                ivNam.setImageResource(R.drawable.nubanhtrung);
                ivNu.setImageResource(R.drawable.namduahau);
            }else  if(am[0]==3 && am[1]==1)
            {
                content="Mùng 3 tết thầy";
                author="Mùng 3 tết";

                ivNam.setImageResource(R.drawable.nubanhtrung);
                ivNu.setImageResource(R.drawable.nutetdo);

            }else  if(am[0]==15 && am[1]==1)
            {
                content="Ngày dằm tháng giêng";
                author="Tết nguyên tiêu";
            }else  if(am[0]==3 && am[1]==3)
            {
                content="Tết bánh trôi bánh tray";
                author="Tết hàn thực";
            }else  if(am[0]==10 && am[1]==3)
            {
                content="Ngày dỗ tổ hùng vương. Vua hùng đã có công dựng nước";
                author="Vua Hùng";

                ivNam.setImageResource(R.drawable.namvuahung);
                ivNu.setVisibility(View.GONE);

            }else  if(am[0]==15 && am[1]==4)
            {
                content="Ngày lễ phật đản";
                author="A di đà phật";
                ivNam.setImageResource(R.drawable.namsuphu);
                ivNu.setVisibility(View.GONE);
            }else  if(am[0]==5 && am[1]==5)
            {
                content="Ngày tết đoan ngọ";
                author="Tết đoan ngọ";



            }else  if(am[0]==15 && am[1]==7)
            {
                content="Công cha như núi thái sơn. Nghĩa mẹ như nước trong nguồn chảy ra";
                author="Ngày lễ vu lan";
                ivNam.setImageResource(R.drawable.namgiadinh);
                ivNu.setImageResource(R.drawable.nugiadinh);
            }else  if(am[0]==15 && am[1]==8)
            {
                content="Ngày tết trung thu";
                author="Chị hằng";
                ivNam.setImageResource(R.drawable.namduahau);
                ivNu.setVisibility(View.GONE);
            }else  if(am[0]==9 && am[1]==9)
            {
                content="Ngày tết cửu trùng";
                author="Diệt sâu bọ";

                ivNam.setImageResource(R.drawable.dietsaubo);
                ivNu.setVisibility(View.GONE);

            }else  if(am[0]==10 && am[1]==10)
            {
                content="Ngày tết thường tân";
                author="tết";
            }else  if(am[0]==15 && am[1]==10)
            {
                content="Ngày tết hạ nguyên";
                author="Nguyên";
            }else  if(am[0]==23 && am[1]==12)
            {
                content="Tiễn Ông Công Ông Táo Về Trời";
                author="Táo quân";

                ivNu.setImageResource(R.drawable.ongcongongtao);
                ivNam.setVisibility(View.GONE);

            }else  if(ngayduong==1 && thangduong==1)
            {
                content="Ngày tết dương lịch";
                author="Happy new year";
            }else  if(ngayduong==14 && thangduong==2)
            {
                content="Ngày lễ tình nhân";
                author="Valentine";

                ivNu.setImageResource(R.drawable.namtinhyeu);
                ivNam.setVisibility(View.GONE);

            }else  if(ngayduong==27 && thangduong==2)
            {
                content="Ngày thầy thuốc Việt Nam";
                author="Lương y như từ mẫu";

                ivNam.setImageResource(R.drawable.nambacsi);
                ivNu.setVisibility(View.GONE);

            }else  if(ngayduong==8 && thangduong==3)
            {
                content="Ngày quốc tế phụ nữ";
                author="Yêu thương";

                ivNu.setImageResource(R.drawable.nuaobetrap);
                ivNam.setImageResource(R.drawable.nudoinon);

            }else  if(ngayduong==26 && thangduong==3)
            {
                content="Ngày thành lập Đoàn TNCS Hồ Chí Minh";
                author="Hồ Chí Minh";
            }else  if(ngayduong==1 && thangduong==4)
            {
                content="Ngày cá tháng 4";
                author="Nói dối";
            }else  if(ngayduong==30 && thangduong==4)
            {
                content="GIẢI PHÓNG MIỀN NAM";
                author="Giải Phóng";

                ivNam.setImageResource(R.drawable.nuhaiquan);
                ivNu.setImageResource(R.drawable.nubodoi);

            }else  if(ngayduong==1 && thangduong==5)
            {
                content="Ngày quốc tế lao động";
                author="Lao động";

                ivNam.setImageResource(R.drawable.namkysu);
                ivNu.setImageResource(R.drawable.nudoixe);

            }else  if(ngayduong==7 && thangduong==5)
            {
                content="Ngày chiến thắng điện biên phủ";
                author="Đừng ngủ quên trên chiến thắng";

                ivNam.setImageResource(R.drawable.namcongan);
                ivNu.setImageResource(R.drawable.nubodoi);

            }else  if(ngayduong==13 && thangduong==5)
            {
                content="Ngày của mẹ !";
                author="Mẹ yêu";
                ivNu.setImageResource(R.drawable.nugiadinh);
                ivNam.setVisibility(View.GONE);
            }else  if(ngayduong==19 && thangduong==5)
            {
                content="Ngày sinh chủ tịch Hồ Chí Minh";
                author="Sinh nhật bác";
            }else  if(ngayduong==1 && thangduong==6)
            {
                content="Ngày quốc tế thiếu nhi";
                author="Trẻ em hôm nay";
                ivNam.setImageResource(R.drawable.namgiadinh);
                ivNu.setImageResource(R.drawable.nugiadinh);
            }else  if(ngayduong==17 && thangduong==6)
            {
                content="Ngày của cha";
                author="Nợ cha 1 sự nghiệp";
                ivNam.setImageResource(R.drawable.namgiadinh);
                ivNu.setVisibility(View.GONE);
            }
            else  if(ngayduong==21 && thangduong==6)
            {
                content="Ngày báo chí Việt Nam";
                author="Balo";
            }else  if(ngayduong==28 && thangduong==6)
            {
                content="Ngày gia đình Việt Nam";
                author="Gia Đình";
                ivNam.setImageResource(R.drawable.namgiadinh);
                ivNu.setImageResource(R.drawable.nugiadinh);
            }else  if(ngayduong==11 && thangduong==7)
            {
                content="Ngày dân số thế giới";
                author="Kế Hoạch Hóa Gia Đình";
            }else  if(ngayduong==27 && thangduong==7)
            {
                content="Ngày thương binh liệt sĩ";
                author="Tổ quốc ghi công";

                ivNam.setImageResource(R.drawable.namcongan);
                ivNu.setImageResource(R.drawable.nuhaiquan);

            }else  if(ngayduong==28 && thangduong==7)
            {
                content="Ngày công đoàn Việt nam";
                author="Đoàn kết";
                ivNu.setImageResource(R.drawable.codosaovang);
                ivNam.setVisibility(View.GONE);
            }else  if(ngayduong==19 && thangduong==8)
            {
                content="Ngày tổng khởi nghĩa";
                author="Cách mạng tháng 8";

                ivNam.setImageResource(R.drawable.namcongan);
                ivNu.setImageResource(R.drawable.nuhaiquan);

            }else  if(ngayduong==2 && thangduong==9)
            {
                content="Ngày quốc khánh";
                author="Cờ đỏ";

                ivNu.setImageResource(R.drawable.codosaovang);
                ivNam.setVisibility(View.GONE);

            }else  if(ngayduong==10 && thangduong==9)
            {
                content="Ngày thành lập mặt trận tổ quốc Việt nam";
                author="Việt Nam";

                ivNam.setImageResource(R.drawable.namcongan);
                ivNu.setImageResource(R.drawable.nuhaiquan);

            }else  if(ngayduong==1 && thangduong==10)
            {
                content="Ngày quốc tế người cao tuổi";
                author="Kĩnh lão đắc thọ";

            }else  if(ngayduong==10 && thangduong==10)
            {
                content="Ngày giải phóng Thủ Đô";
                author="Hà Nội";

                ivNam.setImageResource(R.drawable.namcongan);
                ivNu.setImageResource(R.drawable.nuhaiquan);

            }else  if(ngayduong==13 && thangduong==10)
            {
                content="Ngày doanh nhân Việt Nam";
                author="Startup";
                ivNam.setImageResource(R.drawable.namdoanhnhan);
                ivNu.setVisibility(View.GONE);
            }else  if(ngayduong==20 && thangduong==10)
            {
                content="Ngày phụ nữ Việt Nam";
                author="Hoa Hồng Có Gai";
                ivNu.setImageResource(R.drawable.nuaobetrap);
                ivNam.setImageResource(R.drawable.nudoinon);

            }else  if(ngayduong==31 && thangduong==10)
            {
                content="Ngày Hallowen";
                author="Bí Ngô";

                ivNu.setImageResource(R.drawable.nuhalowen);
                ivNam.setVisibility(View.GONE);

            }else  if(ngayduong==9 && thangduong==11)
            {
                content="Ngày pháp luật Việt Nam";
                author="pháp luật";
            }else  if(ngayduong==20 && thangduong==11)
            {
                content="Ngày Nhà Giáo Việt Nam";
                author="Bụi phấn";

                ivNam.setImageResource(R.drawable.namthaygiao);
                ivNu.setVisibility(View.GONE);

            }else  if(ngayduong==23 && thangduong==11)
            {
                content="Ngày thành lập hội chữ thập đỏ Việt Nam";
                author="Cộng đồng";

                ivNam.setImageResource(R.drawable.nambacsi);
                ivNu.setVisibility(View.GONE);

            }else  if(ngayduong==1 && thangduong==12)
            {
                content="Ngày toàn thế giới chống xi-đa";
                author="HIV AIDS";

                ivNam.setImageResource(R.drawable.nambacsi);
                ivNu.setVisibility(View.GONE);

            }else  if(ngayduong==19 && thangduong==12)
            {
                content="Ngày toàn quốc kháng chiến";
                author="Cách mạng tháng 12";
                ivNam.setImageResource(R.drawable.namcongan);
                ivNu.setImageResource(R.drawable.nuhaiquan);

            }else  if(ngayduong==22 && thangduong==12)
            {
                content="Ngày thành lập quân đội nhân dân Việt Nam";
                author="CF";

                ivNam.setImageResource(R.drawable.namcongan);
                ivNu.setVisibility(View.GONE);
            }else  if(ngayduong==24 && thangduong==12)
            {
                content="Ngày lễ giáng sinh";
                author="Noel";
                rlBg.setBackgroundResource(R.drawable.giangsinh);
                ivNam.setVisibility(View.GONE);
                ivNu.setVisibility(View.GONE);
            }else  if(ngayduong==25 && thangduong==12)
            {
                content="Giáng sinh an lành";
                author="Noel";
                rlBg.setBackgroundResource(R.drawable.giangsinh);
                ivNam.setVisibility(View.GONE);
                ivNu.setVisibility(View.GONE);
            }
            else {

                if(lstVN.size()>0)
                {


                    Integer chon=am[0]+(am[1]*31);
                    content=""+lstVN.get(chon).getContent();
                    author=""+lstVN.get(chon).getAuthor();

                    chon=rd.nextInt(7);
                    if(chon==1)
                    {
                        rlBg.setBackgroundResource(R.drawable.nenmua);
                    }else if(chon==2)
                    {
                        rlBg.setBackgroundResource(R.drawable.nentuyet);
                    }else if(chon==3)
                    {
                        rlBg.setBackgroundResource(R.drawable.nenvang);
                    }else
                    {
                        rlBg.setBackgroundResource(R.drawable.nennang);
                    }


                    Integer chonNam=rd.nextInt(16);
                    Integer chonNu=rd.nextInt(16);
                    switch (chonNam)
                    {
                        case 0:
                            ivNam.setImageResource(R.drawable.namdodam);
                            break;
                        case 1:
                            ivNam.setImageResource(R.drawable.namtim);
                            break;
                        case 2:
                            ivNam.setImageResource(R.drawable.namxanhduong);
                            break;
                        case 3:
                            ivNam.setImageResource(R.drawable.namve);
                            break;
                        case 4:
                            ivNam.setImageResource(R.drawable.namkysu);
                            break;
                        case 5:
                            ivNam.setImageResource(R.drawable.namlichviet);
                            break;
                        case 6:
                            ivNam.setImageResource(R.drawable.namtana);
                            break;
                        case 7:
                            ivNam.setImageResource(R.drawable.namdothuphap);
                            break;
                        case 8:
                            ivNam.setImageResource(R.drawable.namdoanhnhan);
                            break;
                        case 9:
                            ivNam.setImageResource(R.drawable.nunhayday);
                            break;
                        case 10:
                            ivNam.setImageResource(R.drawable.nunhayloco);
                            break;
                        case 11:
                            ivNam.setImageResource(R.drawable.namnhochoang);
                            break;
                        case 12:
                            ivNam.setImageResource(R.drawable.namongphoc);
                            break;
                        case 13:
                            ivNam.setImageResource(R.drawable.namthandong);
                            break;
                        case 14:
                            ivNam.setImageResource(R.drawable.nusuueonum);
                            break;
                        case 15:
                            ivNu.setImageResource(R.drawable.namdaurach);
                            break;
                        default:
                            ivNam.setImageResource(R.drawable.nambetrap);
                            break;
                    }


                    switch (chonNu)
                    {
                        case 0:
                            ivNu.setImageResource(R.drawable.nudodam);
                            break;

                        case 1:
                            ivNu.setImageResource(R.drawable.nutim);
                            break;
                        case 2:
                            ivNu.setImageResource(R.drawable.nuaodai);
                            break;
                        case 3:
                            ivNu.setImageResource(R.drawable.nudoimu);
                            break;

                        case 4:
                            ivNu.setImageResource(R.drawable.nuaobetrap);
                            break;

                        case 5:
                            ivNu.setImageResource(R.drawable.nuvanphong);
                            break;
                        case 6:
                            ivNu.setImageResource(R.drawable.nudoinon);
                            break;

                        case 7:
                            ivNu.setImageResource(R.drawable.ongcongongtao);
                            break;

                        case 8:
                            ivNu.setImageResource(R.drawable.namxanhthuphap);
                            break;
                        case 9:
                            ivNu.setImageResource(R.drawable.nambatuat);
                            break;

                        case 10:
                            ivNu.setImageResource(R.drawable.namcameo);
                            break;
                        case 11:
                            ivNu.setImageResource(R.drawable.namongphoc1);
                            break;
                        case 12:
                            ivNu.setImageResource(R.drawable.namtrap);
                            break;
                        case 13:
                            ivNu.setImageResource(R.drawable.nusuueo);
                            break;
                        default:
                            ivNu.setImageResource(R.drawable.nudoixe);
                            break;
                    }

                    Integer chonNamNu=rd.nextInt(6);
                    if(chonNamNu<=1)
                    {
                        ivNam.setVisibility(View.GONE);
                    }else if(chonNamNu<=4)
                    {
                        ivNu.setVisibility(View.GONE);
                    }

                }

            }

            if(ViTri==183&&ThongBaoSV!="")
            {
                tvCadao.setText("" + ThongBaoSV);
                tvAuthor.setText("Tân Á Đại Thành");
            }else {
                tvCadao.setText("" + content);
                tvAuthor.setText("" + author);
            }

        }catch (Exception ex)
        {

        }


        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frm_layout, container, false);


     try {

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
         rlBg=(RelativeLayout)view.findViewById(R.id.rlBg);
         ivNam=(ImageView)view.findViewById(R.id.ivNam);
         ivNu=(ImageView)view.findViewById(R.id.ivNu);

          tvTitleGio=(TextView)view.findViewById(R.id.tvTitleGio);
          tvTitleNgay=(TextView)view.findViewById(R.id.tvTitleNgay);
          tvTitleThanh=(TextView)view.findViewById(R.id.tvTitleThanh);


         Typeface type = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Flamenco.ttf");
         tvTitleVannien.setTypeface(type);
         tvTitleGio.setTypeface(type);
         tvTitleNgay.setTypeface(type);
         tvTitleThanh.setTypeface(type);

         MainActivity activity =(MainActivity) getActivity();
         ThongBaoSV=""+activity.ThongBaoSV;

     }catch (Exception ex)
     {

     }


        return view;
    }

}
