package User;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import User.OptionsPackage.Options;

/**
 * Holds static information that would be persistent the whole time the app is open.
 */
public class Game {

    public static Player player;
    public static final String playerSavePath="Player.hak";
    public static AppCompatActivity activity;

    public static Options options;



    public static void SetFullScreen(){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE); //Removes the ugly title for the app.
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN); //Makes the activity app fullscreen.
    }

    public static Context getCurrentAppContext(){
        return activity.getApplicationContext();
    }
}
