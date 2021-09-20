package com.janfic.games.spacejunk.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.janfic.games.spacejunk.SpaceJunkGame;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        GwtApplicationConfiguration config = new GwtApplicationConfiguration(1920 / 2, 640);
        config.stencil = false;
        return config;
        // Fixed size application:
        //return new GwtApplicationConfiguration(480, 320);
    }

    @Override
    public ApplicationListener createApplicationListener() {
        return new SpaceJunkGame();
    }
}
