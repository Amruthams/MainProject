package com.example.edulinknew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class hod_custom_viewsubject_allocation extends BaseAdapter {

    String[] sid,facultyid,subject,fname,lname,sem;
    private Context context;
    public  hod_custom_viewsubject_allocation(Context appcontext,String[] sid, String[] facultyid, String[] subject, String[] fname, String[] lname,String[]sem){
        this.context = appcontext;
        this.sid = sid;
        this.facultyid = facultyid;
        this.subject = subject;
        this.fname = fname;
        this.lname = lname;
        this.sem = sem;

    }

    @Override
    public int getCount() {
        return sid.length;
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
            gridView=inflator.inflate(R.layout.hod_custom_viewsubject_allocation,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView25);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView26);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView27);



        tv1.setText(fname[i]+" "+lname[i]);
        tv2.setText(subject[i]);
        tv3.setText(sem[i]);



        return gridView;
    }
}
