import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Programa para calcular el máximo capital que se puede esperar en el tiempo n según lo estipulado para el Problema B.
 * @author Luis Carlos Garavito Romero. 201614451.<br>
 * Miguel Ángel Puentes Ramírez. 201616771.
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
				int answer = instancia.maximoCapital(n, C, rA, rB, false);
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
	 * Encuentra el máximo capital que se puede esperar en el tiempo n<br>
	 * @param n Número de periodos en los que se desea invertir. n > 0.
	 * @param C Capital inicial que se va a invertir. C > 0.
	 * @param rA Arreglo con la rentabilidad de la bolsa A para cada periodo. rA.length = n.
	 * @param rB Arreglo con la rentabilidad de la bolsa B para cada periodo. rB.length = n.
	 * @param bagA Booleano para controlar las inversiones en la bolsa A.<br>
	 * bagA es igual a true si en el periodo anterior se invirtió en la bolsa A y es igual a false de lo contrario.
	 * @return Máximo capital que se puede esperar en el tiempo n.
	 */
	public int maximoCapital(int n, int C, double[] rA, double[] rB, boolean bagA)
	{
		double answer = 0;
		if(n==1)		// Caso base del algoritmo de programación dinámica.
		{
			if(bagA)	// Si en el periodo anterior se invirtió en la bolsa A.
				answer = C*(1+rB[n-1]/100);
			else
				answer = Double.max(C*(1+rA[n-1]/100), C*(1+rB[n-1]/100));
		}
		else
		{
			if(bagA)	// Si en el periodo anterior se invirtió en la bolsa A.
				answer = maximoCapital(n-1, (int) (C*(1+rB[n-1]/100)), rA, rB, false);
			else
			{
				answer = Double.max(maximoCapital(n-1, (int) (C*(1+rB[n-1]/100)), rA, rB, false), maximoCapital(n-1, (int) (C*(1+rA[n-1]/100)), rA, rB, true));
			}
		}
		return (int)answer;
	}
}
