package com.example.music_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{
    MediaPlayer medcont;
    AudioManager audioManager;

    public void player(View view)
    {
       medcont.start();
    }

    public void pauser(View view)
    {
        medcont.pause();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


         medcont = MediaPlayer.create(this,R.raw.donnie);
         SeekBar volumecont = findViewById(R.id.volumecont);
         volumecont.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
         volumecont.setProgress(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));

         volumecont.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
             @Override
             public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                 audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

             }

             @Override
             public void onStartTrackingTouch(SeekBar seekBar) {

             }

             @Override
             public void onStopTrackingTouch(SeekBar seekBar) {

             }
         });

        final SeekBar scrubcontrol = findViewById(R.id.scrubcontrol);
        scrubcontrol.setMax(medcont.getDuration());
        scrubcontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubcontrol.setProgress(medcont.getCurrentPosition());
            }
        },0,300);
    }
}
