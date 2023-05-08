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

public class custom_teacher_viewstudents extends BaseAdapter  {

    String [] id,ktu_id,fname,lname,dob,house,place,post,pin,district,email,phno,photo,BATCH_id,DEPARTMENT_id,semester,LOGIN_id;

//    Button edit;

    private Context context;

    public custom_teacher_viewstudents(Context appcontext, String[] id, String[] ktu_id, String[] fname, String[] lname, String[] dob, String[] house, String[] place, String[] post, String[] pin, String[] district, String[] email, String[] phno, String[] photo, String[] semester){
        this.context = appcontext;
        this.id = id;
        this.ktu_id =ktu_id ;
        this.fname = fname;
        this.lname = lname;
//        this.dob = dob;
//        this.house = house;
//        this.place = place;
//        this.post = post;
//        this.pin = pin;
//        this.district = district;
        this.email = email;
        this.phno = phno;
        this.photo = photo;
        this.semester = semester;



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
            gridView=inflator.inflate(R.layout.custom_teacher_viewstudents,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView25);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView26);
//        TextView tv3=(TextView)gridView.findViewById(R.id.textView27);
//        TextView tv4=(TextView)gridView.findViewById(R.id.textView20);
//        TextView tv5=(TextView)gridView.findViewById(R.id.textView28);
//        TextView tv6=(TextView)gridView.findViewById(R.id.textView30);
//        TextView tv7=(TextView)gridView.findViewById(R.id.textView32);
//        TextView tv8=(TextView)gridView.findViewById(R.id.textView34);
        TextView tv9=(TextView)gridView.findViewById(R.id.textView36);
        TextView tv10=(TextView)gridView.findViewById(R.id.textView38);
        ImageView img=(ImageView)gridView.findViewById(R.id.imageView10);
//        Button edit=(Button)gridView.findViewById(R.id.button10);

//        edit.setTag(id[i]);
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(context);
//                SharedPreferences.Editor ed=sh.edit();
//                ed.putString("sid",v.getTag().toString());
//                ed.commit();
//                Intent in=new Intent(context,tutor_edit_students.class);
//                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(in);
//            }
//        });








        tv1.setText(ktu_id[i]);
        tv2.setText(fname[i] +" "+ lname[i]);
//        tv3.setText(lname[i]);
//        tv3.setText(dob[i]);
//        tv4.setText(house[i]);
//        tv5.setText(place[i]);
//        tv6.setText(post[i]);
//        tv7.setText(pin[i]);
//        tv8.setText(district[i]);
        tv9.setText(email[i]);
        tv10.setText(phno[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000" +photo[i];
//        Toast.makeText(context, ""+url, Toast.LENGTH_SHORT).show();


        Picasso.with(context).load(url). into(img);



        return gridView;
    }
}
