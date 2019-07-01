package com.example.multimediamanager;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;

import java.io.IOException;

public class MyMultimediaService extends Service implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {

    private MediaPlayer mediaPlayerInService;

    public MyMultimediaService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mediaPlayerInService == null) {
            createAndConfigMediaPlayer();
        }
        if (mediaPlayerInService.isPlaying()) {
            mediaPlayerInService.stop();
        }
        mediaPlayerInService.prepareAsync();

        return Service.START_STICKY;
    }

    private void createAndConfigMediaPlayer() {
        mediaPlayerInService = new MediaPlayer();
        mediaPlayerInService.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayerInService.setOnPreparedListener(this);
        mediaPlayerInService.setOnCompletionListener(this);
        final Uri mediaPath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bensoundcountryboy);
        try {
            mediaPlayerInService.setDataSource(getApplicationContext(), mediaPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mp.release();
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

}