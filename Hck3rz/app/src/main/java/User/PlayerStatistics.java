package User;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Keeps track of various things the player has been doing. This is a game about hacking after all.
 */
public class PlayerStatistics {

    /**
     * The number of times a player has clicked a button.
     */
    public Integer numberOfButtonsClicked;

    /**
     * The number of times the player has logged in.
     */
    public Integer numberOfTimesLoggedIn;

    public Integer numberOfMinigamesPlayed;

    /**
     * The file name that the player's statistic information is saved/loaded from.
     */
    private static final String fileSaveName = "PlayerStatistics.hak";

    /**
     * Constructor.
     */
    public PlayerStatistics(){
        this.numberOfButtonsClicked=0;
        this.numberOfTimesLoggedIn=0;
        this.numberOfMinigamesPlayed=0;
    }


    /**
     * Saves the player statistics to a file.
     * @param context Use (ActivityName).this to be able to pass in the context to save the player.
     *                Reference: https://stackoverflow.com/questions/4118751/how-do-i-serialize-an-object-and-save-it-to-a-file-in-android
     */
    public void save(Context context) {
        try {

            //Check if our file exists and if so, delete it so we can overwrite it.
            File f= context.getFileStreamPath(fileSaveName);
            if(f.exists()) {
                f.delete();
            }

            FileOutputStream fos = context.openFileOutput(fileSaveName, Context.MODE_PRIVATE); //Open a file stream
            ObjectOutputStream os = new ObjectOutputStream(fos); //open an object stream
            os.writeObject(this); //Save the player statistics to the file.
            os.close(); //Close the object stream.
            fos.close(); //Close the file stream.
        } catch (Exception e) {
            e.printStackTrace(); //Print out an error if there is one.
        }
    }

    /**
     * Loads the save data of the player's statistics.
     * @param context Use (ActivityName).this to be able to pass in the context to save the player.
     *                Reference: https://stackoverflow.com/questions/4118751/how-do-i-serialize-an-object-and-save-it-to-a-file-in-android
     */
    @Nullable
    public static PlayerStatistics load(Context context) {
        try {
            FileInputStream fis = context.openFileInput(fileSaveName); //Load the file if there is one.
            ObjectInputStream is = new ObjectInputStream(fis); //Open up an object input stream.
            PlayerStatistics playerStatistics = (PlayerStatistics) is.readObject(); //Read in the player statistics object from the stream.
            is.close(); //Close the object input stream.
            fis.close(); //Close the file input stream.
            return playerStatistics; //Return the player statistics object.
        } catch (Exception err) {
            Log.v("File load error",err.toString()); //An error occured when loading in the player's save data.
            return null;
        }
    }

}
