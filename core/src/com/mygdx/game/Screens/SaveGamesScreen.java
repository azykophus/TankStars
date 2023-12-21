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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;

import static com.mygdx.game.Screens.PlayScreen.texture2;
import static com.mygdx.game.Screens.TankSelector1.ft1;
import static com.mygdx.game.Screens.TankSelector2.ft2;
public class SaveGamesScreen implements Screen {
    private OrthographicCamera gamecam;
    private Stage stage;
    private Viewport gamePort;
    private TankStars game;
    private BitmapFont font;
    Texture texture1;
    public SaveGamesScreen(TankStars game){
        this.game = game;
        texture1 = new Texture("tankbg.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,gamecam);
    }
    @Override
    public void show() {
        ft1 = new Texture("tank1.png");
        ft2 = new Texture("tank2.png");
        texture2 = new Texture("gamebbg1.png");
        stage = new Stage();

        Texture ng = new Texture("save1.png");
        ImageButton save1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(ng)));
        Texture lg = new Texture("save2.png");
        ImageButton save2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(lg)));
        Texture ex = new Texture("save3.png");
        ImageButton save3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(ex)));
        Texture yu = new Texture("save4.png");
        ImageButton save4 = new ImageButton(new TextureRegionDrawable(new TextureRegion(yu)));
        Texture po = new Texture("save5.png");
        ImageButton save5 = new ImageButton(new TextureRegionDrawable(new TextureRegion(po)));
        Texture bb = new Texture("back.png");
        ImageButton back = new ImageButton(new TextureRegionDrawable(new TextureRegion(bb)));

        save1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });

        save2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new SaveGamesScreen(game));
                game.setScreen(new PlayScreen(game));
            }
        });

        save3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });

        save4.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });

        save5.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new PlayScreen(game));
            }
        });
        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MenuScreen(game));

            }
        });

        Gdx.input.setInputProcessor(stage);

        stage.addActor(save1);
        stage.addActor(save2);
        stage.addActor(save3);
        stage.addActor(save4);
        stage.addActor(save5);
        stage.addActor(back);

        save1.setPosition(225,340);
        save1.setSize(200,100);

        save2.setPosition(225,260);
        save2.setSize(200,100);

        save3.setPosition(225,180);
        save3.setSize(200,100);

        save4.setPosition(225,100);
        save4.setSize(200,100);

        save5.setPosition(225,20);
        save5.setSize(200,100);

        back.setSize(50,100);



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
