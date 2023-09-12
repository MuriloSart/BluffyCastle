package game.helper;

public final class RandomNumber
{	
	
	public final static int CustomRandom(int min, int max) {
		if(max > 0)
			max++;
		if(min < 0)
			min--;
		return (int) (Math.random() * (max - min) + min);
	}
	
	public final static float CustomRandom(float min, float max) {
		return (float) (Math.random() * (max - min) + min);
	}
	
	public final static double CustomRandom(double min, double max) {
		return (double) (Math.random() * (max - min) + min);
	}
		
	


}
