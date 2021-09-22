package com.janfic.games.spacejunk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.janfic.games.spacejunk.SpaceJunkGame;
import com.janfic.games.spacejunk.model.Junk;
import com.janfic.games.spacejunk.model.Player;
import com.janfic.games.spacejunk.model.junk.Apple;
import com.janfic.games.spacejunk.model.junk.Asteroid;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan Fic
 */
public class GameScreen implements Screen {

    Stage playStage;
    Player player;

    SpaceJunkGame game;
    List<Junk> junk;

    Image background1;

    float progress;

    public GameScreen(SpaceJunkGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.playStage = new Stage(new FitViewport(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4));
        this.player = new Player();
        junk = new ArrayList<>();
        background1 = new Image(new Texture("background_1.png"));
        background1.setScale(2);

        Gdx.input.setInputProcessor(playStage);
        playStage.addActor(background1);
        this.playStage.addActor(player);

        junk.add(new Asteroid());

        for (Junk j : junk) {
            j.setPosition((float) (Math.random() * playStage.getWidth() - j.getWidth()), (float) (Math.random() * playStage.getHeight() - j.getHeight()));
            playStage.addActor(j);
        }

        player.setPosition(playStage.getWidth() / 4, playStage.getHeight() / 4);
    }

    @Override
    public void render(float delta) {
        playStage.act(delta);
        playStage.draw();
        for (int i = 0; i < junk.size(); i++) {
            for (int j = i + 1; j < junk.size(); j++) {
                if (junk.get(i).getHitbox().overlaps(junk.get(j).getHitbox())) {

                    float mi = junk.get(i).getHitbox().area();
                    float mj = junk.get(j).getHitbox().area();
                    float vix = junk.get(i).getVelocityX();
                    float viy = junk.get(i).getVelocityY();
                    float vjx = junk.get(j).getVelocityX();
                    float vjy = junk.get(j).getVelocityY();

                    float pix = mi * vix;
                    float piy = mi * viy;
                    float pjx = mj * vjx;
                    float pjy = mj * vjy;

                    float keix = 0.5f * mi * vix * vix;

                    float vixf = (mi - mj) / (mi + mj) * vix + (2 * mj / (mi + mj)) * vjx;
                    float viyf = (mi - mj) / (mi + mj) * viy + (2 * mj / (mi + mj)) * vjy;
                    float vjxf = (mj - mi) / (mj + mi) * vjx + (2 * mi / (mj + mi)) * vix;
                    float vjyf = (mj - mi) / (mj + mi) * vjy + (2 * mi / (mj + mi)) * viy;

                    junk.get(i).setVelocity(vixf, viyf);
                    junk.get(j).setVelocity(vjxf, vjyf);
                }
            }
            if (junk.get(i).getHitbox().overlaps(player.getHitbox())) {
                junk.get(i).remove();
                junk.remove(i);
            }
        }
        progress += 3 * delta;
        background1.setPosition(playStage.getWidth() * -0.10f, -playStage.getHeight() * 0.1f);
        playStage.setViewport(new StretchViewport(playStage.getWidth() + 3 * delta, playStage.getHeight() + 2 * delta));
        playStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }

    @Override
    public void resize(int width, int height) {
        playStage.getViewport().update(width, height, true);
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
