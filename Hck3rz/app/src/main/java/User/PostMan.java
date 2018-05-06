package User;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
TODO: Load email contents from .txt files
TODO: Make email display activity.
 */
public class PostMan {


    /**
     * Resource pool of all valid emails.
     */
    public static ArrayList<Email> EmailPool=new ArrayList<Email>(){
        //{new Email();}
    };


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
                addEmailToInbox(e);
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
    public static void addEmailToInbox(Email e){
        for(int i=0; i<Game.player.emails.size();i++){
            if(e.uniqueID==Game.player.emails.get(i).uniqueID){
                Log.v("Postman Add Email Error","Error, player already has an email with same unique id");
                return;
            }
        }
        Game.player.emails.add(e); //If we don't have an email with the same unique ID add it to my inbox.
    }

    /**
     * Get an email from the email pool with a unique id string.
     * @param uniqueID
     * @return
     */
    public static Email getEmailByUniqueID(String uniqueID){
        for (Email e:
            EmailPool ) {
            if(e.uniqueID==uniqueID){
                return e;
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

    public static void loadContentsFromDisk(String uniqueID){

    }

}
