package com.example.temporary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    PostIdeasTask mt;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        mt = new PostIdeasTask(this, new Idea("Idea №4", "Now, it is the newest idea", "It is very long and boring description of an idea", "lololol.png"));
        mt.execute();
    }

}
