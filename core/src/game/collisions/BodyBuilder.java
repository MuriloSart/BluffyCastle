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

	public void setProperties(float density, float friction, float restitution, float radius, float x, float y, BodyDef.BodyType bodyType, short cBits, short mBits, short gIndex, String tag)
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(x, y); // Posicao inicial
		bodyDef.type = bodyType;
		body = world.createBody(bodyDef); // Criando o Objeto "body"
		fixtureDef = new FixtureDef();
		fixtureDef.density = density; // Densidade do corpo (afeta a massa)
		fixtureDef.friction = friction; // Coeficiente de friccao
		fixtureDef.restitution = restitution; // Coeficiente de restituicao (elasticidade)
		fixtureDef.filter.categoryBits = cBits;
		fixtureDef.filter.maskBits = mBits;
		fixtureDef.filter.groupIndex = gIndex;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		fixtureDef.shape = shape;

		body.createFixture(fixtureDef).setUserData(this); // Adicione a Fixture ao corpo
		shape.dispose();
	}

	public void setProperties(float density, float friction, float restitution, float hx, float hy, float x, float y, BodyDef.BodyType bodyType, short cBits, short gIndex, String tag) 
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(x, y); // Posicao inicial
		bodyDef.type = bodyType;
		body = world.createBody(bodyDef); // Criando o Objeto "body"
		fixtureDef = new FixtureDef();
		fixtureDef.density = density; // Densidade do corpo (afeta a massa)
		fixtureDef.friction = friction; // Coeficiente de friccao
		fixtureDef.restitution = restitution; // Coeficiente de restituicao (elasticidade)
		fixtureDef.filter.categoryBits = cBits; // Tipo de bit que ele eh
		fixtureDef.filter.groupIndex = gIndex;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(hx / PPM, hy / PPM);
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef).setUserData(this); // Criando Fixture e Setando seu Bit
		
		shape.dispose();
	}

}
