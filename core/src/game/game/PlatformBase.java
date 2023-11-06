package game.game;

import static game.helper.Constants.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import game.collisions.builders.BodyBuilder;

public class PlatformBase extends BodyBuilder
{
	float widthGround;
	float heightGround;
	int xGround;
	int yGround; 
	
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
		setProperties(0, 0, 0, widthGround, heightGround - 15, xGround, yGround, BodyDef.BodyType.KinematicBody, BIT_PLATFORM, (short) 0, "platform");
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(texture, ((float)body.getPosition().x) - (widthGround/PPM), ((float)body.getPosition().y) - ((heightGround)/PPM) + (8/PPM) , widthGround / PPM*2, heightGround / PPM*2);
	}
}
