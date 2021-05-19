package de.unihannover.nguyen.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import de.unihannover.nguyen.MyGdxGame;


/**
 * Desktop launcher for this game, acts as entry point
 */
public class DesktopLauncher {

    /**
     * Main function of this application, the entry point.
     * Starts the application.
     *
     * @param arg command line arguments, unused.
     */
    public static void main (String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(640*3,360*3);
        new Lwjgl3Application(new MyGdxGame(), config);
    }
}
