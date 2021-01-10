package de.unihannover.nguyen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends Game {
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    BitmapFont font;


    @Override
    public void create () {
        batch = new SpriteBatch();
        Assets.load();

        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        setScreen(new TitleScreenOne(this));
    }

    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
