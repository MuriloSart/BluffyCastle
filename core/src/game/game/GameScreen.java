package game.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import game.helper.Constants;

public class GameScreen extends ScreenAdapter
{
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private World world;
	private Box2DDebugRenderer box2DDebugRenderer;
	
	//Setting up DeltaTime
	private float accumulator = 0;
	private float dt;
	private float timeStep = 1/60f;
	private float frameTime;
	
	//Config de world step
	int velocityIterations = 6;
	int positionIterations = 2;
	
	public GameScreen(OrthographicCamera camera)
	{
		this.camera = camera;
		this.batch = new SpriteBatch();
		this.world = new World( new Vector2(0,0), false);
		this.box2DDebugRenderer = new Box2DDebugRenderer();
	}
	
	private void update()
	{
		doPhysicsStep();
		cameraUpdate();
		
		batch.setProjectionMatrix(camera.combined);
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		{
			Gdx.app.exit();
		}
	}
	
	private void cameraUpdate()
	{
		camera.position.set(new Vector3(0,0,0));
		camera.update();
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		//render objects
		
		batch.end();
		box2DDebugRenderer.render(world, camera.combined.scl(Constants.PPM));
		this.update();
	}
	
	@Override
	public void dispose () 
	{
		batch.dispose();
		world.dispose();
	}
	
	private void doPhysicsStep() 
	{	
		dt = Gdx.graphics.getDeltaTime();
	    frameTime = Math.min(dt, 0.25f);
	    accumulator += frameTime;
	    
	    if(accumulator >= timeStep)
	    {
	    	world.step(timeStep, velocityIterations, positionIterations);
	        accumulator -= timeStep;
	    }
	}
}
