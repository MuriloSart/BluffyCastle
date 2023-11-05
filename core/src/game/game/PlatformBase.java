package game.game;

import static game.helper.Constants.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import game.collisions.builders.ObstaclesBuilder;

public class PlatformBase extends ObstaclesBuilder implements Disposable
{
	float widthGround;
	float heightGround;
	int xGround = 0;
	int yGround = 0; 
	
	//====== Image ======//
	private Texture texture;
		
	public PlatformBase(World world, int xGround, int yGround) 
	{
		super(world);
		texture = new Texture("platform.png");
		widthGround = texture.getWidth();
		heightGround = texture.getHeight();
		this.xGround = xGround;
		this.yGround = yGround;
		setProperties(xGround, yGround, widthGround, heightGround/2);
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, ((float)groundBody.getPosition().x) - (widthGround/PPM), ((float)groundBody.getPosition().y) - ((heightGround)/PPM) + (8/PPM) , widthGround / PPM*2, heightGround / PPM*2);
	}
	
	@Override
	public void dispose() 
	{
		groundBox.dispose();
	}
}
