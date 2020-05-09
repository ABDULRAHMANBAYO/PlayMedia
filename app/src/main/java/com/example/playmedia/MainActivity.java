package com.example.playmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

//https://www.al-hamdoulillah.com/coran/mp3/files/mohammed-siddiq-minshawi/002.mp3
public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = findViewById(R.id.playButton);

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("https://www.al-hamdoulillah.com/coran/mp3/files/mohammed-siddiq-minshawi/002.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }

        MediaPlayer.OnPreparedListener prepareListener = new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mp) {

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
