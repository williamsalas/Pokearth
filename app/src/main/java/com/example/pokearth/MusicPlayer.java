package com.example.pokearth;

import android.content.Context;
import android.media.MediaPlayer;

public class MusicPlayer {

    public static MediaPlayer player;
    public static boolean isPlaying = false;

    public static void SoundPlayer(Context ctx, int raw_id){
        player = MediaPlayer.create(ctx, raw_id);
        player.setLooping(true); // Set looping
        isPlaying = true;
        player.start();
    }

    public static void StopSound(){
        player.stop();
        isPlaying = false;
    }
}
