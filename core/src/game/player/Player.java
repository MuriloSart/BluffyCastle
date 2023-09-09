package game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import game.collisions.BodyBuilder;

public class Player extends BodyBuilder implements Disposable
{
	private Sprite sprite;
	private float density = 0f;
	private float friction = 0.2f;
	private float restitution = 0;
	private float width = 20;
	private float height = 20;
	
	public float x = 0;
	public float y = 80;
	

	private float maxVelocity = 10;
	private float velocity = 0;
	private float aceleracao = 1f;
	
	private float cd = 2;
	private float time = cd;
	
	public Player(World world)
	{
		super(world);	
		setProperties(density, friction, restitution, width, height, x, y, BodyDef.BodyType.DynamicBody);
	}
	
	public void handleMoviment()
	{	
		time += Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.A))
		{
			velocity -= aceleracao;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) 
		{
			velocity += aceleracao;
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE) && time > cd) 
		{
			body.applyForceToCenter( velocity, 300, false);
			
			time -= time;
		}
		
		//Parando o player ao parar de andar
		if(!Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D))
		{
			if(velocity > 0)
				velocity -= aceleracao;
			else if(velocity < 0)
				velocity += aceleracao;
			if(velocity > -1 && velocity < 0)
				velocity = 0;
		}
		
		//Setando o m�ximo de velocidade para o Player
		if(velocity > maxVelocity)
			velocity = maxVelocity;
		if(velocity < -maxVelocity)
			velocity = -maxVelocity;
		
		body.setLinearVelocity( velocity, body.getLinearVelocity().y);
	}
	
	public void draw(SpriteBatch batch)
	{
		sprite.draw(batch);
	}

	@Override
	public void dispose() 
	{
		sprite.getTexture().dispose();
	}

}
