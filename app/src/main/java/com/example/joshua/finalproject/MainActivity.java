package com.example.joshua.finalproject;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public final static String USER_NAME ="com.example.student.login1";
    public final static String PASSWORD ="com.example.student.login2";
    private GestureDetectorCompat gestureDetectorCompat;
    EditText user;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetectorCompat = new GestureDetectorCompat(this, new LearnGesture());
    }
    // Swipe to intent
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{
        public  boolean onFling(MotionEvent event1, MotionEvent event2,
                                float velocityx, float velocityY){
            if(event2.getX() > event1.getX()){
                Intent intent1 = new Intent(MainActivity.this,AllPhoto.class);
                finish();
                startActivity(intent1);
            }else
                if(event2.getX()<event1.getX()){
                    Intent intent = new Intent(MainActivity.this, pictures.class);
                    finish();
                    startActivity(intent);
                }
            return true;
        }
    }
    // Button to intent
    public void login(View view){
        Intent intent = new Intent(this,pictures.class);
        user = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);

        String userName = user.getText().toString();
        String passwordPass = password.getText().toString();

        intent.putExtra(USER_NAME, userName);
        intent.putExtra(PASSWORD, passwordPass);
        startActivity(intent);
    }
    public void EDIT(View view){
        Intent intent = new Intent(this,EditUser.class);
        user = (EditText)findViewById(R.id.userName);
        password = (EditText)findViewById(R.id.password);

        String userName = user.getText().toString();
        String passwordPass = password.getText().toString();

        intent.putExtra(USER_NAME, userName);
        intent.putExtra(PASSWORD, passwordPass);
        startActivity(intent);
    }

}
