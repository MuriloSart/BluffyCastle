package game.game;

import static game.helper.Constants.PPM;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import game.collisions.ContactsListener;
import game.collisions.EndLine;
import game.collisions.Obstacles;
import game.collisions.Walls;
import game.player.Player;

public class GameManagers extends Game
{
	public static GameManagers INSTANCE;
	
	 //Criando o mundo e o pintando
	public OrthographicCamera camera;
	public SpriteBatch batch;
	public World world;
	public Box2DDebugRenderer debugRenderer;
	private final float SCALE = 2.0f;
	
	 //Player
	Player player;
	
	//Paredes Laterais
	Walls walls;
	
	//Obstáculos
	SpawnPlatforms spawnPlatforms;
	Obstacles obstacles;
	EndLine endLine;
		
	 //Config de world step
	int velocityIterations = 6;
	int positionIterations = 2;
		
	 //Setting up DeltaTime
	private float accumulator = 0;
	private float dt;
	private float timeStep = 1/60f;
	private float frameTime;
	
	 //time to spawn enemies
	public long cd = 3*1000;
	
	public GameManagers()
	{
		INSTANCE = this;
	}
	
	@Override
	public void create ()
	{
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth() * SCALE, Gdx.graphics.getHeight() * SCALE);
		
		world = new World(new Vector2(0, -30f), false);
		batch = new SpriteBatch();
		obstacles = new Obstacles(world);
		walls = new Walls(world);
		player = new Player(world);
		endLine = new EndLine(world);
		debugRenderer = new Box2DDebugRenderer();
		spawnPlatforms = new SpawnPlatforms(obstacles);
		
		world.setContactListener(new ContactsListener(player, spawnPlatforms, obstacles));
	}
	
	@Override
	public void render()
	{
		update();
		player.handleInputs();
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
		obstacles.draw(batch);
		player.draw(batch);
		batch.end();
		debugRenderer.render(world, camera.combined);
		HandlePlatforms();
	}
	
	private void HandlePlatforms()
	{
		for(int i = 0; i < spawnPlatforms.platforms.platformArray.size; i++)
		{
			if(obstacles.platformArray.get(i).body.getPosition().y <= endLine.body.getPosition().y)
			{
				spawnPlatforms.RespawningPlatforms(i);
			}
		}
	}
	
	private void update()
	{
		doPhysicsStep();
		cameraUpdate();
		
		batch.setProjectionMatrix(camera.combined.scl(PPM));
		
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
		{
			Gdx.app.exit();
		}
	}
	
	public void cameraUpdate()
	{
		camera.position.x = 0;
		camera.position.y = 0;
		camera.update();
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
	
	@Override
	public void dispose()
	{
		batch.dispose();
		debugRenderer.dispose();
		world.dispose();
	}
}