package com.janfic.games.spacejunk.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.janfic.games.spacejunk.SpaceJunkGame;
import com.janfic.games.spacejunk.model.Junk;
import com.janfic.games.spacejunk.model.Player;
import com.janfic.games.spacejunk.model.junk.Apple;
import com.janfic.games.spacejunk.model.junk.Asteroid;
import com.janfic.games.spacejunk.model.junk.Satallite;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jan Fic
 */
public class GameScreen implements Screen {

    Stage playStage, uiStage;
    Player player;

    Label scoreLabel;

    SpaceJunkGame game;
    List<Junk> junk;

    Image background;
    Image background1;

    Image leftWarning, rightWarning;

    float score;

    boolean gameOver;

    public GameScreen(SpaceJunkGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.playStage = new Stage(new FitViewport(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 4));
        this.uiStage = new Stage(new FitViewport(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));
        Skin skin = new Skin(Gdx.files.internal("spaceSkin.json"));
        Table uiTable = new Table();
        uiStage.addActor(uiTable);
        uiTable.setFillParent(true);
        uiTable.top().defaults().top();

        scoreLabel = new Label("score  " + (int) score, skin, "title");
        uiTable.add(scoreLabel).expandX().left();

        this.player = new Player();
        junk = new ArrayList<>();
        background1 = new Image(new Texture("background_1.png"));
        leftWarning = new Image(new Texture("warning.png"));
        rightWarning = new Image(new Texture("warning.png"));
        leftWarning.addAction(Actions.fadeOut(0));
        rightWarning.addAction(Actions.fadeOut(0));
        background1.setScale(2);
        Texture b = new Texture(Gdx.files.internal("background.png"));
        b.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        background = new Image(b);

        Gdx.input.setInputProcessor(playStage);
        playStage.addActor(background);
        playStage.addActor(background1);

        this.playStage.addActor(player);
        this.uiStage.addActor(leftWarning);
        this.uiStage.addActor(rightWarning);

        player.setPosition(playStage.getWidth() / 4, playStage.getHeight() / 4);

        Junk j = new Apple();
        j.setPosition(playStage.getWidth() + 100, playStage.getHeight() / 2);
        j.setVelocity(-40, (float) (Math.random() * 20) + -10);
        junk.add(j);
        playStage.addActor(j);
        rightWarning.addAction(Actions.sequence(Actions.fadeIn(3), Actions.delay(2), Actions.fadeOut(1)));

        uiStage.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });
    }

    @Override
    public void render(float delta) {
        background.setSize(playStage.getWidth(), playStage.getHeight());
        scoreLabel.setText("score   " + (int) (score * 10));
        leftWarning.setPosition(10, uiStage.getHeight() / 2 - leftWarning.getImageHeight() / 2);
        rightWarning.setPosition(uiStage.getWidth() - rightWarning.getImageWidth() - 10, uiStage.getHeight() / 2 - leftWarning.getImageHeight() / 2);

        background1.setPosition(playStage.getWidth() * -0.10f, -playStage.getHeight() * 0.1f);
        playStage.setViewport(new FitViewport(playStage.getWidth() + 3 * delta, playStage.getHeight() + 2 * delta));
        playStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        if (!gameOver) {
            playStage.act(delta);
            spawnJunk();
            collisions();
            score += delta;
        }
        playStage.draw();
        uiStage.act(delta);
        uiStage.draw();

    }

    public void collisions() {
        for (int i = 0; i < junk.size(); i++) {
            for (int j = i + 1; j < junk.size(); j++) {
                if (Intersector.intersectPolygons(junk.get(i).getHitbox(), junk.get(j).getHitbox(), null)) {

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
                    junk.get(i).clearActions();
                    junk.get(j).clearActions();
                    junk.get(i).reverseRotationDirection();
                    junk.get(j).reverseRotationDirection();
                    junk.get(i).addAction(Actions.forever(Actions.rotateBy(360 * junk.get(i).getrRotationDirection(), (float) (Math.random() * 2) + 2)));
                    junk.get(j).addAction(Actions.forever(Actions.rotateBy(360 * junk.get(j).getrRotationDirection(), (float) (Math.random() * 2) + 2)));
                }
            }
            if (Intersector.intersectPolygons(junk.get(i).getHitbox(), player.getHitbox(), null)) {
                gameOver = true;
                Gdx.input.setInputProcessor(uiStage);
                uiStage.addActor(scoreLabel);
                player.setColor(Color.RED);
                scoreLabel.addAction(Actions.moveTo(uiStage.getWidth() / 2 - scoreLabel.getWidth() / 2, uiStage.getHeight() / 2 - scoreLabel.getHeight() / 2, 2));
            }
        }
    }

    public void spawnJunk() {
        if (((int) score) % 30 == 0) {
            score += 1;
            if (score >= 90 && score <= 150) {
                Junk j = new Satallite();
                j.setPosition(-200, playStage.getHeight() / 2 + 90);
                j.setVelocity(60, (float) (Math.random() * 60) + -30);
                junk.add(j);
                playStage.addActor(j);
                leftWarning.addAction(Actions.sequence(Actions.fadeIn(2), Actions.delay(2), Actions.fadeOut(1)));
            }
            if (score >= 60) {
                Junk j = new Asteroid(true);
                j.setPosition(playStage.getWidth() + 200, playStage.getHeight() / 2 + 60);
                j.setVelocity(-40, (float) (Math.random() * 20) + -10);
                junk.add(j);
                playStage.addActor(j);
                rightWarning.addAction(Actions.sequence(Actions.fadeIn(2), Actions.delay(2), Actions.fadeOut(1)));
            }
            if (score >= 30 && score <= 90) {
                Junk j = new Asteroid(false);
                j.setPosition(playStage.getWidth() + 200, playStage.getHeight() / 2 - 40);
                j.setVelocity(-40, (float) (Math.random() * 20) + -10);
                junk.add(j);
                playStage.addActor(j);
                rightWarning.addAction(Actions.sequence(Actions.fadeIn(2), Actions.delay(2), Actions.fadeOut(1)));
            }
        }
        if (((int) score) == 15) {
            score += 1;
            Junk j = new Asteroid(false);
            j.setPosition(playStage.getWidth() + 200, playStage.getHeight() / 2 - 40);
            j.setVelocity(-40, (float) (Math.random() * 20) + -10);
            junk.add(j);
            playStage.addActor(j);
            rightWarning.addAction(Actions.sequence(Actions.fadeIn(2), Actions.delay(2), Actions.fadeOut(1)));
        }
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
