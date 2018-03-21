package tana.daithanh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tana.daithanh.lichvannien.R;
import tana.daithanh.mode.MonthCalender;
import tana.daithanh.mode.QuestionHop;

/**
 * Created by Administrator on 21/03/2018.
 */

public class HopAdapter extends BaseAdapter {

    private Context context;
    private final ArrayList<QuestionHop> lstHop;



    public HopAdapter(Context context, ArrayList<QuestionHop> websiteValues) {

        this.context = context;
        this.lstHop = websiteValues;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;

        if(viewGroup==null)
        {
            gridView=new View(context);
            gridView=inflater.inflate(R.layout.item_hop, null);
            TextView textQuestion=(TextView) gridView.findViewById(R.id.tvItemQuestion);
            String nHop=lstHop.get(i).getQuestion();
            textQuestion.setText(""+nHop);

        }
        else
        {
            gridView = (View) viewGroup;
        }
        return gridView;
    }

    @Override
    public int getCount() {
        return lstHop.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
