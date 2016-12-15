package com.example.joshua.finalproject;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class pictures extends AppCompatActivity {
    public final static String USER_NAME ="com.example.student.login1";
    public final static String PASSWORD ="com.example.student.login2";
   // TextView textView = (TextView) findViewById(R.id.tect);
    LinearLayout layout;
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
            layout = (LinearLayout) findViewById(R.id.layout);
        JsonObjectRequest get = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("response",response.toString());
                ArrayList<String> res = new ArrayList<String>();
                List<JSONArray> array = new ArrayList<>();
                String s="";
                try {
                   final JSONArray ITEMS = response.getJSONArray("Items");
                    final JSONArray LIKES = null;
                    for(int i = 0; i < ITEMS.length();i++){
                        try{
                            JSONArray likes = ITEMS.getJSONObject(i).getJSONArray("Likes");
                            for(int j =0; j<likes.length(); j++){
                                WebView web = new WebView(pictures.this);
                                web.getSettings().setJavaScriptEnabled(true);
                                web.loadUrl(likes.get(j).toString());
                                layout.addView(web,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                                //layout;
                                //break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", error.toString());

            }
        });
        queue.add(get);
    }
public JSONArray parseJson(JSONObject response) {
    List<JSONArray> res = new ArrayList<>();
    JSONArray LIKES = null;
    try {
        LIKES = (new JSONObject(String.valueOf(response))).getJSONArray("Likes");
        List<JSONArray> array = new ArrayList<>();
        array.add(LIKES);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return LIKES;
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
