package de.unihannover.nguyen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture bitmap;
    public static TextureRegion snakeSprite;
    public static TextureRegion snakeSprite2;
    public static TextureRegion snakeSprite3;
    public static TextureRegion snakeSprite4;
    public static Sprite sprite;

    public static Animation<TextureRegion> animation;

    public static Texture loadTexture (String imagePath) {
        return new Texture(Gdx.files.internal(imagePath));
    }

    public static void load() {
        bitmap = loadTexture("example.png");
        snakeSprite = new TextureRegion(bitmap,0,0,30,30);
        snakeSprite2 = new TextureRegion(bitmap,30,0,30,30);
        snakeSprite3 = new TextureRegion(bitmap,60,0,30,30);
        snakeSprite4 = new TextureRegion(bitmap,90,0,30,30);
        sprite = new Sprite(snakeSprite2);
        sprite.setAlpha(0f);
        sprite.setColor(0.1f,0.1f,0.1f,0.1f);

        TextureRegion[] textureRegions = {snakeSprite, snakeSprite2, snakeSprite3, snakeSprite4};

        animation = new Animation<>(0.25f, textureRegions);
        animation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    }
}
