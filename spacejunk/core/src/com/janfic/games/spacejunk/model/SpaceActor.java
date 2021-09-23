package com.janfic.games.spacejunk.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

/**
 *
 * @author Jan Fic
 */
public class SpaceActor extends Actor {

    Texture texture;
    Polygon hitbox;
    ShapeRenderer sr;
    float vx, vy, ax, xy;
    float rDirection;

    public SpaceActor(Texture texture, Polygon hitbox) {
        this.texture = texture;
        this.hitbox = hitbox;
        int d = (int) (Math.random() * 60);
        vx = d;
        vy = 100 - d;
        sr = new ShapeRenderer();
        rDirection = 1;
        this.addAction(Actions.forever(Actions.rotateBy(rDirection * 360, (float) (Math.random() * 2) + 2)));
    }

    public float getrRotationDirection() {
        return rDirection;
    }

    public void reverseRotationDirection() {
        rDirection *= -1;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        moveBy(vx * delta, vy * delta);
        sr.setProjectionMatrix(getStage().getCamera().combined);
        float x = getX() + getOriginX() - hitbox.getOriginX();
        float y = getY() + getOriginY() - hitbox.getOriginY();
        Polygon screen = new Polygon(new float[]{0, 0, 0, getStage().getHeight(), getStage().getWidth(), getStage().getHeight(), getStage().getWidth(), 0});
        hitbox.setPosition(x, y);
        hitbox.setRotation(getRotation());
        Polygon overlap = new Polygon();
        if (Intersector.intersectPolygons(screen, hitbox, overlap)) {
            if (overlap.area() != hitbox.area()) {
                for (int i = 0; i < hitbox.getTransformedVertices().length; i += 2) {
                    Vector2 p = new Vector2(hitbox.getTransformedVertices()[i], hitbox.getTransformedVertices()[i + 1]);
                    if (p.x < 0 && vx < 0) {
                        vx = -vx;
                        break;
                    }

                    if (p.x > getStage().getWidth() && vx > 0) {
                        vx = -vx;
                        break;
                    }

                    if (p.y < 0 && vy < 0) {
                        vy = -vy;
                        break;
                    }

                    if (p.y > getStage().getHeight() && vy > 0) {
                        vy = -vy;
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(this.getColor());
        batch.draw(texture,
                getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(),
                getRotation(), 0, 0, texture.getWidth(), texture.getHeight(),
                false, false);
//        batch.end();
////        sr.begin(ShapeRenderer.ShapeType.Line);
////        sr.polygon(hitbox.getTransformedVertices());
////        sr.end();
//        batch.begin();
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void setHitbox(Polygon hitbox) {
        this.hitbox = hitbox;
    }

    public Polygon getHitbox() {
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
