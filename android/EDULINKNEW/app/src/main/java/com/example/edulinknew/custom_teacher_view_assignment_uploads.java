package com.example.edulinknew;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class custom_teacher_view_assignment_uploads extends BaseAdapter {

    String[] fname,lname,date,file;
    Button b1;

    private Context context;
    public custom_teacher_view_assignment_uploads(Context appcontext, String[] fname,String [] lname, String [] date,String[] file){
        this.context = appcontext;
        this.fname = fname;
        this.lname = lname;
        this.date = date;
        this.file = file;
    }

    @Override
    public int getCount() {
        return file.length;
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
            gridView=inflator.inflate(R.layout.custom_teacher_view_assignment_uploads,null);
        }
        else
        {
            gridView=(View)view;
        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView133);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView138);

        Button b1=(Button) gridView.findViewById(R.id.button32);


        b1.setTag(file[i]);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000"+v.getTag().toString();
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

            }
        });



        tv1.setText(fname[i]);
        tv2.setText(date[i]);


        return gridView;
    }
}
