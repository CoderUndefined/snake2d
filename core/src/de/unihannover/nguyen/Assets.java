package de.unihannover.nguyen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
    public static Texture bitmap;
    public static TextureRegion snakeSprite;
    public static TextureRegion snakeSprite2;
    public static Sprite sprite;

    public static Texture loadTexture (String imagePath) {
        return new Texture(Gdx.files.internal(imagePath));
    }

    public static void load() {
        bitmap = loadTexture("example.png");
        snakeSprite = new TextureRegion(bitmap,0,0,30,30);
        snakeSprite2 = new TextureRegion(bitmap,30,0,30,30);
        sprite = new Sprite(snakeSprite2);
        sprite.setAlpha(0f);
        sprite.setColor(0.1f,0.1f,0.1f,0.1f);
    }
}
