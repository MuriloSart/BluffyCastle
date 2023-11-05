package game.game;

import game.collisions.Obstacles;

public class SpawnPlatforms 
{
	 public Obstacles platforms;
	 
	 public SpawnPlatforms(Obstacles obstacles)
	 {
		 this.platforms = obstacles;
		 SpawningPlatforms();
	 }
	 
	 public void SpawningPlatforms()
	 {
		  for(int i = 1; i < platforms.platformArray.size; i++)
		  {
			  platforms.platformArray.get(i).groundBody.setTransform(-30 + i* 3, 21, 0);
			  platforms.platformArray.get(i).groundBody.setLinearVelocity(0, -1);
		  }
	 }
	 
	 public void MovingPlatforms()
	 {
//		  for(int i = 1; i < platforms.platformArray.size; i++)
//		  {
//			  platforms.platformArray.get(i).groundBody.setLinearVelocity(0, 1);
//		  }
	 }
	 
	 public void RespawningPlatform(int index)
	 {
		 
	 }
}
