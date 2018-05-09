package User;

import android.content.Context;

import com.example.hck3rz.hck3rz.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Email {

    public int image;
    public String subject;
    public boolean hasBeenRead;
    public String contents;
    public String senderName;
    public String senderEmail;
    public int senderIcon;

    public String uniqueID;

    /**
     * Default constructor.
     */
    public Email(){

    }

    /**
     * Constructor.
     * @param imageID Reference to R.drawable.id????
     * @param title The email subject.
     * @param senderName The name of the sender for the email.
     * @param senderEmail The email address of the sender of the email.
     * @param senderIcon The icon to display for the sender.
     * @param contents The body of the email.
     * @param uniqueID An unique id to ensure the email is never added twice.
     */
    public Email(int imageID,String title,String senderName,String senderEmail,int senderIcon, String contents, String uniqueID){
        this.image=imageID;
        this.subject=title;
        this.hasBeenRead=false;
        this.contents=contents;
        this.senderName=senderName;
        this.senderEmail=senderEmail;
        this.senderIcon=senderIcon;
        this.uniqueID=uniqueID;
    }

    /**
     * Read the email and go to an email display screen.
     */
    void readEmail(){
        if(this.hasBeenRead==false){
            this.hasBeenRead=true;
            this.image=R.drawable.email; //Read email icon.
        }
        //Do logic regarding displaying info.
    }

    /**
     * Save the email to disk.
     * @param context The app context.
     * @throws IOException
     */
    public void save(Context context) throws IOException {

        File f=new File(context.getFilesDir()+File.pathSeparator+"EmailsDir"+File.pathSeparator+this.subject+".email");
        if(f.exists()) return; //Don't want to save dupe copies.

        FileOutputStream fis =new FileOutputStream(f);
        ObjectOutputStream ois =new ObjectOutputStream(fis);


        ois.writeUTF(this.subject);
        ois.write(this.image);

        ois.writeUTF(this.senderName);
        ois.writeUTF(this.senderEmail);
        ois.write(this.senderIcon);

        ois.writeBoolean(this.hasBeenRead);
        ois.writeUTF(this.contents);

        ois.writeUTF(this.uniqueID);

        ois.close();
        fis.close();
    }

    /**
     * Load an email from file.
     * @param context The app context.
     * @param absPath The path to the file on disk.
     * @return
     * @throws IOException
     */
    public static Email load(Context context,String absPath) throws IOException {
        File f=new File(absPath);
        if(f.exists()) return null; //Don't want to save dupe copies.

        FileInputStream fis =new FileInputStream(f);
        ObjectInputStream ois =new ObjectInputStream(fis);


        String subject=ois.readUTF();
        int image=ois.read();

        String senderName=ois.readUTF();
        String senderEmail=ois.readUTF();
        int senderIcon=ois.read();

        boolean hasBeenRead=ois.readBoolean();
        String contents=ois.readUTF();

        String uniqueID=ois.readUTF();

        ois.close();
        fis.close();
        Email email=new Email();

        email.senderEmail=senderEmail;
        email.subject=subject;
        email.senderIcon=senderIcon;
        email.contents=contents;
        email.image=image;
        email.hasBeenRead=hasBeenRead;
        email.senderName=senderName;
        email.uniqueID=uniqueID;

        return email;

    }
}
