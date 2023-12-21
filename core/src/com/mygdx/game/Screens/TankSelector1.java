package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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

public class TankSelector1 implements Screen {
    private Stage stage;
    private Table table;
    private Skin skin;
    boolean drawt1 = false;
    boolean drawt2 = false;
    boolean drawt3 = false;
    static Texture ft1;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TankStars game;
    int i = 999;

    Texture texture1;
    public TankSelector1(TankStars game){
        this.game = game;
        texture1 = new Texture("tankbg.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,gamecam);

    }
    final Texture tank1 = new Texture("tank1.png");
    final Texture tank2 = new Texture("tank2.png");
    final Texture tank3 = new Texture("tank3.png");

    @Override
    public void show() {
        ft1 = tank1;
        stage = new Stage();
        table = new Table();

        Texture rb = new Texture("ra.png");
        ImageButton ra = new ImageButton(new TextureRegionDrawable(new TextureRegion(rb)));
        Texture lb = new Texture("la.png");
        ImageButton la = new ImageButton(new TextureRegionDrawable(new TextureRegion(lb)));

        Texture sb = new Texture("select.png");
        ImageButton select = new ImageButton(new TextureRegionDrawable(new TextureRegion(sb)));

        Texture bb = new Texture("back.png");
        ImageButton back = new ImageButton(new TextureRegionDrawable(new TextureRegion(bb)));

        Texture ass = new Texture("select1.png");
        Image select1 = new Image(ass);


        ra.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                i++;
                if(i%3 == 1) {
                    drawt1 = false;
                    drawt2 = true;
                    ft1 = tank2;
                    dispose();
                }else if(i%3 == 2){
                    drawt2 = false;
                    drawt3 = true;
                    ft1 = tank3;
                    dispose();
                }else if(i%3 == 0){
                    drawt3 = false;
                    drawt1 = true;
                    ft1 = tank1;
                    dispose();
                }

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
                    ft1 = tank2;
                    dispose();
                }else if(i%3 == 2){
                    drawt1 = false;
                    drawt3 = true;
                    ft1 = tank3;
                    dispose();
                }else if(i%3 == 0){
                    drawt2 = false;
                    drawt1 = true;
                    ft1 = tank1;
                }
            }
        });

        select.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new TankSelector2(game));
            }
        });

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));

            }
        });
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
//        table.setSize(50,50);
//
//        table.setBounds(0,0,50,50);
//        table.setPosition(0,0);




        //table.debug();
        table.add(la).width(60).height(80).pad(250);
        table.add(ra).width(60).height(80).pad(250);

        select.setSize(130,220);
        select.setPosition(250,-57);
        back.setSize(50,100);

        select1.setPosition(130,375);
        select1.setSize(400,80);

        stage.addActor(select1);
        stage.addActor(select);
        stage.addActor(back);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();

        game.batch.draw(texture1,-640,-360);
        game.batch.draw(tank1,-600,-360);
        if (drawt1){
            game.batch.draw(texture1,-640,-360);
            game.batch.draw(tank1,-660,-360);
        }

        if (drawt2){
            game.batch.draw(texture1,-640,-360);
            game.batch.draw(tank2,-650,-360);
        }

        if (drawt3){
            game.batch.draw(texture1,-640,-360);
            game.batch.draw(tank3,-610,-360);
        }



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
