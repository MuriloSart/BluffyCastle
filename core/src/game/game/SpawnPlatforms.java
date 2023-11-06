package game.game;

import com.badlogic.gdx.utils.Array;

import game.collisions.Obstacles;
import game.helper.RandomNumber;

public class SpawnPlatforms 
{
	 public Obstacles platforms;
	 
	 //PRIVATES
	 private Array<Integer> xPositionsArray = new Array<Integer>();
	 private Array<Integer> yPositionsArray = new Array<Integer>();
	 private float distancePlats = 6;
	 
	 public SpawnPlatforms(Obstacles obstacles)
	 {
		 this.platforms = obstacles;
		 for(int i = - 36; i <= 36; i += distancePlats)
		 {
			 xPositionsArray.add(i);
		 }
		 
		 for(int i = - 60; i <= 60; i += 20)
		 {
			 yPositionsArray.add(i);
		 }

		 SpawningPlatforms();
	 }
	 
	 public void SpawningPlatforms()
	 {
		  for(int i = 1; i < platforms.platformArray.size; i++)
		  {
			  platforms.platformArray.get(i).body.setTransform(xPositionsArray.get(i), yPositionsArray.get(RandomNumber.CustomRandom(0, yPositionsArray.size - 1)) + 10, 0);
			  MovingPlatforms(i, RandomNumber.CustomRandom(10, 15));
		  }
	 }
	 
	 public void MovingPlatforms(int index, int velocity)
	 {
		 platforms.platformArray.get(index).body.setLinearVelocity(0, -velocity);
	 }
	 
	 public void RespawningPlatforms(int index)
	 {
		 platforms.platformArray.get(index).body.setTransform(xPositionsArray.get(index), 23, 0);
		 MovingPlatforms(index, RandomNumber.CustomRandom(7, 10));
	 }
}
