package com.example.edulinknew;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class custom_teacher_view_attendance extends BaseAdapter  {

    String [] name,icon,photo,fname;
    String[] attendance;

//    Button edit;

    private Context context;

    public custom_teacher_view_attendance(Context appcontext, String[] name,String[]  fname,String[] photo,String[] attendance){
        this.context = appcontext;
        this.name =name ;
        this.fname=fname;
        this.icon =photo ;
        this.attendance = attendance ;




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
            gridView=inflator.inflate(R.layout.custom_teacher_view_attendance,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView73);
        ImageView img=(ImageView)gridView.findViewById(R.id.imageView15);
        ImageView img2=(ImageView)gridView.findViewById(R.id.imageView16);

        if(attendance[i].equalsIgnoreCase("present"))
        {
            img.setBackgroundResource(R.drawable.present);
        }
        else{
            img.setBackgroundResource(R.drawable.absent);
        }


        tv1.setText(name[i]);
        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ip","");

        String url="http://" + ip + ":8000" +icon[i];
//        Toast.makeText(context, ""+url, Toast.LENGTH_SHORT).show();


        Picasso.with(context).load(url). into(img2);





        return gridView;
    }
}
