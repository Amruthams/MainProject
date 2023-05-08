package com.example.edulinknew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_teacher_view_substitution extends BaseAdapter  {

    String [] pname,semester,date,hour,name;

    Button edit;

    private Context context;
    public custom_teacher_view_substitution(Context appcontext, String[] pname, String[] semester, String[] date,
                                            String[] hour, String[] name){
        this.context = appcontext;
        this.pname = pname;
        this.semester =semester ;
        this.date = date;
        this.hour = hour;
        this.name = name;




    }

    @Override
    public int getCount() {
        return pname.length;
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
            gridView=inflator.inflate(R.layout.custom_teacher_view_substitution,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView85);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView86);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView87);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView88);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView89);






        tv1.setText(pname[i]);
        tv2.setText(semester[i]);
        tv3.setText(date[i]);
        tv4.setText(hour[i]);
        tv5.setText(name[i]);





        return gridView;
    }
}
