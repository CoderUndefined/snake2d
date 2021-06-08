package de.unihannover.nguyen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.unihannover.nguyen.screens.TitleScreenOne;

/**
 * Parent class which controls the currenly used screen and can switch screen
 */
public class MyGdxGame extends Game {
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;

    /**
     * Create the elements and sets the screen to the title screen
     */
    @Override
    public void create () {
        batch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        setScreen(new TitleScreenOne(this));
    }

    /**
     * Disposes elements to free memory
     */
    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
