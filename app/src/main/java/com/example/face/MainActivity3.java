package com.example.face;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {

    ProgressBar pb2;
    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_activity3);

        pb2 = (ProgressBar) findViewById(R.id.pb2);
        pb2.setProgressBackgroundTintList(ColorStateList.valueOf(Color.WHITE));
        pb2.setMax(20);

        Runnable r =new Runnable(){
            int i=1;
            @Override
            public void run() {
                while(i<21) {
                    try {
                        Thread.sleep(100);
                        pb2.setProgress(i);
                        i++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                String value = sharedpreferences.getString("passwordKey", "");
//                    Toast.makeText(Splash.this, value.toString(), Toast.LENGTH_SHORT).show();;
                if (value.length() > 0) {
                    Intent it = new Intent(MainActivity3.this, HomeActivity.class);
                    startActivity(it);
                    finish();
                } else {
                    Intent it = new Intent(MainActivity3.this, com.example.face.SetPin.class);
                    startActivity(it);
                    finish();
                }
            }
        };

        new Thread(r).start();
    }

}