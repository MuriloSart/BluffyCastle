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
import game.player.AttackBox;
import game.player.Player;

public class ContactsListener implements ContactListener
{
	private Enemies enemies;
	private Player player;
	public float currentForce = 5;//Controle de Impulso sempre que uma Entidade levar um Ataque (Faz diferenca apenas entre 0 e 10)
	public float forceX = currentForce;

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
		
		//=============================== Colisao do Player com a Plataforma para poder Pular ===============================//
		
		if(fa.getUserData() instanceof Player || fb.getUserData() instanceof Player)//Definindo as colisoes do Player
		{
			if(fa.getUserData() instanceof Obstacles || fb.getUserData() instanceof Obstacles)
			{
				player.canJump =  true;
				player.canRun = true;
				player.canWalk = true;
			}
		}
		
		//=============================== Colisao Do AttackBox com o Inimigo para dar Dano ===============================//
		
		if(fa.getUserData() instanceof AttackBox || fb.getUserData() instanceof AttackBox)//Definindo as colisoes do Player
		{	
			for(int i = 0; i < enemies.enemiesArray.size; i++)
			{
				if(enemies.enemiesArray.get(i).equals(fb.getUserData()) || enemies.enemiesArray.get(i).equals(fa.getUserData()))
				{
					enemies.enemiesArray.get(i).collided = true;
				}
			}
		}
		
		//=============================== Colisao do Player com Inimigo ===============================//
		
		if(fa.getUserData() instanceof Player || fb.getUserData() instanceof Player)
		{
			if(fa.getUserData() instanceof EnemyBase || fb.getUserData() instanceof EnemyBase)
			{
				for(int i = 0; i < enemies.enemiesArray.size; i++)
				{
					if(enemies.enemiesArray.get(i).equals(fb.getUserData()))
					{
						if(player.body.getPosition().x >= enemies.enemiesArray.get(i).body.getPosition().x)
							forceX = currentForce;
						else
							forceX = -currentForce;
						player.healthPlayer.Damage(enemies.enemiesArray.get(i).damage);
						player.body.setLinearVelocity(0, 0);
						player.body.applyForce(new Vector2(0 , 400), new Vector2(0, 0), false);
						player.velocity = forceX;
						player.canWalk = false;
						player.endBreak = false;
						player.startTime = System.currentTimeMillis();
					}		
				}
			}
		}
	}

	@Override
	public void endContact(Contact contact) 
	{
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		// Conferindo se ha dados de usuario
		if(fa == null || fb == null) return;
		if(fa.getUserData() == null || fb.getUserData() == null) return;
		
		if(fa.getUserData() instanceof AttackBox || fb.getUserData() instanceof AttackBox)//Definindo as colisoes do Player
		{	
			for(int i = 0; i < enemies.enemiesArray.size; i++)
			{
				if(enemies.enemiesArray.get(i).equals(fb.getUserData()) || enemies.enemiesArray.get(i).equals(fa.getUserData()))
				{
					if(enemies.enemiesArray.get(i).collided)
						enemies.enemiesArray.get(i).collided = false;
				}	
			}
		}
		
		if(fa.getUserData() instanceof Player || fb.getUserData() instanceof Player)//Definindo as colisoes do Player
		{
			if(fa.getUserData() instanceof Obstacles || fb.getUserData() instanceof Obstacles)
				player.canRun = false;
		}
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

