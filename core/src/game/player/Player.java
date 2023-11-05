package game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;

import game.collisions.builders.BodyBuilder;

import static game.helper.Constants.*;

public class Player extends BodyBuilder implements Disposable
{
	private float density = 0f;
	private float friction = 0f;
	private float restitution = 0;
	private float width = 32;
	private float height = 64;
	
	public float x = 0;
	public float y = 10;
	
	//====== Movimento ======//
	// Velocidade do player
	private float maxVelocity = 10;
	private float aceleracao = 1f;
	
	public float velocity = 0;
	public boolean canRun = false;
	public boolean canWalk = true;
	
	//Lidando com o Pulo
	public boolean canJump = false;
	
	//====== Sprite Player ======//
	private Sprite sprite;
	private Texture texture;
	private boolean inverted = false;
	
	public Player(World world)
	{
		super(world);
		setProperties(density, friction, restitution, width, height, x, y, BodyDef.BodyType.DynamicBody, BIT_PLAYER, (short) 0, "player");	
		texture = new Texture("player.png");
		sprite = new Sprite(texture);
		sprite.setSize(width * 2 /PPM , height * 2 /PPM);
	}
	
	public void handleInputs()
	{
		//========== Movimentando o Player ==========//
		if (Gdx.input.isKeyPressed(Keys.A)  && canWalk)
		{
			velocity -= aceleracao;
			if(!inverted)
			{
				sprite.flip(true, false);
				inverted = true;
			}	
		}
		if (Gdx.input.isKeyPressed(Keys.D) && canWalk) 
		{
			velocity += aceleracao;
			if(inverted)
			{
				sprite.flip(true, false);
				inverted = false;
			}	
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && canJump)
		{
			body.applyForceToCenter(velocity, 1200, false);
			canJump = false;
		}
		
		//Parando o player ao parar de andar
		if(!Gdx.input.isKeyPressed(Keys.A) && !Gdx.input.isKeyPressed(Keys.D) && canRun)
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

		body.setLinearVelocity(velocity, body.getLinearVelocity().y);
		sprite.setPosition(body.getPosition().x - (sprite.getWidth() / 2), body.getPosition().y - (sprite.getHeight() / 2));
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
