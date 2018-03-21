package tana.daithanh.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tana.daithanh.lichvannien.R;
import tana.daithanh.mode.QuestionHop;

/**
 * Created by Administrator on 21/03/2018.
 */

public class HopAdapter extends ArrayAdapter<Object> {

    ArrayList<Object> m_ListView;

    protected LayoutInflater m_Inflater;
    int m_SourceOfView;




    public HopAdapter(Context context, int resource, ArrayList<Object> objects) {
        super(context, resource, objects);
        // TODO Auto-generated constructor stub
        this.m_ListView=objects;
        this.m_Inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    // Kết thúc khởi tạo

    public View getLayout(Object obj, View v, int position) {

        ViewHolder viewHolder;
        if (v == null) {
            viewHolder = new ViewHolder();
            v = (View) m_Inflater.inflate(R.layout.item_hop, null);


            viewHolder.tvTitle= (TextView) v.findViewById(R.id.tvItemQuestion);

            v.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) v.getTag();
        }
        QuestionHop feed = (QuestionHop) obj;



        viewHolder.tvTitle.setText(feed.getQuestion());




        return v;
    }



    /* Dùng hàm này trong TH set dữ liệu tĩnh */
    public ArrayList<Object> getData() {
        return new ArrayList<Object>();
    }

    public int getCount() {
        return m_ListView.size();
    }

    public Object getItem(int index) {
        return m_ListView.get(index);
    }

    /**
     * @return the listView
     */
    public ArrayList<Object> getListView() {
        return m_ListView;
    }

    /**
     * @param listView
     *            the listView to set
     */
    public void setListView(ArrayList<Object> listView) {
        this.m_ListView = listView;
    }



    public long getItemId(int arg0) {

        return 0;
    }

    public View getView(int position, View myview, ViewGroup arg2) {
        try {
            myview = getLayout(m_ListView.get(position), myview, position);
        } catch (Exception e) {
            // Log.w("Move", "Loi khi scroll listview - xem lai muc gan data");
        }
        return myview;
    }

    static class ViewHolder {
        TextView tvTitle;

    }

}
