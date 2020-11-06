package de.unihannover.nguyen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.unihannover.nguyen.MyGdxGame;


class Global {
    public static int scaleFactor = 2;
}

public class DesktopLauncher {

    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width  = 640*3;
        config.height = 360*3;
//        config.vSyncEnabled = false;
//        config.foregroundFPS = 0;
//        config.backgroundFPS = 0;
        new LwjglApplication(new MyGdxGame(), config);
    }
}
