package tana.daithanh.frame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import tana.daithanh.adapter.MonthAdapter;
import tana.daithanh.lichvannien.MainActivity;
import tana.daithanh.lichvannien.R;
import tana.daithanh.mode.MonthCalender;

/**
 * Created by Manh on 1/6/2018.
 */

public class MyFrameMonth extends Fragment {

    String namefarme;
    MonthAdapter monthAdapter;
    Context context;
    ArrayList<MonthCalender>lst;
    GridView gridView;
    Calendar c;
    public MyFrameMonth()
    {
        super();
    }

    @SuppressLint("ValidFragment")
    public MyFrameMonth(String namefarme) {
        super();
        this.namefarme = namefarme;


    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.frm_month_layout, container, false);

        context=container.getContext();

        lst=new ArrayList<MonthCalender>();


        for(int i=0;i<31;i++)
        {
            MonthCalender thn=new MonthCalender(""+i,""+i);
            lst.add(thn);
        }

        monthAdapter=new MonthAdapter(context,lst);

         gridView=(GridView)view.findViewById(R.id.gvMonth);


        return view;
    }

    @Override
    public void onResume() {

        c = Calendar.getInstance();

        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, 4);
        c.add(Calendar.DAY_OF_MONTH, -1);
        int ngau= c.get(Calendar.DAY_OF_MONTH);

       Log.e("hhh","KK:"+ngau);

        gridView.setAdapter(monthAdapter);
        super.onResume();
    }
}

