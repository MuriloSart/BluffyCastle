package game.collisions;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import game.game.Obstacles;
import game.player.Player;

public class ContactsListener implements ContactListener
{
	private Player player;

	public ContactsListener(Player player)
	{
		this.player = player;
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
			if(fa.getUserData() instanceof Obstacles || fb.getUserData() instanceof Obstacles)
			{
				player.canJump =  true;
				player.canRun = true;
				player.canWalk = true;
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

