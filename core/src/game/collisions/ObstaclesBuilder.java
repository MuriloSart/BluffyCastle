package game.collisions;

import static game.helper.Constants.PPM;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
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
		// Set its world position
		groundBodyDef.position.set(x, y);

		// Create a body from the definition and add it to the world
		groundBody = world.createBody(groundBodyDef);  

		// Create a polygon shape
		groundBox = new PolygonShape();  
		// Set the polygon shape as a box which is twice the size of our view port and 20 high
		// (setAsBox takes half-width and half-height as arguments)
		groundBox.setAsBox(width/PPM, height/PPM);
		// Create a fixture from our polygon shape and add it to our ground body  
		groundBody.createFixture(groundBox, 0.0f).setUserData(this);
	}
}
