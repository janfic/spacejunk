package com.janfic.games.spacejunk.model.junk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.janfic.games.spacejunk.model.Junk;

/**
 *
 * @author Jan Fic
 */
public class Apple extends Junk {

    public Apple() {
        super(new Texture(Gdx.files.internal("junk/apple.png")), new Polygon());
        setHitbox(new Polygon(new float[]{
            0, 0,
            0, getTexture().getHeight(),
            getTexture().getWidth(), getTexture().getHeight(),
            getTexture().getWidth(), 0
        }));
        getHitbox().setOrigin(getTexture().getWidth() / 2, getTexture().getHeight() / 2);
        setSize(getTexture().getWidth(), getTexture().getHeight());
        setOrigin(Align.center);
    }
}
