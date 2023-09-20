package game.collisions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import game.enemies.Enemies;
import game.enemies.EnemyBase;
import game.game.Obstacles;
import game.player.Player;

public class ContactsListener implements ContactListener
{
	private Enemies enemies;
	private Player player;

	public ContactsListener(Player player, Enemies enemies)
	{
		this.player = player;
		this.enemies = enemies;
	}
	
	@Override
	public void beginContact(Contact contact) 
	{
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		// Conferindo se ha dados de usuario
		if(fa == null || fb == null) return;
		if(fa.getUserData() == null || fb.getUserData() == null) return;
		
		if(fa.getUserData() instanceof Player || fb.getUserData() instanceof Player)//Definindo as colisoes do Player
		{
			
			if(fa.getUserData() instanceof Obstacles || fb.getUserData() instanceof Obstacles)
			{
				player.canJump =  true;
			}
		}
		
		if(fa.getUserData() == player)
		{
			if(enemies.enemiesArray.contains((EnemyBase) fb.getUserData(), true))
			{
				for(int i = 0; i < enemies.enemiesArray.size; i++)
				{
					if(enemies.enemiesArray.get(i).equals(fb.getUserData()))
					{
						enemies.enemiesArray.get(i).HealthEnemy.Damage(player.damage);
						enemies.enemiesArray.get(i).body.applyForce(new Vector2(150 , 300), new Vector2(0, 0), false);
					}		
				}
			}
		}
	}

	@Override
	public void endContact(Contact contact) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) 
	{
		// TODO Auto-generated method stub
		
	}

}

