package com.janfic.games.spacejunk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.janfic.games.spacejunk.model.Player;

/**
 *
 * @author Jan Fic
 */
public class GameScreen implements Screen {
    
    Stage playStage;
    Player player;
    
    @Override
    public void show() {
        this.playStage = new Stage(new FitViewport(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
        this.player = new Player();
        this.playStage.addActor(player);
        player.setPosition(100, 100);
    }
    
    @Override
    public void render(float f) {
        playStage.act(f);
        playStage.draw();
    }
    
    @Override
    public void resize(int i, int i1) {
    }
    
    @Override
    public void pause() {
    }
    
    @Override
    public void resume() {
    }
    
    @Override
    public void hide() {
    }
    
    @Override
    public void dispose() {
    }
    
}
