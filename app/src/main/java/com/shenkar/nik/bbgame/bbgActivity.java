package com.shenkar.nik.bbgame;

import android.app.Activity;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

public class bbgActivity extends Activity {

    public static BreakoutEngine breakoutEngine;
    MediaPlayer mMediaPlayer;
    int length;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_bbg);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this,R.raw.song1);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

        //detect resolution of device display and respond to it
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        //initialize gameView and set it as a view
        breakoutEngine = new BreakoutEngine(this,size.x,size.y);
        setContentView(breakoutEngine);
    }
    @Override
    protected void onDestroy() {
        //other codes
        super.onDestroy();
        mMediaPlayer.stop();
    }
    protected void onResume() {
        super.onResume();
        breakoutEngine.resume();
        mMediaPlayer.seekTo(length);
        mMediaPlayer.start();

    }
    protected void onPause() {
        super.onPause();
        breakoutEngine.pause();
        mMediaPlayer.pause();
        length = mMediaPlayer.getCurrentPosition();

    }

}
