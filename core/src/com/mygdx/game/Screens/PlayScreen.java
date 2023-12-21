package com.mygdx.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.TankStars;
import jdk.tools.jlink.internal.Platform;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import static com.mygdx.game.Screens.TankSelector1.ft1;
import static com.mygdx.game.Screens.TankSelector2.ft2;
import static java.lang.Math.pow;

;

class bullet{

}
class PlayScreen implements Screen {
    boolean f = false;
    private OrthographicCamera gamecam;
    public int a = 0;
    private int sin;
    private int cos;
    boolean flag = false;
    boolean flag2 = false;
    private Body p1;
    private Body p2;
    private Body pl1;
    private Body line;
    private Body bullet1;
    private Body bullet2;
    private World world;
    private boolean turn = false;
    private Platform platform;
    private Box2DDebugRenderer debugRenderer;
    private final float TIMESTEP = 1/60f;
    private final float VELOCITY = 8,POSITION = 3;

    private Stage stage;
    private Table table;

    private Viewport gamePort;
    private TankStars game;

    private int shape_length = 0;
    private int tank_health;

    public int getTank_health() {
        return tank_health;
    }

    public void setTank_health(int tank_health) {
        this.tank_health = tank_health;
    }

    private int shape_length2 = 0;
    private double health_length1 = 270;
    private double health_length2 = 270;
    private float total_time = 0f;

    private BitmapFont font;
    private ShapeRenderer shape;

    Texture texture1;
    static Texture texture2;
    public PlayScreen(TankStars game){
        this.game = game;
        Random rand = new Random();
        int opt = rand.nextInt(0,3);
        ArrayList<Texture> arr = new ArrayList<>();
        arr.add(new Texture("gamebbg1.png"));
        arr.add(new Texture("gamebbg2.png"));
        arr.add(new Texture("gamebbg3.png"));

        Iterator<Texture> iter = arr.iterator();

        while (iter.hasNext()){
            texture2 = iter.next();
            break;
        }

        if (opt == 0){
            texture1 = new Texture("gamebg1.png");

        }
        if (opt == 1){
            texture1 = new Texture("gamebg2.png");

        }
        if (opt == 2){
            texture1 = new Texture("gamebg3.png");

        }
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(TankStars.V_WIDTH,TankStars.V_HEIGHT,gamecam);
    }

    @Override
    public void show() {

        stage = new Stage();
        table = new Table();
        shape = new ShapeRenderer();

        world = new World(new Vector2(0, -20f), true);
        debugRenderer = new Box2DDebugRenderer();
        Gdx.input.setInputProcessor(new inputcontroller() {
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.ESCAPE)
                    ((Game) Gdx.app.getApplicationListener()).setScreen(new PauseScreen(game));
                return true;
            }
        });

        p1 = createPlayer(-440,185);
        p2 = createPlayer(365,-85);

        pl1 = createPlatform();
//        line = createLine(-440,220);

//        p1.applyLinearImpulse(2.0f,1.0f,0,0,true);




        Texture ho1 = new Texture("healthoutline.png");
        Texture ho2 = new Texture("healthoutline.png");
//        Texture h1 = new Texture("health.png");
//        Texture h2 = new Texture("health.png");
        Texture s1 = new Texture("shield.png");
        Texture s2 = new Texture("shield.png");
        Texture vs = new Texture("vsicon.png");

        Texture po = new Texture("healthoutline.png");

        Image healthoutline1 = new Image(ho1);
        Image healthoutline2 = new Image(ho2);
//        Image health1 = new Image(h1);
//        Image health2 = new Image(h2);
        Image shield1 = new Image(s1);
        Image shield2 = new Image(s2);
        Image vsicon = new Image(vs);

        Image poweroutline = new Image(po);

        Texture p = new Texture("pause.png");

        ImageButton pause = new ImageButton(new TextureRegionDrawable(new TextureRegion(p)));
        pause.setSize(40, 85);


        pause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //game.setScreen(new SaveGamesScreen(game));
                game.setScreen(new PauseScreen(game));
            }
        });

        Gdx.input.setInputProcessor(stage);


        pause.setPosition(2, 398);

        stage.addActor(healthoutline1);
        healthoutline1.setPosition(145, 435);
        healthoutline1.setSize(150, 35);

        stage.addActor(healthoutline2);
        healthoutline2.setPosition(344, 435);
        healthoutline2.setSize(150, 35);

//        stage.addActor(health1);
//        health1.setPosition(145, 435);
//        health1.setSize(150, 35);
//
//        stage.addActor(health2);
//        health2.setPosition(344, 435);
//        health2.setSize(150, 35);


        stage.addActor(shield1);
        shield1.setPosition(135, 430);
        shield1.setSize(30, 45);

        stage.addActor(shield2);
        shield2.setPosition(480, 430);
        shield2.setSize(30, 45);

        stage.addActor(vsicon);
        vsicon.setPosition(300, 430);
        vsicon.setSize(40, 40);

        stage.addActor(poweroutline);
        poweroutline.setPosition(187,19);
        poweroutline.setSize(266, 50);


//        table.add((CharSequence) s1);
        stage.addActor(table);
        stage.addActor(pause);


        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if ((contact.getFixtureA().getBody() == p2 && contact.getFixtureB().getBody() == bullet1)) {

                    f = true;
                    Health2(56, 300, 67.5);


                } else if ((contact.getFixtureA().getBody() == pl1 && contact.getFixtureB().getBody() == bullet1)) {

                    f = true;
                    Health2(56, 300, damage(bullet1.getPosition().x, bullet1.getPosition().y, p2.getPosition().x, p2.getPosition().y, 67.5));


                } else if ((contact.getFixtureA().getBody() == p1 && contact.getFixtureB().getBody() == bullet2)) {
                    f = true;
                    Health1(-330, 300, damage(bullet1.getPosition().x, bullet1.getPosition().y, p2.getPosition().x, p2.getPosition().y, 67.5));
                } else if ((contact.getFixtureA().getBody() == pl1 && contact.getFixtureB().getBody() == bullet2)) {
                    f = true;
                    Health1(-330, 300, damage(bullet1.getPosition().x, bullet1.getPosition().y, p2.getPosition().x, p2.getPosition().y, 67.5));
                }
            }
//                }if((contact.getFixtureA().getBody() == p1 && contact.getFixtureB().getBody() == bullet)) {
//                    f  = true;
//                    Health1(56,300,50);
//                }
//            }

            @Override
            public void endContact(Contact contact) {
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }
        });

    }

    public void inputupdate1(float delta){
        int hf = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            hf -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            hf += 1;
        }
        p1.setLinearVelocity(hf*40,p1.getLinearVelocity().y);
    }
    public void inputupdate2(float delta){
        int hf = 0;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            hf -= 1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            hf += 1;
        }
        p2.setLinearVelocity(hf*40,p2.getLinearVelocity().y);
    }


    public void impulse1(float delta, int power, double sin){
        //int hf = 0;
        double cos = pow((1 - pow(sin,2)),0.5);

        float f = 50000.0f*power;
        bullet1.applyLinearImpulse((float)(f*cos), (float) (f*sin),bullet1.getPosition().x,bullet1.getPosition().y,true);
    }
    public void impulse2(float delta, int power, double sin){

        double cos = pow((1 - pow(sin,2)),0.5);
        float f = 50000.0f*power;
        bullet2.applyLinearImpulse(-(float)(f*cos), (float) (f*sin),bullet2.getPosition().x,bullet2.getPosition().y,true);
    }



    public Body createPlayer(int x, int y){
        Body pBody;
        BodyDef b1 = new BodyDef();
        b1.type = BodyDef.BodyType.DynamicBody;
        b1.position.set(x,y);
        b1.fixedRotation = false;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(25,17);

//        CircleShape shape = new CircleShape();
//        shape.setRadius(25.0f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 10000;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;
        fixtureDef.shape = shape;
        pBody = world.createBody(b1);
        pBody.createFixture(fixtureDef);
        shape.dispose();

        return pBody;

    }
//    public Body createLine(int x, int y){
//        Body pBody;
//        BodyDef b1 = new BodyDef();
//        b1.type = BodyDef.BodyType.StaticBody;
//        b1.position.set(x,y);
//        b1.fixedRotation = false;
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(250,1);
//
////        CircleShape shape = new CircleShape();
////        shape.setRadius(25.0f);
//
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        pBody = world.createBody(b1);
//        pBody.createFixture(fixtureDef);
//        shape.dispose();
//
//        return pBody;
//
//    }
    public Body createBullet(int x,int y){
        Body pBody;
        BodyDef b1 = new BodyDef();
        b1.type = BodyDef.BodyType.DynamicBody;
        b1.position.set(x,y);
        b1.fixedRotation = false;

        CircleShape shape = new CircleShape();
        shape.setRadius(7.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;
        fixtureDef.shape = shape;
        pBody = world.createBody(b1);
        pBody.createFixture(fixtureDef);
        shape.dispose();

        return pBody;
    }
    public Body createBullet2(int x,int y){
        Body pBody;
        BodyDef b1 = new BodyDef();
        b1.type = BodyDef.BodyType.DynamicBody;
        b1.position.set(x,y);
        b1.fixedRotation = false;

        CircleShape shape = new CircleShape();
        shape.setRadius(7.5f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 2.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;
        fixtureDef.shape = shape;
        pBody = world.createBody(b1);
        pBody.createFixture(fixtureDef);
        shape.dispose();

        return pBody;
    }
    public static double damage(double x1, double y1, double x2, double y2, double factor){
        double dist = pow((pow(x2-x1,2)+pow(y2-y1,2)),0.5);
        if(dist<=1){
            return factor;
        }
        return factor/pow(dist,0.5);
    }

    public Body createPlatform(){
        Body pBody;
        BodyDef b1 = new BodyDef();
        b1.type = BodyDef.BodyType.StaticBody;
        b1.position.set(0,0);

//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(-50/PPM,10/PPM);
        ChainShape shape = new ChainShape();
        shape.createChain(new Vector2[]{new Vector2(-640,70),new Vector2(-590,80),new Vector2(-520,139),new Vector2(-365,139),new Vector2(-345,126),new Vector2(-220,-50),new Vector2(-194,-66),new Vector2(125,-66),new Vector2(230,-145),new Vector2(575,-140),new Vector2(+640,-25)});
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0;
        fixtureDef.shape = shape;
        pBody = world.createBody(b1);
        pBody.createFixture(fixtureDef);
        shape.dispose();
        return pBody;

    }
    public double angleline1(float deltaTime,int x1,int y1) {

            shape.begin(ShapeType.Line);
            shape.setColor(Color.YELLOW);
            int x2 = x1+500-a;
            int y2 = y1-a;
            shape.line(x1, y1, x2,y2);
            if(!Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
                if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    a += 20;
                } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    a -= 20;
                }
            }
            shape.end();
            double sin = ((y2-y1)/pow((pow(y1-y2,2)+pow(x1-x2,2)),0.5));
            return sin;


    }
    public double angleline2(float deltaTime,int x1,int y1) {

        shape.begin(ShapeType.Line);
        shape.setColor(Color.YELLOW);
        int x2 = x1-500+a;
        int y2 = y1+a;
        shape.line(x1, y1, x2,y2);
        if(!Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                a -= 20;
            } else if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                a += 20;
            }
        }
        shape.end();
        double sin = ((y2-y1)/pow((pow(y1-y2,2)+pow(x1-x2,2)),0.5));
        return sin;


    }
    public void launch(float deltaTime){
        if(Gdx.input.isKeyPressed(Input.Keys.Q)){
            if (!turn){
                double sin = angleline1(deltaTime,(int) p1.getPosition().x,(int)p1.getPosition().y);
                if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
                    System.out.println("test");
                    bullet1 = createBullet(((int) p1.getPosition().x+5),((int)p1.getPosition().y+5));
                    impulse1(deltaTime,shape_length/25,sin);
                }
                if(f) {
                    world.destroyBody(bullet1);
                    turn = true;
                    flag2 = false;
                    f = false;
                }
            }
            else {
                double sin2 = angleline2(deltaTime,(int) p2.getPosition().x,(int)p2.getPosition().y);
                if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
                    bullet2 = createBullet(((int) p2.getPosition().x-10),((int)p2.getPosition().y+10));
                    impulse2(deltaTime,shape_length/25,sin2);
                    }
                if(f) {
                    world.destroyBody(bullet2);
                    turn = false;
                    flag2 = false;
                    f = false;
                }
            }
            f = false;
        }
    }

    public void powerbar(float deltaTime) {
        if (!flag2) {
            if (!Gdx.input.isKeyJustPressed((Input.Keys.SPACE))) {
                shape.setProjectionMatrix(gamecam.combined);
                shape.begin(ShapeType.Filled);
                total_time += deltaTime;
                if (total_time >= 0.5f) {
                    if (!flag) {
                        shape_length += 25;
                        total_time = 0.f;
                    } else {
                        shape_length -= 25;
                        total_time = 0.f;
                    }
                    if (shape_length == 0) {
                        flag = false;
                    } else if (shape_length == 500) {
                        flag = true;
                    }
                }
                int z = (255 * shape_length / 500);

                shape.setColor(new Color(z, 255, 255, 1));
                shape.rect(-250, -320, shape_length, 50);

                shape.end();
            } else {
                flag2 = true;
            }
        }
    }

    public void Health1(int x, int y,double damage) {
        shape.setProjectionMatrix(gamecam.combined);
        shape.begin(ShapeType.Filled);


            if (health_length1 != 0) {
                if (health_length1 - damage > 0) {
                    health_length1 = health_length1 - damage;
                } else {
                    health_length1 = 0;
                }
            }

        shape.setColor(Color.PURPLE);
        shape.rect(x, y, (float) health_length1, 40);
        shape.end();
    }

    public void Health2(int x, int y,double damage) {
        shape.setProjectionMatrix(gamecam.combined);
        shape.begin(ShapeType.Filled);


            if (health_length2 != 0) {
                if (health_length2 - damage > 0) {
                    health_length2 = health_length2 - damage;
                } else {
                    health_length2 = 0;
                }
            }

        shape.setColor(Color.PURPLE);
        shape.rect(x, y, (float) health_length2, 40);
        shape.end();
    }

//    public void player2Health(int damage) {
//        shape.setProjectionMatrix(gamecam.combined);
//        shape.begin(ShapeType.Filled);
////        int z = (255 * shape_length / 500);
//
//        if (health_length!=0) {
//            health_length = health_length - damage;
//        }
//        shape.setColor(new Color(0, 142, 204, 1));
//        shape.rect(56, 300, health_length, 40);
//            shape.rect(-330, 300, health_length, 40);
//        shape.end();
//    }


    public void update(float deltaTime){
        setTank_health(getTank_health());
        world.step(TIMESTEP, (int) VELOCITY, (int) POSITION);
        inputupdate1(deltaTime);
        inputupdate2(deltaTime);
        powerbar(deltaTime);

//        double sin2 = angleline2(deltaTime,(int) p2.getPosition().x,(int)p2.getPosition().y);
        launch(deltaTime);



        //player1Health(50);
        //player2Health(50);
        Health1(-330,300,0);
        Health2(56,300,0);
    }
    @Override
    public void render(float deltaTime) {

        Sprite ftank1 = new Sprite(ft1);
        Sprite ftank2 = new Sprite(ft2);

        ftank2.flip(true,false);

        Texture terrain = new Texture("terrain.png");
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.setProjectionMatrix(gamecam.combined);


        Vector2 pos1 = p1.getPosition();
        Vector2 pos2 = p2.getPosition();


        game.batch.begin();
        game.batch.draw(texture1,-640,-360);
        game.batch.draw(ftank1,pos1.x-135,pos1.y-54,260,150);
        game.batch.draw(ftank2,pos2.x-130,pos2.y-54,260,150);

        game.batch.draw(terrain,-640,-360);
        game.batch.end();

        stage.act();
        stage.draw();
        debugRenderer.render(world, gamecam.combined);
        update(Gdx.graphics.getDeltaTime());
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
        dispose();

    }

    @Override
    public void dispose() {

    }
}
