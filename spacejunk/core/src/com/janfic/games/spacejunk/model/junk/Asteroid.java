package com.janfic.games.spacejunk.model.junk;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Align;
import com.janfic.games.spacejunk.model.Junk;

/**
 *
 * @author Jan Fic
 */
public class Asteroid extends Junk {

    public Asteroid() {
        super(null, null);
        int r = (int) (Math.random() * 2);
        if (r == 0) {
            setTexture(new Texture(Gdx.files.internal("junk/asteroid_1.png")));
        } else {
            setTexture(new Texture(Gdx.files.internal("junk/asteroid_2.png")));
        }
        setHitbox(new Rectangle(0, 0, getTexture().getWidth(), getTexture().getHeight()));
        setSize(getTexture().getWidth(), getTexture().getHeight());
        setOrigin(Align.center);
    }
}
