package game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	
	public float x = 39;
	public float y = 10;
	
	//====== Movimento ======//
	// Velocidade do player
	private float maxVelocity = 15;
	private float aceleracao = 1f;
	
	public float velocity = 0;
	public boolean canRun = false;
	public boolean canWalk = true;
	public boolean idleState = true;
	
	//Lidando com o Pulo
	public boolean canJump = false;
	
	//====== Imagem Player ======//
	private Texture textureIdle;
	private Texture textureWalk;
	private TextureRegion[] frames;
	private boolean inverted = false;
	
	private Animation<TextureRegion> animation;
	private float frameDuration = .5f; // A duração de cada quadro da animação
	
	private float elapsedTime = 0; // Tempo acumulado
	private TextureRegion currentFrame;
	
	private float currentSide;
	private float currentPosition;
	
	public Player(World world)
	{
		super(world);
		setProperties(density, friction, restitution, width, height, x, y, BodyDef.BodyType.DynamicBody, BIT_PLAYER, (short) 0, "player");
		
		//Desenhando o Player
		currentPosition = -4;
		textureIdle = new Texture("Player/PlayerIdle.png");
		textureWalk = new Texture("Player/Player_Walk_Spritesheet.png");
		SpriteSheet(textureIdle);
		currentSide = currentFrame.getRegionWidth();
	}
	
	private void SpriteSheet(Texture texture)
	{
		frames = TextureRegion.split(texture, 64, 64)[0];
		animation = new Animation<TextureRegion>(frameDuration, frames);
		currentFrame = animation.getKeyFrame(elapsedTime, true);
	}
	
	public void handleInputs()  
	{
		elapsedTime += Gdx.graphics.getDeltaTime();
		currentFrame = animation.getKeyFrame(elapsedTime, true);
		
		//========== Movimentando o Player ==========//
		if (Gdx.input.isKeyPressed(Keys.A)  && canWalk)
		{
			velocity -= aceleracao;
			if(idleState)
			{
				SpriteSheet(textureWalk);
				idleState = false;
			}
			if(!inverted)
			{
				currentSide = -currentSide;
				currentPosition = 0;
				inverted = true;
			}	
		}
		else if (Gdx.input.isKeyPressed(Keys.D) && canWalk) 
		{
			velocity += aceleracao;
			if(idleState)
			{
				SpriteSheet(textureWalk);
				idleState = false;
			}
			if(inverted)
			{
				currentSide = -currentSide;
				currentPosition = -4;
				inverted = false;
			}	
		}
		else
		{
			if(!idleState)
			{
				SpriteSheet(textureIdle);
				idleState = true;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE) && canJump)
		{
			body.applyForceToCenter(velocity, 2000, false);
			canJump = false;
		}
		if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && !canJump)
		{
			body.applyForceToCenter(velocity, -300, false);
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
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(currentFrame, body.getPosition().x + (currentFrame.getRegionWidth()/PPM) + currentPosition , body.getPosition().y - 2, currentSide / (PPM/2), currentFrame.getRegionHeight() / (PPM/2));
	}
	
	@Override
	public void dispose() 
	{
		textureIdle.dispose();
		textureWalk.dispose();
		for (TextureRegion frame : animation.getKeyFrames()) {
		    frame.getTexture().dispose();
		}
	}

}
