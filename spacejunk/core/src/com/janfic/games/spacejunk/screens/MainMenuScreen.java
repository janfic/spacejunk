package com.janfic.games.spacejunk.screens;

import static com.badlogic.gdx.Application.ApplicationType.Desktop;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.janfic.games.spacejunk.SpaceJunkGame;

/**
 *
 * @author Jan Fic
 */
public class MainMenuScreen implements Screen {

    Stage menuStage;

    SpaceJunkGame game;
    Image background;

    public MainMenuScreen(SpaceJunkGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        this.menuStage = new Stage(new FitViewport(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2));

        Gdx.input.setInputProcessor(menuStage);

        Skin skin = new Skin(Gdx.files.internal("spaceSkin.json"));
        Texture b = new Texture(Gdx.files.internal("background.png"));
        b.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        //b.setFilter(Texture.TextureFilter.MipMapNearestNearest, Texture.TextureFilter.MipMapNearestNearest);
        background = new Image(b);
        background.setSize(menuStage.getWidth(), menuStage.getHeight());
        Label title = new Label("spaceJUNK", skin, "title");

        TextButton playButton = new TextButton("PLAY", skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });

        Table table = new Table();
        table.setFillParent(true);

        table.add(title).center().padBottom(25).row();
        table.add(playButton).center().width(100).row();

        if (Gdx.app.getType() == Desktop) {
            TextButton exitButton = new TextButton("EXIT", skin);
            exitButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });
            table.add(exitButton).center().width(100);
        }
        menuStage.addActor(background);
        menuStage.addActor(table);
    }

    @Override
    public void render(float f) {
        menuStage.act(f);
        menuStage.draw();
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
