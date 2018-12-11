import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Programa para calcular el m�ximo capital que se puede esperar en el tiempo n seg�n lo estipulado para el Problema B.
 * @author Luis Carlos Garavito Romero. 201614451.<br>
 * Miguel �ngel Puentes Ram�rez. 201616771.
 */
public class ProblemaB {

	/**
	 * Ejecuta el programa.
	 * @param args
	 */
	public static void main(String[] args) {
		int n;
		int C;
		double rA[];
		double rB[];
		ArrayList<Integer> answers = new ArrayList<Integer>();
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		ProblemaB instancia = new ProblemaB();
		try
		{
			String line = br.readLine();
			while(line!=null && line.length()>0 && !line.equals("0 0"))
			{
				n=Integer.parseInt(line.split(" ")[0]);
				C=Integer.parseInt(line.split(" ")[1]);
				String[] rAS = br.readLine().split(" ");
				rA = new double[rAS.length];
				for (int i = 0; i < rAS.length; i++) {
					rA[i] = Double.parseDouble(rAS[i]);
				}
				String[] rBS = br.readLine().split(" ");
				rB = new double[rBS.length];
				for (int i = 0; i < rBS.length; i++) {
					rB[i] = Double.parseDouble(rBS[i]);
				}
				int answer = instancia.maximoCapital(n, C, rA, rB);
				answers.add(answer);
				line = br.readLine().trim();
			}
			for (Integer integer : answers) {
				System.out.println(integer);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
			System.out.println("ERROR: Entrada con formato incorrecto.");
		}
	}
	
	/**
	 * Encuentra el m�ximo capital que se puede esperar en el tiempo n<br>
	 * @param n N�mero de periodos en los que se desea invertir. n > 0.
	 * @param C Capital inicial que se va a invertir. C > 0.
	 * @param rA Arreglo con la rentabilidad de la bolsa A para cada periodo. rA.length = n.
	 * @param rB Arreglo con la rentabilidad de la bolsa B para cada periodo. rB.length = n.
	 * @return M�ximo capital que se puede esperar en el tiempo n.
	 */
	public int maximoCapital(int n, int C, double[] rA, double[] rB)
	{
		int matrix[][] = new int[2][n];				// Matriz para guardar los resultados de programación dinámica.
		matrix[0][0] = (int)(C*(1+rA[0]/100));		// Valores iniciales
		matrix[1][0] = (int)(C*(1+rB[0]/100));		// Valores iniciales
		for (int i = 1; i < n; i++)					// Se llena la matriz
		{
			matrix[0][i] = (int)(matrix[1][i-1]*(1+rA[i]/100));
			matrix[1][i] = Integer.max((int)(matrix[0][i-1]*(1+rB[i]/100)), (int)(matrix[1][i-1]*(1+rB[i]/100)));
		}
		return Integer.max(matrix[0][n-1], matrix[1][n-1]);
	}
}
