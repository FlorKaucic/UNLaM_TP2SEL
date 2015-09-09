package test;

import sel.GaussJordan;
import sel.MatrizMath;

public class GaussJordanTest {

	public static void main(String[] args) {
		double[][] m = new double[5][5];
		double[][] n = new double[5][5];

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				m[i][j] = i + j + 1;

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				if (i == j)
					n[i][j] = 1;
				else
					n[i][j] = 0;
			}

		MatrizMath mat = new MatrizMath(m);
		MatrizMath id = new MatrizMath(n);
		
		System.out.println("Matriz: " + mat);
		System.out.println("Matriz identidad: " + id);
		
		
		System.out.println("Prueba");

		System.out.println(GaussJordan.gaussJordan(m, n));
		System.out.println("slsls");

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(m[i][j]);

			}
			System.out.println("");
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(n[i][j]);

			}
			System.out.println("");
		}
	}
}