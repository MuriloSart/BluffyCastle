package game.game;

import static game.helper.Constants.PPM;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;

import game.collisions.ContactsListener;
import game.player.Player;

public class GameManagers extends Game
{
	public static GameManagers INSTANCE;
	
	//Criando o mundo e o pintando
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public World world;
	public Box2DDebugRenderer debugRenderer;
	
	//Player
	Player player;
		
	//Obstáculos
	Obstacles obstacles;
		
	//Config de world step
	int velocityIterations = 6;
	int positionIterations = 2;
		
	//Setting up DeltaTime
	private float accumulator = 0;
	private float dt;
	private float timeStep = 1/60f;
	private float frameTime;
	
	public GameManagers()
	{
		INSTANCE = this;
	}
	
	@Override
	public void create ()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//setScreen(new GameScreen(camera));
		
		batch = new SpriteBatch();
		
		world = new World(new Vector2(0, -9.81f), false);
		world.setContactListener(new ContactsListener());
		debugRenderer = new Box2DDebugRenderer();
		obstacles = new Obstacles(world);
		player = new Player(world);
	
	}
	
	@Override
	public void render ()
	{
		ScreenUtils.clear(0, 0, 0, 0);
		update();	
		
		batch.begin();
		//Area para desenhar na Janela
		debugRenderer.render(world, camera.combined.scl(PPM));
		batch.end();
		
		player.handleMoviment();
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
	
	public void cameraUpdate()
	{
		camera.position.x = player.body.getPosition().x * PPM;
		camera.position.y = player.body.getPosition().y * PPM;
		
		camera.update();
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