package com.janfic.games.spacejunk;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.janfic.games.spacejunk.screens.GameScreen;

public class SpaceJunkGame extends Game {
    
    SpriteBatch batch;
    Texture img;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        this.setScreen(new GameScreen());
    }
    
    @Override
    public void render() {
            ScreenUtils.clear(0, 0, 0, 1);
        this.getScreen().render(Gdx.graphics.getDeltaTime());
    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
}
