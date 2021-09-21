package com.janfic.games.spacejunk.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Jan Fic
 */
public class SpaceActor extends Actor {

    Texture texture;
    Rectangle hitbox;
    ShapeRenderer s;
    float vx, vy, ax, xy;

    public SpaceActor(Texture texture, Rectangle hitbox) {
        this.texture = texture;
        this.hitbox = hitbox;
        vx = 100;
        vy = 0;
        s = new ShapeRenderer();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(vx * delta, vy * delta);
        if (hitbox.setPosition(getX(), getY()).contains(0, getY()) || hitbox.setPosition(getX(), getY()).contains(getStage().getWidth(), getY())) {
            vx = -vx;
        }
        if (hitbox.setPosition(getX(), getY()).contains(getX(), 0) || hitbox.setPosition(getX(), getY()).contains(getX(), getStage().getHeight())) {
            vy = -vy;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(texture,
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
    }
}
