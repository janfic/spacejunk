package com.janfic.games.spacejunk.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author Jan Fic
 */
public class Player extends SpaceActor {

    DirectionIndicator arrow;
    float arrowRotation, arrowDirection;

    public Player() {
        super(new Texture(Gdx.files.internal("astronaut.png")), new Polygon());
        this.hitbox = new Polygon(new float[]{0, 0, 0, texture.getHeight() - 16, texture.getWidth() - 16, texture.getHeight() - 16, texture.getWidth() - 16, 0,});
        this.hitbox.setOrigin((texture.getWidth() - 16) / 2, (texture.getHeight() - 16) / 2);
        setSize(texture.getWidth(), texture.getHeight());
        this.arrow = new DirectionIndicator();
        this.setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
        arrowDirection = 1;
//        this.addAction(Actions.forever(Actions.rotateBy(360, 4)));
        this.addAction(Actions.rotateBy(-180));
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
            arrowDirection = -arrowDirection;
        } else {
            arrowRotation += 180 * delta * arrowDirection;
            if (arrowRotation > 360) {
                arrowRotation = arrowRotation - 360;
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
