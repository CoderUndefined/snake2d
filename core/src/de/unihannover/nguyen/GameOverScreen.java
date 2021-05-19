package de.unihannover.nguyen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

/**
 * Very similar to the start screen
 */
public class GameOverScreen extends ScreenAdapter{

    MyGdxGame game;

    /**
     * Basic constructor with the parent class to allow screen switching
     * @param game parent class
     */
    public GameOverScreen(MyGdxGame game) {
        this.game = game;
    }

    /**
     * Initiates the screen. Also sets a basic InputListener, pressing ENTER resets the game
     */
    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new TitleScreenOne(game));
                }
                return true;
            }
        });
    }

    /**
     * Renders the text
     *
     * @param delta delta time, unused
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.font.draw(game.batch, "Game over!",
                Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "",
                Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Please Press enter to restart.",
                Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();
    }

    /**
     * Remove the input processor when hiding this screen
     */
    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}

