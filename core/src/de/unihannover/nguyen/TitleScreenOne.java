package de.unihannover.nguyen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

/**
 * Basic title screen with basic instructions on how to play the game
 */
public class TitleScreenOne extends ScreenAdapter {

    MyGdxGame game;

    /**
     * Basic constructor with the parent class to allow screen switching
     * @param game parent class
     */
    public TitleScreenOne(MyGdxGame game) {
        this.game = game;
    }

    /**
     * Initiates the screen. Also sets a basic InputListener, pressing SPACE starts the game
     */
    @Override
    public void show() {
        Stage stage = new Stage();
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                    return true;
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Renders the text with the basic instructions
     * @param delta delta time, unused
     */
    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "SnakeFourTwo",
            Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "WASD for player 1, Arrow keys for player 2",
            Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Eat food to grow. Try not to lose. Press space to play.",
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




