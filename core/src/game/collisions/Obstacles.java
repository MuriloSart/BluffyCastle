package game.collisions;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import game.game.PlatformBase;

public class Obstacles 
{
	// Instanciando Walls
	public Array<PlatformBase> platformArray = new Array<PlatformBase>();
	private World world;
	
	public Obstacles(World world) 
	{
		this.world = world;
		addPlatform(38,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
		addPlatform(0,0);
	}
	
	public void addPlatform(int xPlatform, int yPlatform)
	{
		PlatformBase platforms = new PlatformBase(world, xPlatform, yPlatform);
		platformArray.add(platforms);
	}
	
	public void draw(SpriteBatch batch)
	{
		for(int i = 0; i < platformArray.size; i++)
		{
			platformArray.get(i).draw(batch);
		}
	}
	
	public void removeWall(int index)
	{
		platformArray.removeIndex(index);
	}
}