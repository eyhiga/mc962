import java.util.Random;

public class RandomGenerator
{
	private static final double A = 16807.0;
	private static final double M = 2147483647.0;
	private static final double Q = 127773.0;
	private static final double R = 2836.0;

	private double seed;
	private Random rnd;

	public RandomGenerator()
	{
		rnd = new Random();
		//this.seed = seed;
	}

	public Double generateRandom()
	{
		Double x_div_q, x_mod_q, x_new;

		x_div_q = (long) seed / Q;
		x_mod_q = seed - Q * x_div_q;
		x_new = A * x_mod_q - R * x_div_q;
		if(x_new > 0.0)
		{
			seed = x_new;
		}
		else
		{
			seed = x_new + M;
		}

		return rnd.nextDouble();
		//return seed / M;
	}
}
