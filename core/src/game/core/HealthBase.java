package game.core;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public class HealthBase 
{
	public float health = 10f;
	private float currentLife;
	
	public HealthBase()
	{
		setCurrentLife(health);
	}
	
	public void Damage(float damage)
	{
		setCurrentLife(getCurrentLife() - damage);
	}
	
	public void Healing(float heal)
	{
		setCurrentLife(getCurrentLife() + heal);
	}
	
	public void Kill(World world, Body body)
	{
		world.destroyBody(body);
	}
	
	//Getters and Setters
	public float getCurrentLife() {
		return currentLife;
	}

	public void setCurrentLife(float currentLife) {
		this.currentLife = currentLife;
		if(currentLife > health)
		{
			health = currentLife;
		}
	}
}
