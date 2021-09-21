package com.janfic.games.spacejunk.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 *
 * @author Jan Fic
 */
public class DirectionIndicator extends Actor {

    Texture texture;

    public DirectionIndicator() {
        this.texture = new Texture("arrow.png");
        this.setSize(texture.getWidth(), texture.getHeight());
        this.setOrigin(Align.center);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
    }
}
