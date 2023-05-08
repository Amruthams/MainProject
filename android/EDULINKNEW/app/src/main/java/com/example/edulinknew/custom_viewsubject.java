package com.example.edulinknew;

import android.app.backup.BackupAgent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class custom_viewsubject extends BaseAdapter {

    String[] sid,pid,subname,sem,progrm;
    private Context context;
    public  custom_viewsubject(Context appcontext, String[] sid, String[] pid, String[] subname, String[] sem, String[] progrm){
        this.context = appcontext;
        this.sid = sid;
        this.pid = pid;
        this.subname = subname;
        this.sem = sem;
        this.progrm = progrm;

    }

    @Override
    public int getCount() {
        return subname.length;
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
            gridView=inflator.inflate(R.layout.custom_viewsubject,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView25);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView26);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView27);



        tv1.setText(progrm[i]);
        tv2.setText(subname[i]);
        tv3.setText(sem[i]);
        Button b1=(Button) gridView.findViewById(R.id.button28) ;
        b1.setTag(sid[i]);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("subid",v.getTag().toString());
                ed.commit();
                Intent in=new Intent(context,student_view_studymaterial.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });



        return gridView;
    }
}
