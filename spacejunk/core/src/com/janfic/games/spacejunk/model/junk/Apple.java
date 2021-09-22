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
public class Apple extends Junk {

    public Apple() {
        super(new Texture(Gdx.files.internal("junk/apple.png")), new Rectangle(0, 0, 16, 16));
        setSize(16, 16);
        setOrigin(Align.center);
    }
}
