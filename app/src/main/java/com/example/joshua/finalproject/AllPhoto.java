package com.example.joshua.finalproject;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class AllPhoto extends AppCompatActivity {
    private GestureDetectorCompat gestureDetectorCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_photo);
        gestureDetectorCompat = new GestureDetectorCompat(this, new AllPhoto.LearnGesture());
    }
    public boolean onTouchEvent(MotionEvent event){
        this.gestureDetectorCompat.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    class LearnGesture extends GestureDetector.SimpleOnGestureListener{

        public  boolean onFling(MotionEvent event1, MotionEvent event2,
                                float velocityx, float velocityY){
            if(event2.getX() > event1.getX()){


            }else
            if(event2.getX()<event1.getX()){
                Intent intent = new Intent(AllPhoto.this, MainActivity.class);
                finish();
                startActivity(intent);

            }
            return true;
        }
    }
}
