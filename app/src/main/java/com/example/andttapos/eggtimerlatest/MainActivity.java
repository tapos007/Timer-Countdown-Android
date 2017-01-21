package com.example.andttapos.eggtimerlatest;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar timerSeekbar;
    private TextView timerTextView;
    private boolean counterIsActive = false;
    private Button controllerButton;
    private CountDownTimer countDownTimer;

    public void resetTimer(){
        timerTextView.setText("00:30");
        timerSeekbar.setProgress(30);
        countDownTimer.cancel();
        controllerButton.setText("Go!");
        timerSeekbar.setEnabled(true);
        counterIsActive = false;
    }
    public void controlTimer(View view) {

        if (counterIsActive == false) {
            counterIsActive = true;
            timerSeekbar.setEnabled(false);
            controllerButton.setText("Stop");
           countDownTimer =  new CountDownTimer(timerSeekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    resetTimer();
                    timerTextView.setText(String.format("%02d:%02d", 0, 0));
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                    mediaPlayer.start();

                }
            }.start();
        }else {
            resetTimer();
        }

    }

    public void updateTimer(int SecondLeft) {
        int minute = SecondLeft / 60;
        int second = SecondLeft - minute * 60;
        timerTextView.setText(String.format("%02d:%02d", minute, second));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekbar = (SeekBar) findViewById(R.id.TimerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button) findViewById(R.id.controllerButton);
        timerSeekbar.setMax(600);
        timerSeekbar.setProgress(30);
        timerSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
