package com.awesomeyin.universalcourseplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseVideoPlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_video_player);
    }
}
