package com.example.owner.voicememos;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.io.IOException;

import static com.example.owner.voicememos.MainActivity.MediaRecorderReady;

public class WebAppInterface {

    AppCompatActivity context;
    MediaPlayer player;

    public WebAppInterface(AppCompatActivity c){
        this.context=c;
        player=new MediaPlayer();
    }

    @JavascriptInterface
    //Records a new audio clip.
    public void record(){
        Log.v("Record","I love toast");
        MainActivity.setOutputFile();
        try {
            // recording starts
            MainActivity.mediaRecorder.prepare();
            MainActivity.mediaRecorder.start();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    //Stops recording a new audio clip.
    public void stop() throws IOException {
        Log.v("Stop","I hate toast");
        MainActivity.mediaRecorder.stop();
        MainActivity.save();
    }

    @JavascriptInterface
    //Plays the most recently recorded audio clip.
    public void play(){
        //do media player play stuff here.
        Log.v("Play","I butter toast");
        player = new MediaPlayer();
        try {
            player.setDataSource(context.getFilesDir()+"/"+"Audio Recording"+Integer.toString(MainActivity.saveInt)+".3gp");
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();// play the audio
    }

    @JavascriptInterface
    //Stops the playing audio clip.
    public void stoprec(){
        //do media player stop stuff here
        Log.v("StopRec","I Burn toast");

        if(player != null){
            player.stop(); // stop audio
            player.release(); // free up memory
            MediaRecorderReady();
        }

    }

    @JavascriptInterface
    //Goes back to my main activity
    public void exit(){
        Log.v("Exit","I don't have toast");
        this.context.finish();
    }


}
