public class ProblemaB {

	public static void main(String[] args) {
		int n = 40;
		int C = 100;
		double rA[] = {-6, 5, 7, -5, -1, 2, 4, 9, 7, 5, 6, 3, 2, -4, 5, 2, 8, -5, 9, 7, 
				-6, 5, 7, -5, -1, 2, 4, 9, 7, 5, 6, 3, 2, -4, 5, 2, 8, -5, 9, 7, 
				8, 7, -4, 5, 2, 0, 1, 6, 9, -8, 7, -5, 3, 6, -5, 7, -2, 9, -5, 7, 
				-6, 5, 7, -5, -1, 2, 4, 9, 7, 5, 6, 3, 2, -4, 5, 2, 8, -5, 9, 7, 
				8, 7, -4, 5, 2, 0, 1, 6, 9, -8, 7, -5, 3, 6, -5, 7, -2, 9, -5, 7
		};
		double rB[] = {8, 7, -4, 5, 2, 0, 1, 6, 9, -8, 7, -5, 3, 6, -5, 7, -2, 9, -5, 7, 
				-6, 5, 7, -5, -1, 2, 4, 9, 7, 5, 6, 3, 2, -4, 5, 2, 8, -5, 9, 7, 
				8, 7, -4, 5, 2, 0, 1, 6, 9, -8, 7, -5, 3, 6, -5, 7, -2, 9, -5, 7, 
				-6, 5, 7, -5, -1, 2, 4, 9, 7, 5, 6, 3, 2, -4, 5, 2, 8, -5, 9, 7, 
				-6, 5, 7, -5, -1, 2, 4, 9, 7, 5, 6, 3, 2, -4, 5, 2, 8, -5, 9, 7
		};
		
		long start = System.currentTimeMillis();
		int answer = maximoCapital(n, C, rA, rB, false);
		long end = System.currentTimeMillis();
		System.out.println(answer);
		System.out.println("Tiempo transcurrido: " + (end - start) + "ms");
	}

	public static int maximoCapital(int n, int C, double[] rA, double[] rB, boolean bagA)
	{
		System.out.println("n: " + n);
		System.out.println(bagA);
		double answer = 0;
		if(n==1)
		{
			if(!bagA)
				answer = Double.max(C*(1+rA[n-1]/100), C*(1+rB[n-1]/100));
			else
				answer = C*(1+rB[n-1]/100);
		}
		else
		{
			if(bagA)
				answer = maximoCapital(n-1, (int) (C*(1+rB[n-1]/100)), rA, rB, false);
			else
			{
				answer = Double.max(maximoCapital(n-1, (int) (C*(1+rB[n-1]/100)), rA, rB, false), maximoCapital(n-1, (int) (C*(1+rA[n-1]/100)), rA, rB, true));
			}
		}
		return (int)answer;
	}
	
	public static int maximoCapital2(int n, int C, double[] rA, double[] rB, boolean bagA)
	{
		System.out.println("n: " + n);
		System.out.println(bagA);
		double answer = 0;
		if(n==1)
		{
			if(!bagA)
				answer = Double.max(C*(1+rA[n-1]/100), C*(1+rB[n-1]/100));
			else
				answer = C*(1+rB[n-1]/100);
		}
		else
		{
			if(bagA)
				answer = maximoCapital2(n-1, (int) (C*(1+rB[n-1]/100)), rA, rB, false);
			else
			{
				answer = Double.max(maximoCapital2(n-1, (int) (C*(1+rB[n-1]/100)), rA, rB, false), maximoCapital2(n-1, (int) (C*(1+rA[n-1]/100)), rA, rB, true));
			}
		}
		return (int)answer;
	}
}
