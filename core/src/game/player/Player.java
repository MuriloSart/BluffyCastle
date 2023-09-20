package game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import static game.helper.Constants.*;

import game.collisions.BodyBuilder;
import game.core.HealthBase;

public class Player extends BodyBuilder implements Disposable
{
	private Sprite sprite;
	private float density = 0f;
	private float friction = 0f;
	private float restitution = 0;
	private float width = 20;
	private float height = 20;
	
	public float x = 0;
	public float y = 80;
	
	 //Moviment
	// Velocidade do player
	private float maxVelocity = 10;
	private float velocity = 0;
	private float aceleracao = 1f;
	
	//Lidando com o Pulo
	public boolean canJump = false;
	
	// Lidando com a vida e dano do Player
	public HealthBase healthPlayer;
	public float damage = 5;
	public float readjustment = 30;
	
	public Player(World world)
	{
		super(world);
		setProperties(density, friction, restitution, width, height, x, y, BodyDef.BodyType.DynamicBody, BIT_PLAYER, (short) 0, "player");	
		healthPlayer = new HealthBase(body, world);
	}
	
	public void handleMoviment(AttackBox attackBox)
	{
		if (Gdx.input.isKeyPressed(Keys.A))
		{
			readjustment = -30;
			velocity -= aceleracao;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) 
		{
			readjustment = 30;
			velocity += aceleracao;
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && canJump == true)
		{
			body.applyForceToCenter(velocity, 300, false);
			canJump = false;
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
		
		body.setLinearVelocity(velocity, body.getLinearVelocity().y);
		attackBox.HandlePosition(readjustment);
	}
	
	public void draw(SpriteBatch batch)
	{
		sprite.draw(batch);
	}

	public void Attack()
	{
		
	}
	@Override
	public void dispose() 
	{
		sprite.getTexture().dispose();
	}

}
