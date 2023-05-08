package com.example.edulinknew;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class view_timetable extends BaseAdapter {

    String [] day,hour,SUBJECT_ID,name,sem;
    private Context context;

    public view_timetable(Context appcontext, String[] day, String[] hour, String[] SUBJECT_ID, String[] name, String[] sem){
        this.context = appcontext;
        this.day= day;
        this.hour= hour;
        this.SUBJECT_ID= SUBJECT_ID;
        this.name= name;
        this.sem= sem;



    }


    @Override
    public int getCount() {
        return day.length;
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
            gridView=inflator.inflate(R.layout.view_timetable,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView53);
//        TextView tv2=(TextView)gridView.findViewById(R.id.textView51);

        tv1.setText(name[i]);





        return gridView;
    }
}


