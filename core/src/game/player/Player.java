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
	private float width = 32;
	private float height = 64;
	
	public float x = 0;
	public float y = 80;
	
	//====== Movimento ======//
	// Velocidade do player
	private float maxVelocity = 10;
	private float velocity = 0;
	private float aceleracao = 1f;
	
	//Lidando com o Pulo
	public boolean canJump = false;
	
	//====== Ataque ======//
	// Lidando com a vida e dano do Player
	public HealthBase healthPlayer;
	public float damage = 5;
	public boolean canAttack = false;
	public float readjustmentBox = 60;
	
	public Player(World world)
	{
		super(world);
		setProperties(density, friction, restitution, width, height, x, y, BodyDef.BodyType.DynamicBody, BIT_PLAYER, (short) 0, "player");	
		healthPlayer = new HealthBase(body, world);
	}
	
	public void handleInputs(AttackBox attackBox)
	{	
		//========== Movimentando o Player ==========//
		if (Gdx.input.isKeyPressed(Keys.A))
		{
			if(readjustmentBox > 0)
				readjustmentBox = -readjustmentBox;
			velocity -= aceleracao;
		}
		if (Gdx.input.isKeyPressed(Keys.D)) 
		{
			if(readjustmentBox < 0)
				readjustmentBox = -readjustmentBox;
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
		attackBox.HandlePosition(readjustmentBox);
		
		//========== Gerando Ataque ==========//
		
		if (Gdx.input.isKeyJustPressed(Keys.E) && !canAttack)
		{
			canAttack = true;
		}
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
