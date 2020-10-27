package de.unihannover.nguyen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.unihannover.nguyen.MyGdxGame;

public class DesktopLauncher {

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width  = 960;
        config.height = 540;
        config.vSyncEnabled = false;
        config.foregroundFPS = 0;
        config.backgroundFPS = 0;
        new LwjglApplication(new MyGdxGame(), config);
    }
}
