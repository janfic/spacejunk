package com.janfic.games.spacejunk.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author Jan Fic
 */
public class SpaceActor extends Actor {

    Texture texture;
    Rectangle hitbox;
    float vx, vy, ax, xy;

    public SpaceActor(Texture texture, Rectangle hitbox) {
        this.texture = texture;
        this.hitbox = hitbox;
        int d = (int) (Math.random() * 100);
        vx = d;
        vy = 100 - d;
        this.addAction(Actions.forever(Actions.rotateBy(360, (float) (Math.random() * 4) + 1)));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(vx * delta, vy * delta);
        if (hitbox.setPosition(getX() + getOriginX() - hitbox.width / 2, getY() + getOriginY() - hitbox.height / 2).contains(0, getY() + getOriginY() - hitbox.height / 2) && vx < 0) {
            vx = -vx;
        }
        if (hitbox.setPosition(getX() + getOriginX() - hitbox.width / 2, getY() + getOriginY() - hitbox.height / 2).contains(getStage().getWidth(), getY() + getOriginY() - hitbox.height / 2) && vx > 0) {
            vx = -vx;
        }
        if (hitbox.setPosition(getX() + getOriginX() - hitbox.width / 2, getY() + getOriginY() - hitbox.height / 2).contains(getX() + getOriginX() - hitbox.width / 2, 0) && vy < 0) {
            vy = -vy;
        }
        if (hitbox.setPosition(getX() + getOriginX() - hitbox.width / 2, getY() + getOriginY() - hitbox.height / 2).contains(getX() + getOriginX() - hitbox.width / 2, getStage().getHeight()) && vy > 0) {
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

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Texture getTexture() {
        return texture;
    }

    public void reverse() {
        vx = -vx;
        vy = -vy;
    }

    public void setVelocity(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public float getVelocityX() {
        return vx;
    }

    public float getVelocityY() {
        return vy;
    }
}
