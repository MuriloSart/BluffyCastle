package game.enemies;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Enemies extends EnemyBase
{	
	// Instanciando Inimigos
	public Array<EnemyBase> enemiesArray = new Array<EnemyBase>();
	
	public int enemyAmount = 3;
	
	public Enemies(World world) 
	{
		super(world);
		enemiesArray.clear();
		world.destroyBody(body);
		for(int i = 0; i < enemyAmount; i++) 
		{
			addEnemy();
		}
	}
	
	public long spawnEnemies(long cd, long startTime)
	{
		long currentTime = TimeUtils.millis();
        long elapsedTime = currentTime - startTime;
		if(elapsedTime > cd)
		{
			addEnemy();
			startTime = TimeUtils.millis();
		}
		return startTime;
	}
	
	public void createEnemy()
	{
		addEnemy();
	}
	
	public void addEnemy()
	{
		EnemyBase enemy = new EnemyBase(world);
		enemiesArray.add(enemy);
	}
	
	public void removeEnemy(int index)
	{
		enemiesArray.removeIndex(index);
	}
}
