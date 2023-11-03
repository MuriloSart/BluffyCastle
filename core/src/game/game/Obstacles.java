package game.game;

import static game.helper.Constants.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import game.collisions.ObstaclesBuilder;

public class Obstacles extends ObstaclesBuilder implements Disposable
{
	float widthGround;
	float heightGround;
	int xGround = 0;
	int yGround = 0; 
	
	//====== Image ======//
	private Texture texture;
		
	public Obstacles(World world) 
	{
		super(world);
		texture = new Texture("platform.png");
		widthGround = texture.getWidth();
		heightGround = texture.getHeight();
		setProperties(xGround, yGround, widthGround, heightGround/2);
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, xGround - (widthGround/PPM), yGround - ((heightGround)/PPM) + (8/PPM) , widthGround / PPM*2, heightGround / PPM*2);
	}
	
	@Override
	public void dispose() 
	{
		groundBox.dispose();
	}
}
