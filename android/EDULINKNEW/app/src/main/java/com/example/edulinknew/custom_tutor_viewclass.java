package com.example.edulinknew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_tutor_viewclass extends BaseAdapter {

    String[] sid,year,post,dept;
    private Context context;
    public custom_tutor_viewclass(Context appcontext, String[] sid, String[] year, String[] post, String[] dept){
        this.context = appcontext;
        this.sid = sid;

        this.year = year;
        this.post = post;
        this.dept = dept;

    }

    @Override
    public int getCount() { return sid.length;}

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
            gridView=inflator.inflate(R.layout.custom_tutor_viewclass,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView25);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView26);
//        TextView tv3=(TextView)gridView.findViewById(R.id.textView27);



        tv1.setText(dept[i]);
        tv2.setText(year[i]);
//        tv3.setText(sem[i]);



        return gridView;
    }
}
