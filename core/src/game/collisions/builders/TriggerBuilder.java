package game.collisions.builders;

import static game.helper.Constants.PPM;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class TriggerBuilder
{

	public Body body;
	protected BodyDef bodyDef;
	public FixtureDef fixtureDef;

	protected World world;
	
	public TriggerBuilder(World world) 
	{
		this.world = world;
	}

	public void setTrigger(float radius, float x, float y, String tag)
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(x, y); // Posicao inicial
		bodyDef.type = BodyDef.BodyType.StaticBody;
		body = world.createBody(bodyDef); // Criando o Objeto "body"
		fixtureDef = new FixtureDef();
		fixtureDef.density = 0f; // Densidade do corpo (afeta a massa)
		fixtureDef.friction = 0f; // Coeficiente de friccao
		fixtureDef.restitution = 0f; // Coeficiente de restituicao (elasticidade)
		fixtureDef.isSensor = true;
		
		CircleShape shape = new CircleShape();
		shape.setRadius(radius);
		fixtureDef.shape = shape;

		body.createFixture(fixtureDef).setUserData(this); // Adicione a Fixture ao corpo
		shape.dispose();
	}

	public void setTrigger(float hx, float hy, float x, float y, String tag) 
	{
		bodyDef = new BodyDef();
		bodyDef.position.set(x, y); // Posicao inicial
		bodyDef.type = BodyDef.BodyType.StaticBody;
		body = world.createBody(bodyDef); // Criando o Objeto "body"
		fixtureDef = new FixtureDef();
		fixtureDef.density = 0f; // Densidade do corpo (afeta a massa)
		fixtureDef.friction = 0f; // Coeficiente de friccao
		fixtureDef.restitution = 0f; // Coeficiente de restituicao (elasticidade)
		fixtureDef.isSensor = true;

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(hx / PPM, hy / PPM);
		fixtureDef.shape = shape;
		body.createFixture(fixtureDef).setUserData(this); // Criando Fixture e Setando seu Bit
		
		shape.dispose();
	}

}
