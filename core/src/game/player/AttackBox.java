package game.player;

import static game.helper.Constants.PPM;

import com.badlogic.gdx.physics.box2d.World;
import game.collisions.TriggerBuilder;

public class AttackBox  extends TriggerBuilder
{
	private Player player;
	private float width = 32f;
	private float height = 64f;

	public AttackBox(World world, Player player) 
	{
		super(world);
		this.player = player;
		setTrigger(width, height, player.body.getPosition().x + 1 , player.body.getPosition().y, "attackBox");
	}
	
	public void HandlePosition(float readjustment)
	{
		this.body.setTransform(player.body.getPosition().x + (readjustment / PPM), player.body.getPosition().y , 0);
	}
}
