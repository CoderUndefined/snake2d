package de.unihannover.nguyen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Game screen that shows a representation of the GameThread.
 * There is no actual game logic inside the game screen!
 *
 * But the input processing starts here
 */
public class GameScreen extends ScreenAdapter {
    MyGdxGame game;
    GameThread gameThread;

    /**
     * Basic constructor with the parent class to allow screen switching
     * @param game parent class
     */
    public GameScreen(MyGdxGame game) {
        this.game = game;
    }

    /**
     * Starts the GameThread and sets the input processing
     */
    @Override
    public void show() {
        gameThread = new GameThread();
        gameThread.start();

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                gameThread.handleInput(keycode);
                return true;
            }
        });
    }

    /**
     * Redirects to actualRender
     *
     * @param delta delta time, unused here.
     */
    @Override
    public void render(float delta) {
        actualRender();
    }

    /**
     * Actually rendering the contents from the GameThread
     *
     * Empty space is represented by the BLACK background.
     *
     * Fill the space with a ShapeRenderer
     *
     * Player 1 is represented by RED squares
     * Player 2 is represented by BLUE squares
     *
     * Food beacons are represented by GREEN squares. Only 1 exists at any given time.
     *
     * Switch to the GameOverScreen if the game thread has ended. Which happens if the game is over
     */
    private void actualRender() {
        if(!gameThread.isActive()) {
            game.setScreen(new GameOverScreen(game));
        }
        final int rectSize = 20*3;

        GameState[][] map = gameThread.getMap();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.batch.begin();
        for(int x = 0; x < gameThread.getSIZE_X(); x++) {
            for(int y = 0; y < gameThread.getSIZE_Y(); y++) {
                if(map[x][y] ==  GameState.ONE) {
                    game.shapeRenderer.setColor(1,0,0,1);
                    game.shapeRenderer.rect(x * rectSize,
                        (18-1-y) * rectSize, rectSize, rectSize);
                }
                if(map[x][y] ==  GameState.TWO) {
                    game.shapeRenderer.setColor(0,0,1,1);
                    game.shapeRenderer.rect(x * rectSize,
                        (18-1-y) * rectSize, rectSize, rectSize);
                }
                if(map[x][y] ==  GameState.BEACON) {
                    game.shapeRenderer.setColor(0,1,0,1);
                    game.shapeRenderer.rect(x * rectSize,
                        (18-1-y) * rectSize, rectSize, rectSize);
                }
            }
        }

        game.batch.end();
        game.shapeRenderer.end();

    }

    /**
     * Remove the input processor when hiding this screen
     */
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}