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

public class custom_tutor_chat_students extends BaseAdapter  {

    String [] id,ktu_id,fname,lname,dob,house,place,post,pin,district,email,phno,photo,BATCH_id,DEPARTMENT_id,semester,LOGIN_id;

//    Button edit;

    private Context context;

    public custom_tutor_chat_students(Context appcontext, String[] id, String[] ktu_id, String[] fname, String[] lname, String[] dob, String[] house, String[] place, String[] post, String[] pin, String[] district, String[] email, String[] phno, String[] photo, String[] semester){
        this.context = appcontext;
        this.id = id;
        this.ktu_id =ktu_id ;
        this.fname = fname;
        this.lname = lname;

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
            gridView=inflator.inflate(R.layout.custom_tutor_chat_students,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView25);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView26);

        TextView tv9=(TextView)gridView.findViewById(R.id.textView36);
        TextView tv10=(TextView)gridView.findViewById(R.id.textView38);
        ImageView img=(ImageView)gridView.findViewById(R.id.imageView10);
        Button chat=(Button)gridView.findViewById(R.id.button33);
        chat.setTag(i);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h=Integer.parseInt(v.getTag().toString());
                SharedPreferences sg=PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor ed=sg.edit();
                ed.putString("snames",fname[h]+" "+lname[h]);
                ed.putString("toid",id[h]);
                ed.commit();

                Intent p=new Intent(context,Test.class);
                p.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(p);
            }
        });








        tv1.setText(ktu_id[i]);
        tv2.setText(fname[i] +" "+ lname[i]);

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
