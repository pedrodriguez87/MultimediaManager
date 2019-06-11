package com.example.multimediamanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MediaPlayer.OnPreparedListener {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnPlay = findViewById(R.id.Activity_main__btn__play);
        btnPlay.setOnClickListener(this);
        final Button btnStop = findViewById(R.id.Activity_main__btn__stop);
        btnStop.setOnClickListener(this);
        final Button btnSound = findViewById(R.id.Activity_main__btn__playsound);
        btnSound.setOnClickListener(this);
        final Button btnPlayInService = findViewById(R.id.Activity_main__btn__playinservice);
        btnPlayInService.setOnClickListener(this);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //Option 1:

        //mediaPlayer = MediaPlayer.create(this, R.raw.bensoundbrazilsamba);

        // option 2:
        AssetFileDescriptor ins = getResources().openRawResourceFd(R.raw.bensoundbrazilsamba);
        try {
            mediaPlayer.setDataSource(ins.getFileDescriptor());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnPreparedListener(this);
        //MediaPlayer player = MediaPlayer.create(this, R.raw.bensoundcountryboy);
        //player.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Activity_main__btn__play:
                mediaPlayer.prepareAsync();
                 break;
            case R.id.Activity_main__btn__stop:
                break;
            case R.id.Activity_main__btn__playsound:
                break;
            case R.id.Activity_main__btn__playinservice:
                break;
            default:
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }

}
