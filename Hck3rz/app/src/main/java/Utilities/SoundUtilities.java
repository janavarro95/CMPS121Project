package Utilities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class SoundUtilities {


    /**
     * Play a sound.
     * @param activity The activity page that is currently active.
     * @param song "The uri to the song to play. Ex) R.raw.song_to_play
     * @return Returns whether or not the sound successfully plays.
     */
    public static boolean playSound(AppCompatActivity activity, int song){

        try{
            //This is how we play a sound.
            final MediaPlayer mp = MediaPlayer.create(activity, song);
            Log.v("Play the sound","");
            mp.start();
            return true;
        }
        catch (Exception err){
            Log.v("Utilities.PlaySound","An error occured playing a sound: "+err.toString());
            return false;
        }
    }

}
