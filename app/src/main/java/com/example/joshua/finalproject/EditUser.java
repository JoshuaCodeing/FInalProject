package com.example.joshua.finalproject;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EditUser extends AppCompatActivity {
    public final static String USER_NAME ="com.example.student.login1";
    public final static String PASSWORD ="com.example.student.login2";

    EditText userName;
    EditText fullName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Intent intent = getIntent();
        final String userId = intent.getStringExtra(USER_NAME);
        final RequestQueue queue = Volley.newRequestQueue(this);
        final String url="https://8gaj4yt8ka.execute-api.us-west-2.amazonaws.com/final/adduser";


        userName =(EditText)findViewById(R.id.NewUserName);
        userName.setText(userId);
        fullName = (EditText) findViewById(R.id.NewFullName);
        Button button = (Button) findViewById(R.id.NewUser);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random random = new Random();
                int rand = random.nextInt(1000)+1;
                Map<String,String> param = new HashMap<String, String>();
                param.put("UserName",userName.getText().toString());
                param.put("Full_Name",fullName.getText().toString());
                param.put("ID",userId);
                param.put("Likes"," ");
                param.put("Profile_Picture"," ");
                JsonObjectRequest post = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(param), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response",response.toString());
                        userName.setText("Required");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                        userName.setText("User Added");

                    }
                });
                queue.add(post);
                userName.setText("Done");
            }

        });

        Button button1 = (Button) findViewById(R.id.newUpdate);
        final String url1 = " https://8gaj4yt8ka.execute-api.us-west-2.amazonaws.com/final/updateget";
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> param1 = new HashMap<String, String>();
                param1.put("UserName",userName.getText().toString());
                param1.put("Full_Name",fullName.getText().toString());
                param1.put("ID",userId);
                param1.put("Profile_Picture"," ");
                JsonObjectRequest put = new JsonObjectRequest(Request.Method.PUT, url1, new JSONObject(param1), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("response",response.toString());
                        userName.setText("Required");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                        userName.setText("User updated");

                    }
                });
                queue.add(put);
            }
        });
    }


    public void Add(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void UPDATE(View View){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
