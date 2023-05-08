package com.example.edulinknew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class custom_notification extends BaseAdapter  {

    String [] id,date,notifi;

    private Context context;
    public custom_notification(Context appcontext,String []id, String[] date, String[] notifi){
        this.context = appcontext;
        this.id = id;
        this.date = date;
        this.notifi = notifi;





    }

    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.custom_notification,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView139);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView140);

        tv1.setText(date[i]);
        tv2.setText(notifi[i]);

        return gridView;
    }
}
