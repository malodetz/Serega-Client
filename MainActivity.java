package com.example.temporary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    PostIdeasTask mt;
    GetAllIdeasTask gt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        gt = new GetAllIdeasTask(this);
        gt.execute();
        mt = new PostIdeasTask(this, new Idea("Some new Idea", "The newest idea", "It is very long and boring description of an idea", "hahaha.png"));
        mt.execute();
        gt = new GetAllIdeasTask(this);
        gt.execute();
    }

}
