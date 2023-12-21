package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;

public class HomeScreen implements Screen {
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TankStars game;
    private BitmapFont font;
    Texture texture1;
    public HomeScreen(TankStars game){
        this.game = game;
        texture1 = new Texture("wallpaper.png");


        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,gamecam);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        game.batch.draw(texture1,-640,-360);
        game.font.draw(game.batch, "Click anywhere to continue!", -175, -325);
        game.batch.end();
        if (Gdx.input.isTouched()) {
            game.setScreen(new MenuScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width,height);

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
