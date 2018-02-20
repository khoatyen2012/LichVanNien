package tana.daithanh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tana.daithanh.lichvannien.R;
import tana.daithanh.mode.UserAltp;


/**
 * Created by trangtay on 30/3/2015.
 */
public class RankAdapter extends ArrayAdapter<UserAltp> {

    private ArrayList<UserAltp> dsUSer;
    private LayoutInflater inflater;

    public RankAdapter(Context context, int resource, ArrayList<UserAltp> objects) {
        super(context, resource, objects);

        this.dsUSer = objects;
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.altp_item, null);

            viewHolder.tvName = (TextView) convertView
                    .findViewById(R.id.tvItemName);
            viewHolder.tvTop = (TextView) convertView
                    .findViewById(R.id.tvTopItem);
            viewHolder.tvLevel = (TextView) convertView
                    .findViewById(R.id.tvLevelItem);
            viewHolder.tvYear = (TextView) convertView
                    .findViewById(R.id.tvYearItem);
            viewHolder.tvSecond = (TextView) convertView
                    .findViewById(R.id.tvSecondItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        final UserAltp benh = dsUSer.get(position);

        if (benh != null) {


            String myName=""+benh.getName();
            if(myName.length()>25)
            {
                myName=myName.substring(0,23)+"...";
            }
            viewHolder.tvName.setText(myName);
            viewHolder.tvSecond.setText(benh.getTime()+" giây");
            viewHolder.tvLevel.setText("Câu "+benh.getLevel());
            viewHolder.tvYear.setText(""+benh.getYear());
            if(position+1==1)
            {
                viewHolder.tvTop.setText("1st");
            }else if(position+1==2)
            {
                viewHolder.tvTop.setText("2nd");
            }else if(position+1==3)
            {
                viewHolder.tvTop.setText("3rd");
            }else
            {
                viewHolder.tvTop.setText("Top "+(position+1));
            }
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvTop;
        TextView tvLevel;
        TextView tvYear;
        TextView tvSecond;
    }
}
