import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Programa para calcular la velocidad estimada observada seg�n lo estipulado para el Problema C.
 * @author Luis Carlos Garavito Romero. 201614451.<br>
 * Miguel �ngel Puentes Ram�rez. 201616771.
 */
public class ProblemaC{

	public class Ca
	{
		private boolean state;
		private int n;
		private int a,b;

		public Ca( int a, int b)
		{
			this.state=false;
			this.a=a;
			this.b=b;
			this.n = Math.abs((a-b));
		}
		public void cambiarState(boolean pState)
		{
			state = pState;
		}
		public int darA() {
			return a;
		}
		public int darB() {
			return b;
		}
		public int darN() {
			return n;
		}
		public boolean darState()
		{
			return state;
		}

	}

	private static int[] nums;
	private static int n;
	private static Ca[][] matrix;
	private static ArrayList<Ca> array;
	private static int[] numsWithoutOrder;

	public static void main(String[] args) throws Exception
	{
		ArrayList<Integer> answers = new ArrayList<>();
		ProblemaC instancia = new ProblemaC();
		try ( 
				InputStreamReader is= new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(is);
				)
		{ 
			String line = br.readLine();
			while(line!=null && line.length()>0 && !"0".equals(line))
			{
				final String [] dataStr = line.split(" ");
				if (dataStr.length == 1)
				{
					n = Integer.parseInt(dataStr[0])*2;
				}
				else if(dataStr.length > 1)
				{
					nums = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
					llenarArreglo();
					Arrays.sort(nums);
					llenarMatriz();
					int respuesta = instancia.procesarNumeros();
					answers.add(respuesta);
					n=0;
				}
				line = br.readLine();
			}
			for (Integer integer : answers) {
				System.out.println(integer);
			}
		}
	}
	public static void llenarMatriz()
	{
		ProblemaC instancia = new ProblemaC();
		matrix = new Ca[n][n];
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (nums[i]<= nums[j])
				{
					matrix[i][j] = null;
				}
				else if(nums[i]> nums[j])
				{
					matrix[i][j] = instancia.new Ca(nums[i], nums[j]);
					matrix[i][j].cambiarState(sePuedeAconB(nums[i],nums[j]));
				}
			}
		}
	}
	public static void llenarArreglo()
	{
		ProblemaC instancia = new ProblemaC();
		numsWithoutOrder = nums;
		array = new ArrayList<Ca>();
		for (int i = 0; i < nums.length; i++)
		{
			if (i == 0 || (i%2) == 0)
			{
				Ca tmp = instancia.new Ca(nums[i],nums[i+1]);
				array.add(tmp);
			}
		}
	}
	public static boolean sePuedeAconB(int a , int b)
	{
		boolean answ = false;
		if ((esInicio(a) && esFinal(b)) || (esInicio(b) && esFinal(a)) )
		{
			answ = true;
		}
		return answ;
	}
	public static boolean esInicio(int a)
	{
		boolean answ = false;
		for (int i = 0; i < numsWithoutOrder.length; i++)
		{
			if ((i == 0 || (i%2) == 0) && numsWithoutOrder[i] == a)
			{
				answ = true;
			}
		}
		return answ;
	}
	public static boolean esFinal(int b)
	{
		boolean answ = false;
		for (int i = 0; i < numsWithoutOrder.length; i++)
		{
			if ((i%2) != 0 && numsWithoutOrder[i] == b)
			{
				answ = true;
			}
		}
		return answ;
	}
	/**
	 * 
	 */
	public int procesarNumeros()
	{
		Ca answ = buscarMayor();
		if (answ.darA()< answ.darB())
		{
			return answ.darA();
		}
		return answ.darB();
	}

	public Ca buscarMayor()
	{
		Ca mayor = new Ca(0, 0);
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				if (matrix[i][j]!= null && matrix[i][j].darState() == true && matrix[i][j].darN() > mayor.darN())
				{
					mayor = matrix[i][j];
				}
			}
		}
		return mayor;
	}



}