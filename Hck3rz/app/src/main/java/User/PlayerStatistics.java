package User;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Keeps track of various things the player has been doing. This is a game about hacking after all.
 */
public class PlayerStatistics implements Serializable {


    private static final long serialVersionUID = 7829136421241571160L;
    /**
     * The number of times a player has clicked a button.
     */
    public int numberOfButtonsClicked;

    /**
     * The number of times the player has logged in.
     */
    public int numberOfTimesLoggedIn;

    public int numberOfMinigamesPlayed;

    public int score;

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
        this.score=0;
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
            writeObject(os); //Save the player statistics to the file.
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
            PlayerStatistics playerStatistics = (PlayerStatistics) readObject(is); //Read in the player statistics object from the stream.
            is.close(); //Close the object input stream.
            fis.close(); //Close the file input stream.
            return playerStatistics; //Return the player statistics object.
        } catch (Exception err) {
            Log.v("File load error",err.toString()); //An error occured when loading in the player's save data.
            return null;
        }
    }

    public static PlayerStatistics readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException
    {
        PlayerStatistics stats=new PlayerStatistics();
        try {
            stats.numberOfButtonsClicked = Integer.parseInt(aInputStream.readUTF());
        }
        catch (Exception err){

        }
        try{
            stats.numberOfTimesLoggedIn = Integer.parseInt(aInputStream.readUTF());
        }
        catch (Exception err){

        }
        try{
            stats.numberOfMinigamesPlayed = Integer.parseInt(aInputStream.readUTF());
        }
        catch (Exception err){

        }
        try{
            stats.score=Integer.parseInt(aInputStream.readUTF());
        }
        catch (Exception err){

        }
        return stats;
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException
    {
        aOutputStream.writeUTF(Integer.toString(numberOfButtonsClicked));
        aOutputStream.writeUTF(Integer.toString(numberOfTimesLoggedIn));
        aOutputStream.writeUTF(Integer.toString(numberOfMinigamesPlayed));
        aOutputStream.writeUTF(Integer.toString(score));
    }

}
