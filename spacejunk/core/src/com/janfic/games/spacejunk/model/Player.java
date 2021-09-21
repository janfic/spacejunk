package com.janfic.games.spacejunk.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author Jan Fic
 */
public class Player extends SpaceActor {

    DirectionIndicator arrow;
    float arrowRotation;

    public Player() {
        super(new Texture("astronaut.png"), new Rectangle());
        this.hitbox = new Rectangle(0, 0, texture.getWidth(), texture.getHeight());
        setSize(texture.getWidth(), texture.getHeight());
        this.arrow = new DirectionIndicator();
        this.setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
        this.addAction(Actions.forever(Actions.rotateBy(360, 4)));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        arrow.setPosition(getX() - ((arrow.getWidth() - getWidth()) / 2), getY() - ((arrow.getHeight() - getHeight()) / 2));
        arrow.setRotation(arrowRotation);
        if (Gdx.input.isButtonJustPressed(Buttons.LEFT)) {
            Vector2 v = new Vector2(0, 100);
            v = v.rotateDeg(arrowRotation);
            vx = v.x;
            vy = v.y;
        } else {
            arrowRotation += -180 * delta;
            if (arrowRotation >= 360) {
                arrowRotation = 360 - arrowRotation;
            }
            if (arrowRotation < 0) {
                arrowRotation = arrowRotation + 360;
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        arrow.draw(batch, parentAlpha);
    }
}
