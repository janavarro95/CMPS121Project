package User;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.example.hck3rz.hck3rz.MainActivity;
import com.example.hck3rz.hck3rz.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Utilities.SoundUtilities;

//sound reference for you got mail: http://www.thesoundarchive.com/email/youGotmail.wav

public class PostMan {


    public static boolean hasNewMail;

    /**
     * Resource pool of all valid emails.
     */
    public static ArrayList<Email> EmailPool=new ArrayList<Email>(){
        //{new Email();}

    };


    public static void initializeEmails(Context context){
        EmailPool=new ArrayList<>();
       EmailPool.add(new Email(R.drawable.email,"First Email","Unknown","unknownSender@system.net",R.drawable.email,PostMan.readFromFile(context,"FirstEmail.txt"),"FirstEmail"));
       EmailPool.add(new Email(R.mipmap.ic_launcher,"HOT NEW EMAIL","My Mom","mom@aol.com",R.mipmap.ic_launcher,"Hello","momLovesYou"));
    }

    /**
     * Saves all of the emails the player has recieved to a file.
     * @param context
     * @throws IOException
     */
    public static void saveEmailToFile(Context context) throws IOException {
        for(int i=0; i<Game.player.emails.size(); i++) {
            Game.player.emails.get(i).save(context);
        }

    }

    /**
     * Load all emails from saved files and puts them into the player's inbox.
     * @param context
     * @throws IOException
     */
    public static void loadEmailsFromFiles(Context context) throws IOException {

        File file = new File(context.getFilesDir()+File.pathSeparator+"EmailsDir");
        if(file.exists()==false) file.mkdir();

        String[] filesList=file.list();
        for(int i=0; i<filesList.length;i++){
            if(filesList[i].contains(".email")) { //If we find an email file process it.
                Email e = Email.load(context, filesList[i]);
                addEmailToInbox(e,false);
                //Game.player.emails.add(e); //Add the email back into the player's inbox;
            }
            else{
                //otherwise do nothing.
            }
        }
    }

    /**
     * Delete all emails in the game's directory in case something went very wrong.
     * @param context
     */
    public static void deleteAllEmailsOnDisk(Context context){
        File file = new File(context.getFilesDir()+File.pathSeparator+"EmailsDir");
        if(file.exists()==false) file.mkdir();

        File[] filesList=file.listFiles();
        for(int i=0; i<filesList.length;i++){
            filesList[i].delete();
        }
    }

    /**
     * Adds an email to the player's inbox if they haven't received the same email before.
     * @param e The email to add to the player's inbox.
     */
    public static void addEmailToInbox(Email e,boolean playSound){
        for(int i=0; i<Game.player.emails.size();i++){
            if(e.uniqueID==Game.player.emails.get(i).uniqueID){
                Log.v("Postman Add Email Error","Error, player already has an email with same unique id");
                return;
            }
        }
        if(playSound==true) {
            playNewMailSound();
        }
        hasNewMail=true;
        Game.player.emails.add(e); //If we don't have an email with the same unique ID add it to my inbox.
    }

    /**
     * Get an email from the email pool with a unique id string.
     * @param uniqueID
     * @return
     */
    public static Email getEmailByUniqueID(String uniqueID){
        for (int i=0; i< EmailPool.size();i++) {
            Log.v("Get an email",EmailPool.get(i).subject);
            if(EmailPool.get(i).uniqueID.equals(uniqueID)){
                return EmailPool.get(i);
            }
        }
        Log.v("getEmailByUniqueID","Error: Unique id not found!");
        return null;
    }

    /**
     * Gets an email from the email pool from the subject name. May cause issues with duplicates.
     * @param subjectName
     * @return
     */
    public static Email getEmailBySubjectName(String subjectName){
        for (Email e:
                EmailPool ) {
            if(e.subject==subjectName){
                return e;
            }
        }
        Log.v("getEmailByUniqueID","Error: Unique id not found!");
        return null;
    }

    /**
     * Ensures that there are no duplicate email ids.
     * @return
     * @throws Exception
     */
    public static boolean ensureAllEmailsAreUnique() throws Exception {
        for (Email e:
             EmailPool) {
            for (Email other:
                 EmailPool) {
                if(e.uniqueID==other.uniqueID && e!=other){
                    Log.v("Unique Email Check!","All emails are not unique!");
                    throw new Exception("NON UNIQUE CHECK");
                }
            }
        }
        return true;
    }



    //Reference: https://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
    public static String readFromFile(Context context, String filename) {

        String ret = "";

        try {
            InputStream inputStream = context.getAssets().open(filename);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("Postman", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("Postman", "Can not read file: " + e.toString());
        }

        return ret;
    }

    public static void playNewMailSound(){
        SoundUtilities.playSound(Game.activity,R.raw.yougotmail);
    }

}
