package com.example.joshua.finalproject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class pictures extends AppCompatActivity {
    public final static String USER_NAME ="com.example.student.login1";
    public final static String PASSWORD ="com.example.student.login2";
   // TextView textView = (TextView) findViewById(R.id.tect);
    ImageView imageView;
    private GestureDetectorCompat gestureDetectorCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_blank);
        Intent intent = getIntent();
        final String userId = intent.getStringExtra(USER_NAME);


        /*
        * [
  {
    "UserName": "Power",
    "Profile_Picture": " ",
    "ID": "Power",
    "Full_Name": "Jeyvan Joseph",
    "Likes": [
      "https://drive.google.com/open?id=0B7mDGPDitW96XzcyUE8xeWFFeUk",
      "https://drive.google.com/open?id=0B7mDGPDitW96NWdPZE1jYzMyMjg",
      "https://drive.google.com/open?id=0B7mDGPDitW96MlpTaUZzSldTMWM",
      "https://drive.google.com/open?id=0B7mDGPDitW96Nm42cFN5WXBVcXM",
      "https://drive.google.com/open?id=0B7mDGPDitW96dzRfU212TUxtblk",
      "https://drive.google.com/open?id=0B7mDGPDitW96VWc0cHpOV1ZLRWc"
    ]
  }
]
        *
        * */
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url = "https://8gaj4yt8ka.execute-api.us-west-2.amazonaws.com/final/getuser?ID="+userId;

        JsonObjectRequest get = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());
               Toast.makeText(pictures.this, "Response"+response, Toast.LENGTH_SHORT).show();
               // textView.setText(response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());


            }
        });
        queue.add(get);




      //  final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.activity_pictures);

    }






    public void back(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        public  boolean onFling(MotionEvent event1, MotionEvent event2,
                                float velocityx, float velocityY){
            if(event2.getX() > event1.getX()){
                Intent intent = new Intent(pictures.this, MainActivity.class);
                finish();
                startActivity(intent);
            }else
            if(event2.getX()<event1.getX()){


            }
            return true;
        }
    }

}
