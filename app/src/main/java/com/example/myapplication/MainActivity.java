package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.GridLayoutAnimationController;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button[][] btnArray = new Button[7][7];
    Button btn;
    LinearLayout llyt;
    int btnnum=0;
    GridLayout glyt;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        glyt = new GridLayout(this);
        GridLayout.LayoutParams glytParams = new GridLayout.LayoutParams();
        glyt.setColumnCount(7);
        glyt.setRowCount(7);
        glytParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
        glytParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
        glytParams.setGravity(Gravity.CENTER);
        setContentView(glyt);

        HandlerThread handlerThread = new HandlerThread("other");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
        for (int i = 0; i < 40; i++) {
                btn = new Button(getApplicationContext());

                glyt.addView(btn);

            }

            }
        });
        }


    @Override
    public void onClick(View view) {

    }
}


