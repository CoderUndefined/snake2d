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

public class TitleScreenOne extends ScreenAdapter {

    MyGdxGame game;

    public TitleScreenOne(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {

        Button button = new Button();
        Stage stage = new Stage();
        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if(keycode == Input.Keys.E) {
                    Gdx.graphics.setWindowedMode(1280,720);
                    return true;
                }
                if(keycode == Input.Keys.SPACE) {
                    game.setScreen(new GameScreen(game));
                    return true;
                }
                return true;
            }
        });
        button.setName("Button");

        button.setSize(100,100);
        button.setPosition(100,100);


        stage.addActor(button);

        Gdx.input.setInputProcessor(stage);

//        Gdx.input.setInputProcessor(new InputAdapter() {
//            @Override
//            public boolean keyDown(int keyCode) {
//                if (keyCode == Input.Keys.SPACE) {
//                    game.setScreen(new GameScreen(game));
//                }
//                return true;
//            }
//        });
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.draw(game.batch, "Title Screen!",
            Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "Click the circle to win.",
            Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        game.font.draw(game.batch, "Press space to play.",
            Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

    // abcdef
}

