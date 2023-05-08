package com.example.edulinknew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class custom_students_view_internal extends BaseAdapter {

    String[] subject,mark;
    private Context context;
    public custom_students_view_internal(Context appcontext, String[] subject, String[] mark){
        this.context = appcontext;


        this.subject = subject;
        this.mark = mark;


    }

    @Override
    public int getCount() { return subject.length;}

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
            gridView=inflator.inflate(R.layout.custom_students_view_internal,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView73);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView74);




        tv1.setText(subject[i]);
        tv2.setText(mark[i]);




        return gridView;
    }
}
