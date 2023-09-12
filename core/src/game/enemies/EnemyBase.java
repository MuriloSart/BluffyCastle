package game.enemies;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import game.collisions.BodyBuilder;
import game.core.HealthBase;

public class EnemyBase extends BodyBuilder implements Disposable
{
	private Sprite sprite;
	
	protected float density = 0f;
	protected float friction = 0.2f;
	protected float restitution = 0;
	protected float width = 20;
	protected float height = 20;
	
	public float x = 0;
	public float y = 60;
	
	public HealthBase HealthEnemy;
	
	public EnemyBase(World world) 
	{
		super(world);
		HealthEnemy = new HealthBase();
		HealthEnemy.setCurrentLife(15);
	}

	@Override
	public void dispose() 
	{
		sprite.getTexture().dispose();
	}

}
