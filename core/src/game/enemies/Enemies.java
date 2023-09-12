package game.enemies;

import static game.helper.RandomNumber.CustomRandom;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemies extends EnemyBase
{
	Array<EnemyBase> stringArray = new Array<EnemyBase>();
	
	public int enemyAmount = 3;
	
	public Enemies(World world) 
	{
		super(world);
		for(int i = 0; i < enemyAmount; i++) 
		{
			addEnemy();
			setEnemy(stringArray.get(i));
		}
	}
	
	public long spawnEnemies(long cd, long startTime)
	{
		long currentTime = TimeUtils.millis();
        long elapsedTime = currentTime - startTime;
		System.out.println(elapsedTime);
		if(elapsedTime > cd)
		{
			addEnemy();
			setEnemy(stringArray.get(stringArray.size - 1));
			startTime = TimeUtils.millis();
		}
		return startTime;
	}
	
	public void createEnemy()
	{
		addEnemy();
		setEnemy(stringArray.get(stringArray.size));
	}
	
	public void setEnemy(EnemyBase enemy)
	{
		y = CustomRandom(50.0f, 70.0f);
		enemy.setProperties(density, friction, restitution, width, height, x, y, BodyDef.BodyType.DynamicBody);
	}
	
	public void addEnemy()
	{
		EnemyBase enemy = new EnemyBase(world);
		stringArray.add(enemy);
	}
}
