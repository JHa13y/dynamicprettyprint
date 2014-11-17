package org.ucf.cot5405;

import java.util.ArrayList;
import java.util.List;

public class PrettyPrintMain {

	public static void main(String[] args) {
		int L  = 45;
		String input  = "Call me Ishmael, some years ago, never mind how long percisely, having little or no "
				+"money in my purse, and nothing particular to interest me on the shore, I though I would sail "
				+" about a little and see the watery part of the world.";
		String[] tolkens = input.split(" ");
		int n = tolkens.length;
		int[][] S = new int[n][n];
		double[] M = new double[n];
		for(int i=0; i < n; i++)
		{
			S[i][i] = tolkens[i].length();
			for(int j=i+1; j < n; j++)
			{
				int length = S[i][j-1] + 1 + tolkens[j].length();
				if(length > L)
				{
					S[i][j] = Integer.MAX_VALUE;
				}
				else{
					S[i][j] = length;
				}
			}
		}
		
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
			{
				System.out.printf("%4d ", S[i][j]);
			}
			System.out.println();
		}

		
		//Fill in memo-zation
		M[0]=0;
		int[] B = new int[n];
		for(int i=1; i<n; i++)
		{
			double min = Double.MAX_VALUE;
			int minK = 1;
			for(int k = 1; k <= i; k++)
			{
				double l = S[k][i];
				double value = M[k-1] + Math.pow(L- l, 2);
				if(value < min)
				{
					min = value;
					minK = k;
				}
			}
			B[i] = minK;
			M[i] = M[minK-1] + Math.pow(L- S[minK][i], 2);
			System.out.println("M[" + i + "]="+M[i]);
		}
		
		//Reconstruct Solution
		int k = B[n-1];
		List<Integer> ks = new ArrayList<Integer>();
		
		while(k > 1)
		{
			System.out.println("Break after word " + (k-1));
			System.out.println("B[k-1]= " + B[k-1]);
			ks.add((k-1));
			k = B[k-1];
		}
		
		for(int i =0; i < n; i++)
		{
			System.out.print(tolkens[i] + " ");
			if(ks.contains(i))
			{
				System.out.println();
			}
		}
		
	}

}
