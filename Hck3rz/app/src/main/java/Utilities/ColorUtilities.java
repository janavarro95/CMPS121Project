package Utilities;

import  android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * A class that handles tedious code related to changing and getting colors for various things.
 */
public class ColorUtilities {

    /**
     * Returns a color int that can be used to change the color of text view and other elements.
     * @param activity The app activity.
     * @param id The id of the color in the app.
     * @return
     */
    public static int getColor(AppCompatActivity activity,int id){
        return activity.getResources().getColor(id);
    }

    /**
     * Takes in a TextView element and sets the text color for it.
     * @param text The TextView element to change the color of.
     * @param activity The app activity page.
     * @param id The R.colors.id reference to the color.
     */
    public static void setTextColor(TextView text, AppCompatActivity activity, int id){
        text.setTextColor(getColor(activity,id));
    }

}
