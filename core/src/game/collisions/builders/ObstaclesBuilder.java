package game.collisions.builders;

import static game.helper.Constants.*;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class ObstaclesBuilder 
{
	protected World world;
	public PolygonShape groundBox;
	public Body groundBody;
	public BodyDef groundBodyDef;
	
	public ObstaclesBuilder(World world) 
	{
		this.world = world;
	}
	
	protected void setProperties(int x, int y, float width, float height)
	{
		groundBodyDef = new BodyDef();  
		
		groundBodyDef.position.set(x, y);// Setando a posição no Mundo Físico

		groundBody = world.createBody(groundBodyDef); //Criando o corpo no Mundo Físico

		groundBox = new PolygonShape(); // Criando uma Shape Poligonal
		
		groundBox.setAsBox(width/PPM, height/PPM);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = groundBox;
		fixtureDef.density = 1.0f;
		fixtureDef.filter.categoryBits = BIT_WALL;
		fixtureDef.filter.groupIndex = 0;
		
		groundBody.createFixture(fixtureDef).setUserData(this);
	}
}
