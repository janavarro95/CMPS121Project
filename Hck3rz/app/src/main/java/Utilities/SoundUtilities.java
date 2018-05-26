package Utilities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import User.Game;


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
            mp.setVolume(Game.options.soundOptions.getVolume(),Game.options.soundOptions.getVolume());
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();

                };
            });
            return true;
        }
        catch (Exception err){
            Log.v("Utilities.PlaySound","An error occurred playing a sound: "+err.toString());
            return false;
        }
    }

    /**
     *
     * @param activity The context of the app.
     * @param song The reference to the sound to play in R.raw
     * @param volume The volume to play the sound.
     * @return
     */
    public static boolean playSound(AppCompatActivity activity, int song, float volume){

        try{
            //This is how we play a sound.
            final MediaPlayer mp = MediaPlayer.create(activity, song);
            Log.v("Play the sound","");
            mp.setVolume(volume,volume);
            mp.start();

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    mp.release();

                };
            });
            return true;
        }
        catch (Exception err){
            Log.v("Utilities.PlaySound","An error occurred playing a sound: "+err.toString());
            return false;
        }
    }
}
