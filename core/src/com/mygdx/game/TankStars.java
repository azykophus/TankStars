package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Screens.HomeScreen;


public class TankStars extends Game {
	Stage stage;
	public static TankStars ts;
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	public static final int PPM = 32;
	public SpriteBatch batch;
	public BitmapFont font;
	private TankStars(){

	}
	public static TankStars getInstance(){
		if (ts==null){
			ts= new TankStars();
		}
		return ts;
	}


	@Override
	public void create () {
		ScreenViewport viewport = new ScreenViewport();
		stage = new Stage(viewport);
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(2,2);
		setScreen(new HomeScreen(this));

	}

	@Override
	public void render () {
		super.render();

	}

	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
