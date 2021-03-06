package com.shenkar.nik.bbgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class soundActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    SeekBar seekbar;
    AudioManager audioManager;
    TextView textview;
    ImageView imageView;
    ImageView imageView2;
    int length;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        imageView = findViewById(R.id.mute);
        imageView2 = findViewById(R.id.unMute);
        textview = findViewById(R.id.textView1);
        seekbar = findViewById(R.id.seekBar);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        assert audioManager != null;
        seekbar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

        imageView.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        imageView2.setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
        //imageView.setVisibility(View.INVISIBLE);

        int volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        seekbar.setProgress(volume);
        if(volume==0){
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
        }
        else {
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
        }



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                textview.setText("Volume : " + i);
                if(i==0){
                    imageView.setVisibility(View.VISIBLE);
                    imageView2.setVisibility(View.INVISIBLE);
                }
                else if(i>0){
                    imageView.setVisibility(View.INVISIBLE);
                    imageView2.setVisibility(View.VISIBLE);
                }

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer = MediaPlayer.create(this,R.raw.song);
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();

    }

    @Override
    protected void onDestroy() {
        //other codes
        super.onDestroy();
        mMediaPlayer.stop();
    }
    protected void onResume() {
        super.onResume();
        mMediaPlayer.seekTo(length);
        mMediaPlayer.start();

    }
    protected void onPause() {
        super.onPause();
        mMediaPlayer.pause();
        length = mMediaPlayer.getCurrentPosition();
    }
}
