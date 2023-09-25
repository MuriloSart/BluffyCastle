package game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
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
	public Sprite sprite;
	public Texture texture;
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
	private float aceleracao = 1f;
	
	public float velocity = 0;
	public boolean canRun = false;
	public boolean canWalk = true;
	
	//Lidando com o Pulo
	public boolean canJump = false;
	
	//====== Ataque ======//
	// Lidando com a vida e dano do Player
	public HealthBase healthPlayer;
	public float damage = 5;
	public float readjustmentBox = 72;
	public boolean canAttack = false;
	public boolean endBreak = true;
	
	//Parar ao atacar
	public long startTime;
	public long currentTime;
	public long elapseTime;
	public long coolDown = 500;
	
	public Player(World world)
	{
		super(world);
		setProperties(density, friction, restitution, width, height, x, y, BodyDef.BodyType.DynamicBody, BIT_PLAYER, (short) 0, "player");	
		healthPlayer = new HealthBase(body, world);
		
		texture = new Texture("player.png");
		sprite = new Sprite(texture);
		sprite.setSize(64/PPM , 128/PPM);
	}
	
	public void handleInputs(AttackBox attackBox)
	{
		//========== Movimentando o Player ==========//
		if (Gdx.input.isKeyPressed(Keys.A)  && canWalk)
		{
			if(readjustmentBox > 0)
				readjustmentBox = -readjustmentBox;
			velocity -= aceleracao;
		}
		if (Gdx.input.isKeyPressed(Keys.D) && canWalk) 
		{
			if(readjustmentBox < 0)
				readjustmentBox = -readjustmentBox;
			velocity += aceleracao;
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && canJump)
		{
			body.applyForceToCenter(velocity, 800, false);
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
		
		
		//========== Gerando Ataque ==========//
		
		if (Gdx.input.isKeyJustPressed(Keys.E) && !canAttack && endBreak)
		{
			canAttack = true;
			endBreak = false;
			startTime = System.currentTimeMillis();
		}
		currentTime = System.currentTimeMillis();
		
		elapseTime = currentTime - startTime;
		if(elapseTime > coolDown)
			endBreak = true;

		body.setLinearVelocity(velocity, body.getLinearVelocity().y);
		sprite.setPosition(body.getPosition().x - (sprite.getWidth() / 2), body.getPosition().y - (sprite.getHeight() / 2));
		attackBox.HandlePosition(readjustmentBox);
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
