package game.collisions;

import static game.helper.Constants.PPM;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class BodyBuilder
{

	public Body body;
	
	protected BodyDef bodyDef;
	public FixtureDef fixtureDef;

	protected World world;
	protected BodyDef.BodyType bodyType;
	
	public BodyBuilder(World world) 
	{
		this.world = world;
	}

	protected void setProperties(float density, float friction, float restitution, float radius, float x, float y, BodyDef.BodyType bodyType)
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(x, y); // Posicao inicial
		bodyDef.type = bodyType;
		body = world.createBody(bodyDef); // Crie o objeto body primeiro
		fixtureDef = new FixtureDef();
		fixtureDef.density = density; // Densidade do corpo (afeta a massa)
		fixtureDef.friction = friction; // Coeficiente de friccao
		fixtureDef.restitution = restitution; // Coeficiente de restituicao (elasticidade)

		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		fixtureDef.shape = shape;

		body.createFixture(fixtureDef).setUserData(this); // Adicione a Fixture ao corpo
		shape.dispose();
	}

	protected void setProperties(float density, float friction, float restitution, float hx, float hy, float x, float y, BodyDef.BodyType bodyType) 
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(x, y); // Posicao inicial
		bodyDef.type = bodyType;
		body = world.createBody(bodyDef); // Crie o objeto body primeiro
		fixtureDef = new FixtureDef();
		fixtureDef.density = density; // Densidade do corpo (afeta a massa)
		fixtureDef.friction = friction; // Coeficiente de friccao
		fixtureDef.restitution = restitution; // Coeficiente de restituicao (elasticidade)

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(hx / PPM, hy / PPM);
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef).setUserData(this); // Adicione a Fixture ao corpo
		
		shape.dispose();
	}

}
