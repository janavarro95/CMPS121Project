package User;

import android.content.Context;

/**
 * Holds static information that would be persistent the whole time the app is open.
 */
public class Game {

    public static Player player;
    public static final String playerSavePath="Player.hak";
    public static Context currentAppContext;

    public static float volume;
}
