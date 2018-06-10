package User;

import android.widget.TextView;

public class DialogueText {


    public static int SPEED_SLOW=100;
    public static int SPEED_MEDIUM=75;
    public static int SPEED_FAST=50;
    public static int SPEED_VERY_FAST=25;
    public static int SPEED_HYPER=10;
    public static int SPEED_INSTANT=0;


    /**
     * The current string to display for the text view.
     */
    private String currentString;

    /**
     * The full dialogue string to display when finished.
     */
    private String fullString;

    /**
     * The timer that handles printing all of the dialogue.
     */
    private TimerWrapper timer;

    /**
     * The current position in the dialogue that I am at.
     */
    private int position;

    /**
     * The text view in which to display the dialogue.
     */
    private TextView textToEdit;

    /**
     * Constructor for dialogue text.
     * @param TextToEdit The TextView element where this string will be displayed.
     * @param finalString The final string outcome for this dialogue text.
     * @param dialogueSpeed The speed in milliseconds before the next character of dialogue appears.
     */
    public DialogueText(TextView TextToEdit,String finalString,int dialogueSpeed){
        this.currentString="";
        this.fullString=finalString;
        this.textToEdit=TextToEdit;
        this.timer=new TimerWrapper("dialogueString", getTimerCountFromStringSize(), new IFunction() {
            @Override
            public void execute() {
                //Do nothing when finished.
            }

            @Override
            public void countDownExecute() {
                //Every time the timer ticks down add a letter to the textview and currentString variables.
                printNextDialogueCharacter();
            }
        },false,true,dialogueSpeed);
    }

    /**
     * Gets how many ticks the timer will go through to print out the full dialogue string.
     * @return
     */
    private int getTimerCountFromStringSize(){
        return this.fullString.length();
    }

    /**
     * Starts the dialogue timer to start printing out the text string.
     */
    public void startDialogue(){
        this.timer.start();
    }

    /**
     * Pauses the dialogue timer so that it won't print out more of the text string.
     */
    public void pauseDialogue(){
        this.timer.pause();
    }

    /**
     * Resumes the dialogue text printing.
     */
    public void resumeDialogue(){
        this.timer.resume();
    }

    /**
     * Print the next character in the dialogue sequence.
     */
    public void printNextDialogueCharacter(){
        char character=fullString.charAt(position);
        currentString+=character;
        textToEdit.setText(currentString);
        position++;
    }

    public TimerWrapper getTextTimer(){
        return this.timer;
    }

}
