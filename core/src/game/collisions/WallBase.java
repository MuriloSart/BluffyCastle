package game.collisions;

import com.badlogic.gdx.physics.box2d.World;

import game.collisions.builders.ObstaclesBuilder;

public class WallBase extends ObstaclesBuilder
{ 
	public int xWall;
	public int yWall;
	public int widthWall;
	public int heightWall;
	
	public WallBase(World world, int xWall, int yWall, int widthWall, int heightWall)
	{
		super(world);
		this.xWall = xWall;
		this.yWall = yWall;
		this.widthWall = widthWall;
		this.heightWall = heightWall;
		setProperties(xWall, yWall, widthWall, heightWall);
	}
}
