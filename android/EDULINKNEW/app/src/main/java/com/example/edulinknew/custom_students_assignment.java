package com.example.edulinknew;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.edulinknew.R;
import com.example.edulinknew.student_upload_medical_certi;
public class custom_students_assignment extends BaseAdapter {

    String[] subject,topic,sdate,aid;
    Button b1;

    private Context context;
    public custom_students_assignment(Context appcontext, String[] subject, String[] topic,String [] sdate,String [] aid){
        this.context = appcontext;
        this.subject = subject;
        this.topic = topic;
        this.sdate = sdate;
        this.aid = aid;
    }

    @Override
    public int getCount() {
        return subject.length;
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
            gridView=inflator.inflate(R.layout.custom_students_assignment,null);
        }
        else
        {
            gridView=(View)view;
        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView132);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView133);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView134);
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
                Intent in=new Intent(context,student_upload_assignment.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });



        tv1.setText(subject[i]);
        tv2.setText(topic[i]);
        tv3.setText(sdate[i]);

        return gridView;
    }
}
