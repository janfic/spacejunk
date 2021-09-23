package com.janfic.games.spacejunk.model.junk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.utils.Align;
import com.janfic.games.spacejunk.model.Junk;

/**
 *
 * @author Jan Fic
 */
public class Asteroid extends Junk {

    public Asteroid(boolean big) {
        super(null, null);
        if (big == false) {
            setTexture(new Texture(Gdx.files.internal("junk/asteroid_1.png")));
        } else {
            setTexture(new Texture(Gdx.files.internal("junk/asteroid_2.png")));
        }
        setHitbox(new Polygon(new float[]{
            0, 0,
            0, getTexture().getHeight() * 0.75f,
            getTexture().getWidth() * 0.75f, getTexture().getHeight() * 0.75f,
            getTexture().getWidth() * 0.75f, 0}
        ));
        getHitbox().setOrigin(getTexture().getWidth() * 0.75f / 2, getTexture().getHeight() * 0.75f / 2);
        setSize(getTexture().getWidth(), getTexture().getHeight());
        setOrigin(Align.center);
    }
}
