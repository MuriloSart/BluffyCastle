package game.game;

import static game.helper.Constants.PPM;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import game.collisions.ObstaclesBuilder;

public class Obstacles extends ObstaclesBuilder implements Disposable
{
	float widthGround = 1280;
	float heightGround = 50;
	int xGround = 0;
	int yGround = (int)heightGround; 
	
	//====== Sprite ======//
	private Texture texture;
	private Sprite sprite;
		
	public Obstacles(World world) 
	{
		super(world);
		setProperties(xGround, yGround, widthGround, heightGround);
		texture = new Texture("tijolo.png");
		sprite = new Sprite(texture);
		sprite.setSize( texture.getWidth(), texture.getHeight() / 4);
		sprite.setPosition(xGround - (widthGround / 2), yGround - (heightGround / 2));
	}
	
	public void draw(SpriteBatch batch)
	{
		sprite.draw(batch);
		//batch.draw(texture, xGround - (texture.getWidth() / 2), yGround - (texture.getHeight() / 2));
	}
	
	@Override
	public void dispose() 
	{
		groundBox.dispose();
	}
}
