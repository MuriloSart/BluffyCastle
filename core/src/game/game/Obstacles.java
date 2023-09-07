package game.game;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import game.collisions.ObstaclesBuilder;

public class Obstacles extends ObstaclesBuilder implements Disposable
{
	float widthGround = 1280;
	float heightGround = 50;
	int xGround = 0;
	int yGround = (int)heightGround; 
	
	public Obstacles(World world) 
	{
		super(world);
		setProperties(xGround, yGround, widthGround, heightGround);
	}
	@Override
	public void dispose() 
	{
		groundBox.dispose();
	}
}
