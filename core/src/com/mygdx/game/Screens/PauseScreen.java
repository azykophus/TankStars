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

import static com.mygdx.game.Screens.PlayScreen.texture2;

public class PauseScreen implements Screen {
    private OrthographicCamera gamecam;
    private Stage stage;
    private Table table;

    private Viewport gamePort;
    private TankStars game;
    private BitmapFont font;


    public PauseScreen(TankStars game){
        this.game=game;
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,gamecam);
    }
    @Override
    public void show() {
        stage = new Stage();
        table = new Table();
        Texture r = new Texture("resume.png");
        ImageButton resume = new ImageButton(new TextureRegionDrawable(new TextureRegion(r)));
        Texture rt = new Texture("restart.png");
        ImageButton restart = new ImageButton(new TextureRegionDrawable(new TextureRegion(rt)));
        Texture save = new Texture("savegame.png");
        ImageButton sg = new ImageButton(new TextureRegionDrawable(new TextureRegion(save)));
        Texture q = new Texture("quit.png");
        ImageButton quit = new ImageButton(new TextureRegionDrawable(new TextureRegion(q)));

        resume.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });

        restart.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });

        sg.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });
        quit.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new SaveGamesScreen(game));
                game.setScreen(new MenuScreen(game));
            }
        });

        Gdx.input.setInputProcessor(stage);

        resume.setPosition(225,300);
        resume.setSize(200,100);

        restart.setPosition(225,200);
        restart.setSize(200,100);
        sg.setSize(200,100);
        sg.setPosition(225,100);

        quit.setPosition(225,0);
        quit.setSize(200,100);

        table.add(resume).width(200).height(100);
        table.row();
        table.add(restart).width(200).height(100);
        table.row();
        table.add(sg).width(200).height(100);
        table.row();
        table.add(quit).width(200).height(100);
        table.row();
        table.setPosition(320,240);

//        stage.addActor(resume);
//        stage.addActor(restart);
//        stage.addActor(sg);
//        stage.addActor(quit);
        stage.addActor(table);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        game.batch.draw(texture2,-640,-360);
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
