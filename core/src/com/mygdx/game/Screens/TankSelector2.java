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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;

public class TankSelector2 implements Screen {
    private Stage stage;
    private Table table;
    private Skin skin;
    static boolean drawt1 = false;
    boolean drawt2 = false;
    boolean drawt3 = false;
    static Texture ft2;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TankStars game;
    int i = 999;
    private BitmapFont font;
    Texture texture2;
    public TankSelector2(TankStars game){
        this.game = game;
        texture2 = new Texture("tankbg2.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,gamecam);

    }
    final Texture tank1 = new Texture("tank1.png");
    final Texture tank2 = new Texture("tank2.png");
    final Texture tank3 = new Texture("tank3.png");

    @Override
    public void show() {
        ft2 = tank1;
        stage = new Stage();
        table = new Table();
        Texture rb = new Texture("ra.png");
        ImageButton ra = new ImageButton(new TextureRegionDrawable(new TextureRegion(rb)));
        Texture lb = new Texture("la.png");
        ImageButton la = new ImageButton(new TextureRegionDrawable(new TextureRegion(lb)));
        Texture pb = new Texture("play.png");
        ImageButton play = new ImageButton(new TextureRegionDrawable(new TextureRegion(pb)));
        Texture bb = new Texture("back.png");
        ImageButton back = new ImageButton(new TextureRegionDrawable(new TextureRegion(bb)));

        Texture ass = new Texture("select2.png");
        Image select2 = new Image(ass);



        ra.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                i++;
                if(i%3 == 1) {
                    drawt1 = false;
                    drawt2 = true;
                    ft2 = tank2;
                    dispose();
                }else if(i%3 == 2){
                    drawt2 = false;
                    drawt3 = true;
                    ft2 = tank3;
                    dispose();
                }else if(i%3 == 0){
                    drawt3 = false;
                    drawt1 = true;
                    ft2 = tank1;
                    dispose();
                }

            }
        });
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });
        la.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {

                i--;
                i = Math.abs(i);
                if(i%3 == 1) {
                    drawt3 = false;
                    drawt2 = true;
                    ft2 = tank2;
                    dispose();
                }else if(i%3 == 2){
                    drawt1 = false;
                    drawt3 = true;
                    ft2 = tank3;
                    dispose();
                }else if(i%3 == 0){
                    drawt2 = false;
                    drawt1 = true;
                    ft2 = tank1;
                    dispose();
                }
            }
        });
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
//        table.setSize(50,50);
//
//        table.setBounds(0,0,50,50);
//        table.setPosition(0,0);
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TankSelector1(game));

            }
        });




        //table.debug();
        table.add(la).width(60).height(80).pad(250);
        table.add(ra).width(60).height(80).pad(250);
        back.setSize(50,100);
        play.setSize(130,220);
        play.setPosition(250,-57);
        stage.addActor(play);

        select2.setPosition(130,375);
        select2.setSize(400,80);

        stage.addActor(select2);
        stage.addActor(back);
        stage.addActor(table);


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        game.batch.draw(texture2,-640,-360);
        game.batch.draw(tank1,-600,-360);
        if (drawt1){
            game.batch.draw(texture2,-640,-360);
            game.batch.draw(tank1,-620,-360);
        }

        if (drawt2){
            game.batch.draw(texture2,-640,-360);
            game.batch.draw(tank2,-620,-360);
        }

        if (drawt3){
            game.batch.draw(texture2,-640,-360);
            game.batch.draw(tank3,-620,-360);
        }

//        game.batch.draw(l,-600,-60);

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
    public void dispose(){

    }
}
