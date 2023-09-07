package game.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import game.collisions.BodyBuilder;

public class Enemies extends BodyBuilder implements Disposable
{

	private Sprite sprite;
	
	private float density = 0f;
	private float friction = 0.2f;
	private float restitution = 0;
	private float width = 20;
	private float height = 20;
	
	public float x = 0;
	public float y = 80;
	
	private float cd = 2;
	private float time = cd;
	
	public Enemies(World world) 
	{
		super(world);
	}

	@Override
	public void dispose() 
	{
		sprite.getTexture().dispose();
	}

}
