package com.example.playmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.IOException;

//https://www.al-hamdoulillah.com/coran/mp3/files/mohammed-siddiq-minshawi/002.mp3
public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton);
        seekBar = findViewById(R.id.seekBarId);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://www.al-hamdoulillah.com/coran/mp3/files/mohammed-siddiq-minshawi/112.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                int duration = mp.getDuration();
                Toast.makeText(MainActivity.this,String.valueOf((duration/1000)/60),Toast.LENGTH_LONG).show();
            }
        });
        MediaPlayer.OnPreparedListener prepareListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mp) {
                seekBar.setMax(mediaPlayer.getDuration());

                playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mp.isPlaying()
                        ) {
                            mp.pause();
                            playButton.setText(R.string.play_text);
                        }

                     else

                        {
                            mp.start();
                            playButton.setText(R.string.pause_text);

                        }
                    }
                });

            }
        };
        mediaPlayer.setOnPreparedListener(prepareListener);

        mediaPlayer.prepareAsync();


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if(fromUser)
                {
                    mediaPlayer.seekTo(progress);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.awade);

    }

//    public void pauseMusic() {
//        if (mediaPlayer != null) {
//            mediaPlayer.pause();
//            playButton.setText(R.string.play_text);
//        }
//
//    }
//
//    public void playMusic() {
//        if (mediaPlayer != null) {
//            mediaPlayer.start();
//            playButton.setText(R.string.pause_text);
//        }
//
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.release();
        }
    }
}
