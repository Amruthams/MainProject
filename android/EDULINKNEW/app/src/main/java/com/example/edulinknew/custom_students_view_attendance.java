package com.example.edulinknew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_students_view_attendance extends BaseAdapter {

    String[] sid,name,scount,perc;
    private Context context;
    public custom_students_view_attendance(Context appcontext,  String[] name, String[] scount, String[] perc){
        this.context = appcontext;


        this.name = name;
        this.scount = scount;
        this.perc = perc;

    }

    @Override
    public int getCount() { return name.length;}

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
            gridView=inflator.inflate(R.layout.custom_students_view_attendance,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView73);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView74);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView75);



        tv1.setText(name[i]);
        tv2.setText(scount[i]);
        tv3.setText(perc[i]);



        return gridView;
    }
}