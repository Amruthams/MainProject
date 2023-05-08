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
import android.widget.TextView;

public class custom_teacher_view_assignment extends BaseAdapter {

    String[] topic,sdate,aid;
    Button b1;

    private Context context;
    public custom_teacher_view_assignment(Context appcontext, String[] topic, String [] sdate,String [] aid){
        this.context = appcontext;

        this.topic = topic;
        this.sdate = sdate;
        this.aid = aid;

    }

    @Override
    public int getCount() {
        return topic.length;
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
            gridView=inflator.inflate(R.layout.custom_teacher_view_assignment,null);
        }
        else
        {
            gridView=(View)view;
        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView133);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView134);

        Button b1=(Button) gridView.findViewById(R.id.button32);


        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int ik=Integer.parseInt(v.getTag().toString());
                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("assid",aid[ik]);
                ed.commit();
                Intent in=new Intent(context,teacher_view_assignments_uploads.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });




        tv1.setText(topic[i]);
        tv2.setText(sdate[i]);

        return gridView;
    }
}
