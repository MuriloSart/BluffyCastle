package game.collisions;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import game.game.PlatformBase;
import game.game.SpawnPlatforms;
import game.player.Player;

public class ContactsListener implements ContactListener
{
	private Player player;
	private SpawnPlatforms spawnPlatforms;

	public ContactsListener(Player player, SpawnPlatforms spawnPlatforms)
	{
		this.player = player;
		this.spawnPlatforms = spawnPlatforms;
	}
	
	@Override
	public void beginContact(Contact contact) 
	{
		Fixture fa = contact.getFixtureA();
		Fixture fb = contact.getFixtureB();
		
		// Conferindo se ha dados de usuario
		if(fa == null || fb == null) return;
		if(fa.getUserData() == null || fb.getUserData() == null) return;
		
		//=========================== Colisao do Player com a Plataforma para poder Pular ===========================//
		
		if(fa.getUserData() instanceof Player || fb.getUserData() instanceof Player)//Definindo as colisoes do Player
		{
			if(fa.getUserData() instanceof PlatformBase || fb.getUserData() instanceof PlatformBase)
			{
				player.canJump =  true;
				player.canRun = true;
				player.canWalk = true;
			}
		}
		
		if(fa.getUserData() instanceof EndLine || fb.getUserData() instanceof EndLine)
		{
			if(fa.getUserData() instanceof PlatformBase || fb.getUserData() instanceof PlatformBase)
			{
				spawnPlatforms.SpawningPlatforms();
				for(int i = 0; i < spawnPlatforms.platforms.platformArray.size; i++)
				{
					if(spawnPlatforms.platforms.platformArray.get(i).equals(fb.getUserData()) || spawnPlatforms.platforms.platformArray.get(i).equals(fb.getUserData()))
					{
						spawnPlatforms.RespawningPlatform(i);
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
		
		if(fa.getUserData() instanceof Player || fb.getUserData() instanceof Player)
		{
			if(fa.getUserData() instanceof PlatformBase || fb.getUserData() instanceof PlatformBase)
			{
				player.canRun = false;
				player.canJump = false;
			}
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

