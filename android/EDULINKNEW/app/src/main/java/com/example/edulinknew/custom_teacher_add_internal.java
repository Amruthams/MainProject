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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_teacher_add_internal extends BaseAdapter  {

    String [] name,id;


//    Button edit;

    private Context context;

    public custom_teacher_add_internal(Context appcontext, String[] name, String[]  id){
        this.context = appcontext;
        this.name =name ;
        this.id=id;



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
            gridView=inflator.inflate(R.layout.custom_teacher_add_internal,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView73);
        EditText et=(EditText)gridView.findViewById(R.id.editTextTextPersonName25) ;
        Button btn=(Button)gridView.findViewById(R.id.button31);



        btn.setTag(i);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String mark=et.getText().toString();

                int ik=Integer.parseInt(v.getTag().toString());


                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                String hu = sh.getString("ip", "");
                String url = "http://" + hu + ":8000/myapp/teacher_add_internal/";

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(context, response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {


                                        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
//                                        Intent ii=new Intent(context,teacher_add_internal.class);

//                                        ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                                        context.startActivity(ii);
                                        et.setText("");

                                    }


                                    // }
                                    else {
                                        Toast.makeText(context, "Already Exists", Toast.LENGTH_LONG).show();
                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();


                        params.put("sid",id[ik]);
                        params.put("sub_id",sh.getString("subject",""));
                        params.put("mark",mark);

//                        Toast.makeText(context, ""+sh.getString("subid",""), Toast.LENGTH_SHORT).show();

//                       params.put("mark",marks.getSelectedItem().toString());
//                        params.put("subid", id[ssub.getSelectedItemPosition()]);
//                      params.put("mark",mark.getString("sid",""));


                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS = 100000;

                postRequest.setRetryPolicy(new

                        DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);

            }
        });


        tv1.setText(name[i]);

        return gridView;
    }
}
