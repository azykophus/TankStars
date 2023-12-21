package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;

public class MenuScreen implements Screen {
    private OrthographicCamera gamecam;
    private Stage stage;
    private Table table;

    private Viewport gamePort;
    private TankStars game;
    private BitmapFont font;
    Texture texture1;
    public MenuScreen(TankStars game){
        this.game = game;
        texture1 = new Texture("tankbg.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,gamecam);
    }
    @Override
    public void show() {
        stage = new Stage();
        table = new Table();
        Texture ng = new Texture("newgame.png");
        ImageButton newgameb = new ImageButton(new TextureRegionDrawable(new TextureRegion(ng)));
        Texture lg = new Texture("continue.png");
        ImageButton continuegameb = new ImageButton(new TextureRegionDrawable(new TextureRegion(lg)));
        Texture ex = new Texture("exit.png");
        ImageButton exitb = new ImageButton(new TextureRegionDrawable(new TextureRegion(ex)));

        newgameb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TankSelector1(game));
            }
        });

        continuegameb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new SaveGamesScreen(game));
                game.setScreen(new SaveGamesScreen(game));
            }
        });

        exitb.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        Gdx.input.setInputProcessor(stage);

        stage.addActor(newgameb);
        stage.addActor(exitb);
        stage.addActor(continuegameb);

        newgameb.setPosition(225,300);
        newgameb.setSize(200,100);
        //newgameb.debug();
        continuegameb.setPosition(225,200);
        continuegameb.setSize(200,100);
        //continuegameb.debug();
        exitb.setPosition(225,100);
        exitb.setSize(200,100);
        //exitb.debug();



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        game.batch.draw(texture1,-640,-360);
        game.batch.end();

        stage.act();
        stage.draw();


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
