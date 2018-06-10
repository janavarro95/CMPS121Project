package User.OptionsPackage;

import User.Game;

public class SoundOptions {
    public float volume;
    public boolean muted;

    public SoundOptions(){
        this.volume=1.00f;
        this.muted=false;
    }

    public SoundOptions(float volume){
        this.volume=volume;
        this.muted=false;
    }

    /**
     * Constructor.
     * @param volume The default volume level.
     * @param muted The boolean to set if the volume is muted or not.
     */
    public SoundOptions(float volume, boolean muted){
        this.volume=volume;
        this.muted=muted;
    }

    /**
     * Toggles the mute feature for whether or not sound should play.
     */
    public void toggleMute(){
        if(this.muted){
            this.muted=false;
            return;
        }
        else{
            this.muted=true;
            return;
        }
    }

    /**
     * Gets the volume associated with the options. Returns 0.0 if the volume is muted.
     * @return
     */
    public float getVolume(){
        if(this.muted) return 0.00f;
        else return this.volume;
    }

    public void setVolume(float amount){
        if(amount<0) amount=0;
        if(amount>1.00f) amount=1.00f;
        this.volume=amount;
    }



}
