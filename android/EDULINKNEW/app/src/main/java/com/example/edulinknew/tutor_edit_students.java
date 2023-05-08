package com.example.edulinknew;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class tutor_edit_students extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    EditText ktu,fname,lname,house,place,post,pin,district,email,phno,dob;
    ImageView img;
    String path, atype, attach="", attatch1;
    byte[] byteArray = null;
    Button btn;
    final Calendar myCalendar= Calendar.getInstance();
    String sems[]={"1","2","3","4","5","6"};
    Spinner s1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_edit_students);

        ktu=findViewById(R.id.editTextTextPersonName8);
        fname=findViewById(R.id.editTextTextPersonName9);
        lname=findViewById(R.id.editTextTextPersonName10);
        dob=findViewById(R.id.editTextTextPersonName11);
        house=findViewById(R.id.editTextTextPersonName12);
        place=findViewById(R.id.editTextTextPersonName13);
        post=findViewById(R.id.editTextTextPersonName14);
        pin=findViewById(R.id.editTextTextPersonName15);
        district=findViewById(R.id.editTextTextPersonName16);
        email=findViewById(R.id.editTextTextPersonName17);
        phno=findViewById(R.id.editTextTextPersonName18);
        s1=findViewById(R.id.spinner5);
        img=findViewById(R.id.imageView4);

        s1.setOnItemSelectedListener(this);

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateLabel();
            }
        };

//        ############################################## view ###########################################################

        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String ip= sh.getString("ip", "");
        String url = "http://" + ip + ":8000/myapp/tutor_update_student/";
        String lid = sh.getString("lid", "");

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {


                                ktu.setText(jsonObj.getString("ktuid"));
                                fname.setText(jsonObj.getString("fname"));
                                lname.setText(jsonObj.getString("lname"));
                                dob.setText(jsonObj.getString("dob"));
                                house.setText(jsonObj.getString("house"));
                                place.setText(jsonObj.getString("place"));
                                post.setText(jsonObj.getString("post"));
                                pin.setText(jsonObj.getString("pin"));
                                district.setText(jsonObj.getString("district"));
                                email.setText(jsonObj.getString("email"));
                                phno.setText(jsonObj.getString("phno"));




                                String image = jsonObj.getString("photo");
                                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                String ip = sh.getString("ip", "");
                                String url = "http://" + ip + ":8000" + image;
                                Picasso.with(getApplicationContext()).load(url).into(img);
//                                Picasso.with(getApplicationContext()).load(url).into(image);//circle

                            } else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {

            //                value Passing android to python
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("lid", lid);//passing to python
                params.put("sid",sh.getString("sid",""));

                return params;
            }
        };
        int MY_SOCKET_TIMEOUT_MS = 100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);








        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               DatePickerDialog dod= new DatePickerDialog(tutor_edit_students.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH));
                Calendar c = Calendar.getInstance();
                c.set(1996, 0, 1);
               dod.getDatePicker().setMinDate(c.getTimeInMillis());
                Calendar c1 = Calendar.getInstance();
                c1.set(2005, 0, 1);
                dod.getDatePicker().setMaxDate(c1.getTimeInMillis());
                dod.show();
            }
        });

        ArrayAdapter<String> ad=new ArrayAdapter<String>(tutor_edit_students.this,android.R.layout.simple_list_item_1,sems);
        s1.setAdapter(ad);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfilechooser(1);

            }
        });

        btn=findViewById(R.id.button8);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        final String ktu_d = ktu.getText().toString();
        final String finame = fname.getText().toString();
        final String laname = lname.getText().toString();
        final String houses = house.getText().toString();
        final String places = place.getText().toString();
        final String posts = post.getText().toString();
        final String pinn = pin.getText().toString();
        final String dis = district.getText().toString();
        final String mail = email.getText().toString();
        final String Phno = phno.getText().toString();
        final String Dob = dob.getText().toString();


        if (ktu_d.length()==0){
            ktu.setError("Missing");
            ktu.requestFocus();

        } else if (finame.length()==0) {
            fname.setError("Missing");
            fname.requestFocus();
        } else if (laname.length()==0) {
            lname.setError("Missing");
            lname.requestFocus();
        } else if (!finame.matches("^[a-z,A-Z]*$")) {
            fname.setError("Only Characters Allowed");
            fname.requestFocus();
        }else if (houses.length()==0) {
            house.setError("Missing");
            house.requestFocus();
        } else if (places.length()==0) {
            place.setError("Missing");
            place.requestFocus();
        } else if (posts.length()==0) {
            post.setError("Missing");
            post.requestFocus();
        } else if (pinn.length()==0) {
            pin.setError("Missing");
            pin.requestFocus();
        } else if (pinn.length() != 6) {
            pin.setError("Invalid mobile");
            pin.requestFocus();
        }else if (dis.length()==0) {
            district.setError("Missing");
            district.requestFocus();
        } else if (mail.length()==0) {
            email.setError("Missing");
            email.requestFocus();
        }else if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("Invalid email");
            email.requestFocus();
        } else if (Phno.length()==0) {
            phno.setError("Missing");
            phno.requestFocus();
        }
        else if (Phno.length() != 10) {
            phno.setError("Invalid mobile");
            phno.requestFocus();
        }
        else if (Dob.length()==0) {
            dob.setError("Missing");
            dob.requestFocus();
        }
        else{



        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final String maclis=sh.getString("mac_list","");
        String uid=sh.getString("uid","");
        String hu = sh.getString("ip", "");
        String url = "http://" + hu + ":8000/myapp/tutor_update_student_post/";



        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                Intent i=new Intent(getApplicationContext(),tutor_view_students.class);
                                startActivity(i);
                                Toast.makeText(getApplicationContext(), "Update Successful", Toast.LENGTH_SHORT).show();


                            }
                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("lid",sh.getString("lid",""));
                params.put("ktu_id",ktu_d);
                params.put("fname",finame);
                params.put("lname",laname);
                params.put("house",houses);
                params.put("place",places);
                params.put("post",posts);
                params.put("pin",pinn);
                params.put("district",dis);
                params.put("phone",Phno);
                params.put("email",mail);
                params.put("dob",Dob);
                params.put("photo",attach);
                params.put("semester",s1.getSelectedItem().toString());
                params.put("sid",sh.getString("sid",""));



                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);





    }



}
    void showfilechooser(int string) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //getting all types of files

        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), string);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(getApplicationContext(), "Please install a File Manager.", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                ////
                Uri uri = data.getData();

                try {
                    path = FileUtils.getPath(this, uri);

                    File fil = new File(path);
                    float fln = (float) (fil.length() / 1024);
                    atype = path.substring(path.lastIndexOf(".") + 1);


//                    fname = path.substring(path.lastIndexOf("/") + 1);
//                    ed15.setText(fname);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }

                try {

                    File imgFile = new File(path);

                    if (imgFile.exists()) {

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                        img.setImageBitmap(myBitmap);

                    }


                    File file = new File(path);
                    byte[] b = new byte[8192];
                    Log.d("bytes read", "bytes read");

                    InputStream inputStream = new FileInputStream(file);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();

                    int bytesRead = 0;

                    while ((bytesRead = inputStream.read(b)) != -1) {
                        bos.write(b, 0, bytesRead);
                    }
                    byteArray = bos.toByteArray();

                    String str = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                    attach = str;


                } catch (Exception e) {
                    Toast.makeText(this, "String :" + e.getMessage().toString(), Toast.LENGTH_LONG).show();
                }

                ///

            }
        }

    }


    private void updateLabel(){
        String myFormat="dd-MM-yyyy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        dob.setText(dateFormat.format(myCalendar.getTime()));
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}