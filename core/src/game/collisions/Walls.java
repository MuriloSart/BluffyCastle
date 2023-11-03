package game.collisions;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;



public class Walls 
{
	// Instanciando Walls
	public Array<WallBase> wallArray = new Array<WallBase>();
	private World world;
	
	public Walls(World world) 
	{
		this.world = world;
		addWall(0, 24, 1300, 50);
		addWall(41, 0, 50, 1300);
		addWall(-41, 0, 50, 1300);
	}
	
	public void addWall(int xWall, int yWall, int widthWall, int heightWall)
	{
		WallBase walls = new WallBase(world, xWall, yWall, widthWall, heightWall);
		wallArray.add(walls);
	}
	
	public void removeWall(int index)
	{
		wallArray.removeIndex(index);
	}
}
