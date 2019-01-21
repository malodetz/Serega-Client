package com.example.temporary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    RegisterTask mt;
    DataBaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseHelper(this);
        textView = findViewById(R.id.textView);
        mt = new RegisterTask(this, new User("goodie@gmail.com", "qkvbqercntrcWIFJEQCTETHRTIGG4453269532(%(", "Erich Maria Remark"));
        mt.execute();
        Log.i("Token ", db.getToken());
    }

}
