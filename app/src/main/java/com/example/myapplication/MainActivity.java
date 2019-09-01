package com.example.myapplication;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.service.autofill.FillCallback;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button[][] btn2Array = new Button[7][7];
    Button[] btn1Array = new Button[49];
    int[] Array = new int[49];


    Button btn;
    int btnnum = 0;
    LinearLayout llyt;
    LinearLayout llytv;
    GridLayout glyt;
    TextView testv;
    Button sbtn;
    Button rbtn;


    private TextView textv;
    private Timer timer;
        CountDownTimer Ctimer = new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                textv.setText("second remaining: "+l/1000);
            }

            @Override
            public void onFinish() {
                textv.setText("Fail");
                textv.setTextColor(Color.RED);
            }
        };
    private int cnum=1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        void startTimer(){
            Ctimer.start();
        }
        void stopTimer(){
            Ctimer.cancel();
            textv.setText("setTimer");
        }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        llyt = new LinearLayout(this);
        llyt.setOrientation(LinearLayout.VERTICAL);
        setContentView(llyt);


        textv = new TextView(getApplicationContext());
        textv.setText("setTimer");
        llyt.addView(textv,0);

        llytv = new LinearLayout(this);
        llytv.setOrientation(LinearLayout.HORIZONTAL);
        llyt.addView(llytv,1);

        sbtn = new Button(this);
        sbtn.setText("start");
        sbtn.setOnClickListener(this);
        rbtn = new Button(this);
        rbtn.setText("reset");
        rbtn.setOnClickListener(this);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, ViewGroup.LayoutParams.WRAP_CONTENT);
        llytv.addView(sbtn,0,params);
        llytv.addView(rbtn,1,params);


        glyt = new GridLayout(this);
        glyt.setRowCount(7);
        glyt.setColumnCount(7);
        glyt.setOrientation(GridLayout.VERTICAL);
        llyt.addView(glyt,2);







        for (int i = 0, c = 0, r = 0; i < 49; i++, c++) {
            if(c==glyt.getColumnCount()){
                c=0;
                r++;
            }
            btn = new Button(getApplicationContext());
            btn1Array[i]=btn;
            btn.setText(Integer.toString(btnnum + 1));
            final int finalR = r;
            final int finalC = c;
            btn1Array[i].setOnClickListener(this);


            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = 150;
            param.rightMargin = 5;
            param.topMargin = 5;
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);

            glyt.addView(btn, param);

            btnnum++;
        }



        for(int i=0;i<7;i++){
            for(int j=0;j<7;j++){
                btn1Array[i*7+j].setText(""+Array[i*7+j]);
            }
        }





    }



    public void shakenum(){
        int x,y,tmp;
        Random rd = new Random();

        for(int i=0;i<100;i++){
            x=rd.nextInt(49);
            y=rd.nextInt(49);
            if(x==y) continue;
            tmp=Array[x];
            Array[x]=Array[y];
            Array[y]=tmp;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        if(v == sbtn){
            initnum(1);
            shakenum();
            startTimer();
            for(int i=0;i<49;i++) {
                btn1Array[i].setText(""+Array[i]);
            }
        }else if(v==rbtn){
            initValue();
        }else{
            int x=Integer.parseInt(((Button)v).getText().toString());
            if(cnum==x){
                ((Button)v).setVisibility(View.INVISIBLE);
                cnum++;
            }
            if(cnum==50){
                Ctimer.cancel();
                textv.setText("success");
                textv.setTextColor(Color.BLUE);
            }
        }

    }

    private void initValue() {
        stopTimer();
        for(int i=0;i<49;i++){
            btn1Array[i].setText(""+0);
            btn1Array[i].setVisibility(View.VISIBLE);
        }
        textv.setTextColor(Color.BLACK);
        cnum=1;
    }

    private void initnum(int n) {
        for(int i=0;i<49;i++){
            Array[i]=i+n;
        }
    }
}