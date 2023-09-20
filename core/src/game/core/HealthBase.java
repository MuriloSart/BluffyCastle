package game.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class HealthBase
{
	public float health = 10f;
	private float currentLife;
	private Body body;
	protected World world;
	
	public HealthBase(Body body, World world)
	{
		setCurrentLife(health);
		this.body = body;
		this.world = world;
	}
	
	public void Damage(float damage)
	{
		setCurrentLife(getCurrentLife() - damage);
		if(currentLife <= 0 && body != null) Kill();
	}

	public void Healing(float heal)
	{
		setCurrentLife(getCurrentLife() + heal);
	}
	
	public void Kill()
	{
		System.out.println(body);
		//world.destroyBody(body);
	}
	
	//Getters and Setters
	public float getCurrentLife() 
	{
		return currentLife;
	}

	public void setCurrentLife(float currentLife) 
	{
		this.currentLife = currentLife;
		if(currentLife > health)
		{
			health = currentLife;
		}
	}
}
