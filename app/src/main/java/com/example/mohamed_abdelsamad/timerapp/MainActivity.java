package com.example.mohamed_abdelsamad.timerapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    boolean running = true;
    MediaPlayer md;
    ImageView imageView;
    Button btn;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        textView = (TextView) findViewById(R.id.text);
        btn = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.image);

        seekBar.setProgress(30);
        seekBar.setMax(3600);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                update(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void start(View view) {
        if (running) {

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {

                    update((int) l / 1000);
                    imageView.animate().rotationXBy(360f);

                }

                @Override
                public void onFinish() {
                    reset();

                }
            }.start();
            running = false;
            btn.setText("Stop");
            seekBar.setEnabled(false);
            md = MediaPlayer.create(MainActivity.this, R.raw.song);
            md.start();
        } else {
            reset();

        }
    }

    public void update(int time) {
        int minuite = (int) time / 60;
        int secondes = time - minuite * 60;
        String secon = Integer.toString(secondes);
        if (secondes <= 9) {
            secon = "0" + secon.toString();
        }

        textView.setText(Integer.toString(minuite) + ":" + secon);



    }

    public void reset() {
        seekBar.setProgress(30);
        textView.setText("0:30");
        md.stop();
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        btn.setText("Start");
        running = true;
    }
}
