package User;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TimerWrapper {

    public static ArrayList<TimerWrapper> timers=new ArrayList<>();


    /**
     * The java timer to handle the one second count down.
     */
    public Timer timer;

    /**
     * The unqiue id of the timer.
     */
    public String timerID;

    /**
     * The max time of the timer.
     */
    private int maxTime;
    /**
     * The remaining time on the timer.
     */
    public int timeRemaining;

    /**
     * The hardcoded function id of the timer.
     */
    public String functionID;

    /**
     * Checks whether or not the timer resets itself after it runs out of time.
     */
    public boolean doesLoop;

    /**
     * Checks whether or not the timer is counting down.
     */
    public boolean isPaused;


    /**
     * Creates a timer wrapper that handles basic timer functionality.
     * @param TimerID
     * @param maxTime
     * @param functionID
     * @param doesFunctionLoop
     */
    public TimerWrapper(String TimerID,int maxTime,String functionID,boolean doesFunctionLoop, boolean isPaused){

        this.timer=new Timer();
        this.timerID=TimerID;

        this.timeRemaining=maxTime;
        this.maxTime=maxTime;

        this.functionID=functionID;

        this.doesLoop=doesFunctionLoop;
        this.isPaused=isPaused;

        setSchedule(this);
    }

    /**
     * Sets the functions to run when the timer's internal currentTimeRemaining value hits 0;
     * @param tWrapper
     */
    private void setSchedule(final TimerWrapper tWrapper){
        tWrapper.timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isPaused == false) {
                    tWrapper.tickTimerOneSecond();
                    if (tWrapper.timeRemaining == 0) {
                        //Not the best way to do this but I don't know if java has function pointers.
                        if (tWrapper.functionID == "Say Hello") {
                            TimerTasks.sayHello();
                            if (tWrapper.doesLoop) {
                                tWrapper.restart();
                            }
                        }

                    }
                }
            }
        },1000);
    }

    /**
     * Resets the timer's current time to it's max time value;
     */
    public void resetTime(){
        this.timeRemaining=this.maxTime;
    }

    /**
     * Tick's the timer's internal currentTimeRemaining value by one and handles logic on whether or not to reset it.
     */
    public void tickTimerOneSecond(){

        if(this.timeRemaining <= 0 && this.doesLoop){
            this.restart();
        }
        if(this.timeRemaining <= 0 && !this.doesLoop){
            return;
        }
        this.timeRemaining--;
    }

    /**
     * Sets the maximum amount of time on the timer.
     * @param seconds
     */
    public void setMaxTime(int seconds){
        this.maxTime=seconds;
    }

    /**
     * Sets the amount of time remaining on this timer.
     * @param seconds
     */
    public void setTimeRemaining(int seconds){
        this.timeRemaining=seconds;
    }

    /**
     * Sets the timer's loop functionality.
     * @param doesLoop
     */
    public void setDoesLoop(boolean doesLoop){
        this.doesLoop=doesLoop;
    }

    /**
     * Adds time to the timer's internal currentTimeRemaining value. Ignore's it's maxTime value;
     * @param seconds
     */
    public void addTime(int seconds){
        int Seconds=Math.abs(seconds);
        this.timeRemaining+=Seconds;
    }

    /**
     * Subtracts time from a timer's internal currentTimeRemaining value.
     * @param seconds
     */
    public void subtractTime(int seconds){
        int Seconds=Math.abs(seconds);
        this.timeRemaining-=Seconds;
    }

    /**
     * Returns true if the timer has no time remaining and it doesn't loop.
     * @return
     */
    public boolean isFinished(){
        if(this.timeRemaining==0 && this.doesLoop==false) return true;
        else return false;
    }

    /**
     * Set's the timer's isPaused value to false so that it can resume counting down.
     */
    public void resume(){
        this.isPaused=false;
    }

    /**
     * Sets the current timer isPaused=false and resets it's current time value so that it can start counting.
     */
    public void start(){
        this.isPaused=false;
        this.resetTime();
    }

    /**
     * Pauses the current timer so that it can't count down anymore.
     */
    public void pause(){
        this.isPaused=true;
    }

    /**
     * Restarts the timer by setting isPaused to false and resetting the time value to the max value.
     */
    public void restart(){
        this.start();
    }

    /**
     * Stops the timer and prevents it from counting down any more.
     * The timer can be reinitialized with restart();
     */
    public void stop(){
        this.pause();
        this.timeRemaining=0;
    }


    /**
     *
     * @param timerID
     * @param maxTime
     * @param functID
     * @param doesLoop Checks whether or
     * @param isPaused If false the timer will start counting down immediately. If true, then it will need to be manually started.
     */
    public static void createTimer(String timerID, int maxTime, String functID, boolean doesLoop, boolean isPaused){
        if(timers==null) timers=new ArrayList<>();
        TimerWrapper t= new TimerWrapper(timerID,maxTime,functID,doesLoop,isPaused);
        timers.add(t);
    }

    /**
     * Get a timer from the list of timers that are created.
     * @param timerID
     * @return
     */
    public static TimerWrapper getTimer(String timerID){
        if(timers==null) timers=new ArrayList<>();
        for (TimerWrapper t:
             timers) {
            if(t.timerID.equals(timerID)){
                return  t;
            }
        }
        return null;
    }

    /**
     * Stops all timers held by the timers list and set's their times to 0 and isPaused values to true.;
     */
    public static void StopAllTimers(){
        for (TimerWrapper t: timers
                ) {
            t.stop();
        }
    }

    /**
     * Starts all timers held by the timers list. Sets their currentTime values to their maxValue and sets isPaused to false;
     */
    public static void StartAllTimers(){
        for (TimerWrapper t: timers
                ) {
            t.start();
        }
    }

    /**
     * Pauses all timers held by the timers list. Sets their isPaused values to true but does not affect their time values.
     */
    public static void PauseAllTimers(){
        for (TimerWrapper t: timers
                ) {
            t.pause();
        }
    }

    /**
     * Resumes all timers held by the timers list. Sets their isPaused values to false but does not affect their time value.
     */
    public static void ResumeAllTimers(){
        for (TimerWrapper t: timers
                ) {
            t.resume();
        }
    }

    /**
     * Restarts all of the timers held by the timers list. Sets their currentTime values to their max values and sets isPaused to false.
     */
    public static void RestartAllTimers(){
        for (TimerWrapper t: timers
                ) {
            t.restart();
        }
    }
}
