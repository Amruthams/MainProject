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

public class custom_teacher_view_attendance_overall extends BaseAdapter  {

    String [] roll,name;

//    Button edit;

    private Context context;

    public custom_teacher_view_attendance_overall(Context appcontext, String[] roll, String[] name){
        this.context = appcontext;
        this.roll = roll;
        this.name =name ;




    }

    @Override
    public int getCount() {
        return roll.length;
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
            gridView=inflator.inflate(R.layout.custom_teacher_view_attendance_overall,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView73);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView74);




        tv1.setText(roll[i]);
        tv2.setText(name[i]);





        return gridView;
    }
}
