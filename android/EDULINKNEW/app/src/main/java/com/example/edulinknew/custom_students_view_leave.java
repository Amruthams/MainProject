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

public class custom_students_view_leave extends BaseAdapter  {

    String [] id,from_date,to_date,reason,status,date;

    Button b1;


    private Context context;

    public custom_students_view_leave(Context appcontext,String[] id, String[] date, String[]  from_date, String[] to_date, String[] reason, String[] status){
        this.context = appcontext;
        this.id =id ;
        this.date =date ;
        this.from_date =from_date ;
        this.to_date=to_date;
        this.reason =reason ;
        this.status = status ;




    }

    @Override
    public int getCount() {
        return id.length;
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
            gridView=inflator.inflate(R.layout.custom_students_view_leave,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView121);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView115);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView116);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView118);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView120);
        Button b1=(Button) gridView.findViewById(R.id.button23);
        b1.setTag(id[i]);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("leaveid",v.getTag().toString());
                ed.commit();
                Intent in=new Intent(context,student_upload_medical_certi.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(in);
            }
        });




        b1.setVisibility(View.INVISIBLE);




        tv1.setText(date[i]);
        tv2.setText(from_date[i]);
        tv3.setText(to_date[i]);
        tv4.setText(reason[i]);
        tv5.setText(status[i]);

        if(status[i].equalsIgnoreCase("approved")){

            b1.setVisibility(View.VISIBLE);

        }
        else{
            b1.setVisibility(View.INVISIBLE);



        }




        return gridView;
    }
}
