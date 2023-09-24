package game.enemies;

import static game.helper.Constants.BIT_ENEMY;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import game.collisions.BodyBuilder;
import game.core.HealthBase;
import game.helper.RandomNumber;

public class EnemyBase extends BodyBuilder implements Disposable
{
	private Sprite sprite;
	
	protected float density = 0f;
	protected float friction = 10f;
	protected float restitution = 0;
	protected float width = 32;
	protected float height = 64;
	
	public float x = 0;
	public float y = 60;
	
	// Setando Vida e Dano do Inimigo
	public HealthBase healthEnemy;
	public float damage = 2;
	public boolean collided = false;
	
	public EnemyBase(World world) 
	{
		super(world);
		x = RandomNumber.CustomRandom(0.0f, 25.0f);
		setProperties(density, friction, restitution, width, height, x, y, BodyDef.BodyType.DynamicBody, BIT_ENEMY, (short) -1, "enemy");
		healthEnemy = new HealthBase(body, world);
	}
	
	public void Attacked(float damage, float x)
	{
		float currentForce = 800;
		float forceX;
		
		if(x > body.getPosition().x)
			forceX = -currentForce;
		else
			forceX = currentForce;
		
		if(collided)
		{
			healthEnemy.Damage(damage);
			body.setLinearVelocity(0, 0);
			body.applyForce(new Vector2(forceX , 300), new Vector2(0, 0), false);
		}
			
	}
	
	@Override
	public void dispose() 
	{
		sprite.getTexture().dispose();
	}

}
