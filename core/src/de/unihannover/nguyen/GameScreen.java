package de.unihannover.nguyen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GameScreen extends ScreenAdapter {

    MyGdxGame game;
    GameThread gameThread;


    float circleX = 300;
    float circleY = 150;
    float circleRadius = 50;

    float xSpeed = 4;
    float ySpeed = 3;

    public GameScreen(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        gameThread = new GameThread();
        gameThread.start();


        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;
                if (Vector2.dst(circleX, circleY, x, renderY) < circleRadius) {
                    game.setScreen(new EndScreen(game));
                }
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                gameThread.handleInput(keycode);

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        if(!gameThread.isActive()) {
            game.setScreen(new GameOverScreen(game));
        }

        int rectSize = 30;

        // 18 is the game height. 1

        GameState[][] map = gameThread.getMap();
        int xMax = gameThread.getSIZE_X();
        int yMax = gameThread.getSIZE_Y();


        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
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
//                    game.shapeRenderer.circle(x*rectSize,y*rectSize,rectSize);
            }
        }

        game.shapeRenderer.end();

//
//        circleX += xSpeed;
//        circleY += ySpeed;
//
//        if (circleX < 0 || circleX > Gdx.graphics.getWidth()) {
//            xSpeed *= -1;
//        }
//
//        if (circleY < 0 || circleY > Gdx.graphics.getHeight()) {
//            ySpeed *= -1;
//        }
//
//        Gdx.gl.glClearColor(0, 0, .25f, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        game.shapeRenderer.setColor(0, 1, 0, 1);
//        game.shapeRenderer.circle(circleX, circleY, 75);
//        game.shapeRenderer.end();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}


// 18 -> 0
// 17 -> 1
//