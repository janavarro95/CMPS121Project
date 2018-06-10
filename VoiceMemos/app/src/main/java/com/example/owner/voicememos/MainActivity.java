package com.example.owner.voicememos;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


//Permission and media recorder code taken from class example which is found here: https://users.soe.ucsc.edu/~dustinadams/CMPS121/examples/

public class MainActivity extends AppCompatActivity {

    ListView memoList; //The list view
    public static File saveFile; //The file that holds the int for the number of audio recordings.
    public static int saveInt; //The current integer position I am at.
    ArrayList<String> memoItems; //The list of all of the strings of the names of the audio files I have recorded thus far.
    ArrayList<String> emptyItems; //An empty display list.

    public static MediaRecorder mediaRecorder ; //The media recorder.
    public static final int RequestPermissionCode = 1; //The permission code.

    public static MainActivity activity; //A reference to this activity.

    public MediaPlayer player; //My media player.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //Hides the original action bar.
        setContentView(R.layout.activity_main);
        initialize(); //Initialize all of my stuff.
        setClickFunction(); //Set the functions to play an audio clip when I click an item in my list view.
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialize();
        setClickFunction();
    }

    /**
     * Initialize basic variables to be used only once.
     */
    private void initialize(){
        activity=this;
        saveFile=new File(this.getApplicationContext().getFilesDir()+"/"+"SaveFile.ser"); //Initialize the save file path
        saveInt=0; //Empty initialization.
        this.memoList=findViewById(R.id.MAIN_ACTIVITY_LIST_VIEW);
        try {
            load(); //Load the actual saveInt value.
        } catch (IOException e) {
            //Log.v("FileNotFound","My file was probably not found.");
            e.printStackTrace();
        }
        memoItems=new ArrayList<>(); //Initialize array lists.
        emptyItems=new ArrayList<>();
        emptyItems.add("No Voice Memos");
        populateListItems(); //Populate my memoItems
        setArrayAddapter(); //Set the array adapter.

        //Allow me to click the button in my action bar.
        Button b= findViewById(R.id.addEventButtonActionBar); //Add event button
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceMemeoMenu(v);
                //newEventMenu(v); //Add event activity
            }
        });

        //Check my permissions for my app.
        if(checkPermission()) {
            MediaRecorderReady();

        } else {
            Log.v("Request","DA PERMISSIONS");
            requestPermission();
        }
    }

    //Set the functionality for when I click an item.
    public void setClickFunction(){
        final ListView listy = findViewById(R.id.MAIN_ACTIVITY_LIST_VIEW); //get a reference
        if(memoItems.size()>0) {
            // Set an item click listener for ListView
            listy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String fileName=(String) listy.getItemAtPosition(position); //Get a reference to the current event we are clicking.
                    String[] strings=fileName.split(" ");
                    player = new MediaPlayer();
                    try {
                        player.setDataSource(activity.getFilesDir()+"/"+"Audio Recording"+strings[2]+".3gp");
                        player.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.start();// play the audio
                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            player.stop();
                            player.release();
                        }
                    });
                }
            });
        }
        else{
            // Set an item click listener for ListView if we have no events to display.
            listy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // Do nothing if empty.
                }
            });
        }
    }


    /**
     * Saves the information of which voice memo I am on to a file. Also increments the save int value by 1.
     * @throws IOException
     */
    public static void save() throws IOException {
        saveInt++;
        FileOutputStream fos=new FileOutputStream(saveFile);
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeInt(saveInt);
        oos.close();
        fos.close();

        //This is called after stop()
    }

    /**
     * Loads all of the save information from the save file.
     * @throws IOException
     */
    public static void load() throws  IOException{

        FileInputStream fis=new FileInputStream(saveFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        saveInt=ois.readInt();
        ois.close();
        fis.close();
    }


    /**
     * Populates the memoItems List
     */
    private void populateListItems(){
        this.memoItems.clear();
        this.memoItems=initializeMemoItems();
    }

    /**
     * Initializes all of the necessary strings for the list view.
     * @return
     */
    private ArrayList<String> initializeMemoItems(){
        ArrayList<String> strings=new ArrayList<>();
        for(int i=0;i<saveInt;i++){
            int j=i+1;
            String itemName="Audio Recording "+ Integer.toString(j);
            strings.add(itemName);
        }
        return strings;
    }


    /**
     * Set the array addapter to display all of my list items.
     */
    private void setArrayAddapter(){
        if(memoItems.size()>0) {
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(memoList.getContext(), android.R.layout.simple_expandable_list_item_1, memoItems);
            memoList.setAdapter(stringArrayAdapter);
        }
        else{
            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(memoList.getContext(), android.R.layout.simple_expandable_list_item_1, emptyItems);
            memoList.setAdapter(stringArrayAdapter);
            Log.v("Set","Empty Item");
        }
    }


    /**
     * Go to my webview.
     * @param v
     */
    public void voiceMemeoMenu(View v){
        Intent i = new Intent(this,WebActivity.class);
        startActivity(i);
    }

    // initialize recorder object
    public static void MediaRecorderReady(){
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        setOutputFile();
    }


    /**
     * Sets the output file to the next file name when I am recording.
     */
    public static void setOutputFile(){
        mediaRecorder.setOutputFile(activity.getApplicationContext().getFilesDir()+"/"+"Audio Recording"+Integer.toString(saveInt+1)+".3gp");
    }

    // permissions from user
    private void requestPermission() {
        ActivityCompat.requestPermissions(MainActivity.this, new
                String[]{WRITE_EXTERNAL_STORAGE, RECORD_AUDIO}, RequestPermissionCode);
    }
    // callback method
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode:
                if (grantResults.length> 0) {
                    boolean StoragePermission = grantResults[0] ==
                            PackageManager.PERMISSION_GRANTED;
                    boolean RecordPermission = grantResults[1] ==
                            PackageManager.PERMISSION_GRANTED;

                    if (StoragePermission && RecordPermission) {
                        Toast.makeText(MainActivity.this, "Permission Granted",
                                Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }

    /**
     * Check to make sure my permissions are all set.
     * @return
     */
    public boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(),
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

}
