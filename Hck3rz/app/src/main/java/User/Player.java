package User;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

/**
 * A class that deals with all of the information regarding the player.
 */
public class Player {

    private static final long serialVersionUID = 7829136421241571161L;
    /**
     * The username of the player that they type in the login screen.
     */
    public String username;
    /**
     * The password of the player that they type in the login screen.
     */
    public String password;

    public ArrayList<Email> emails; //Save these.


    public PlayerStatistics statistics;

    /**
     * The file name that the player's information is saved/loaded from.
     */
    //private static final String fileSaveName = "Player.hak";

    public Player(){
        this.emails=new ArrayList<>();
    }

    /**
     * Constructor.
     * @param username The username of the player.
     * @param password The password of the player.
     */
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.emails=new ArrayList<>();
        this.statistics=new PlayerStatistics();
    }

    /**
     * Saves the player to a file.
     * @param context Use (ActivityName).this to be able to pass in the context to save the player.
     *                Reference: https://stackoverflow.com/questions/4118751/how-do-i-serialize-an-object-and-save-it-to-a-file-in-android
     */
    public void save(Context context) throws IOException {
        try {

            //Check if our file exists and if so, delete it so we can overwrite it.
            File f= context.getFileStreamPath(Game.playerSavePath);
            if(f.exists()) {
                f.delete();
            }

            FileOutputStream fos = context.openFileOutput(Game.playerSavePath, Context.MODE_PRIVATE); //Open a file stream
            ObjectOutputStream os = new ObjectOutputStream(fos); //open an object stream

            os.writeUTF(username);
            os.writeUTF(password);
            this.statistics.save(context);
            //os.writeObject(this); //Save the player to the file.
            os.close(); //Close the object stream.
            fos.close(); //Close the file stream.
            Log.v("PlayerSave!","Save worked!");
        } catch (Exception e) {
            Log.v("SAVE ERROR",e.toString()); //Print out an error if there is one.
        }

        PostMan.saveEmailToFile(context);
    }

    /**
     * Loads the save data of the player to the instance of the game.
     * @param context Use (ActivityName).this to be able to pass in the context to save the player.
     *                Reference: https://stackoverflow.com/questions/4118751/how-do-i-serialize-an-object-and-save-it-to-a-file-in-android
     */
    public static void loadToGame(Context context) throws IOException {
        try {
            FileInputStream fis = context.openFileInput(Game.playerSavePath); //Load the file if there is one.
            ObjectInputStream is = new ObjectInputStream(fis); //Open up an object input stream.


            String username = is.readUTF();
            String password = is.readUTF();

            //Player player = (Player) is.readObject(); //Read in the player object from the stream.
            Player p= new Player();

            p.username=username;
            p.password=password;
            PlayerStatistics.load(context);

            is.close(); //Close the object input stream.
            fis.close();

            //fis.close(); //Close the file input stream.
            Game.player=p; //Set the loaded player's reference to the game's player reference.
            //Game.player.statistics=PlayerStatistics.load(context); //Load in the player's statistics.
            //Game.player.statistics.numberOfTimesLoggedIn++; //Increment the number of times the player has logged in.

            PostMan.loadEmailsFromFiles(context);
        } catch (Exception err) {
            Log.v("LoadError",err.toString()); //An error occured when loading in the player's save data.
        }


    }

    /**
     * Loads the save data of the player to a player object to be able to pull information from it.
     * @param context Use (ActivityName).this to be able to pass in the context to save the player.
     *                Reference: https://stackoverflow.com/questions/4118751/how-do-i-serialize-an-object-and-save-it-to-a-file-in-android
     */
    @Nullable
    public static Player loadPlayerObject(Context context) {
        try {
            FileInputStream fis = context.openFileInput(Game.playerSavePath); //Load the file if there is one.
            ObjectInputStream is = new ObjectInputStream(fis); //Open up an object input stream.


            String username = is.readUTF();
            String password = is.readUTF();

            Player p= new Player();

            p.username=username;
            p.password=password;

            //Player player = (Player) is.readObject(); //Read in the player object from the stream.
            is.close();
            fis.close(); //Close the object input stream.
            //fis.close(); //Close the file input stream.
            //player.statistics=PlayerStatistics.load(context);
            return  p;
        } catch (Exception err) {
            Log.v("PlayerLoad",err.toString()); //An error occured when loading in the player's save data.
            return null;
        }
    }


}
