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

public class custom_teacher_view_subjects extends BaseAdapter {

    String [] id,FACULTY_id,SUBJECT_id,name,semester;
    Button b1;


    private Context context;
    public custom_teacher_view_subjects(Context appcontext, String[] id, String[] FACULTY_id, String[] SUBJECT_id, String[] name, String[] semester){
        this.context = appcontext;
        this.id = id;
        this.FACULTY_id = FACULTY_id;
        this.SUBJECT_id = SUBJECT_id;
        this.name = name;
        this.semester = semester;

    }

    @Override
    public int getCount() {
        return name.length;
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
            gridView=inflator.inflate(R.layout.custom_teacher_view_subjects,null);

        }
        else
        {
            gridView=(View)view;

        }
//        TextView tv1=(TextView)gridView.findViewById(R.id.textView25);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView65);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView63);
        Button b1=(Button) gridView.findViewById(R.id.button26) ;
        b1.setTag(SUBJECT_id[i]);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("subid",v.getTag().toString());
                ed.commit();
                Intent in=new Intent(context,teacher_view_studymaterial.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });




//        tv1.setText(progrm[i]);
        tv2.setText(name[i]);
        tv3.setText(semester[i]);



        return gridView;
    }
}
