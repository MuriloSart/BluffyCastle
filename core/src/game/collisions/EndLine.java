package game.collisions;

import com.badlogic.gdx.physics.box2d.World;

import game.collisions.builders.TriggerBuilder;

public class EndLine extends TriggerBuilder
{
	private float width = 1300;
	private float height = 50;
	public EndLine(World world)
	{
		super(world);
		setTrigger(width, height, 0 , -24, "StartLine");
	}
}
