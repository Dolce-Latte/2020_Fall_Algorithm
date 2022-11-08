import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

/**
 * Mainjava.java
 * 
 * 알고리즘 02반
 * 201802088 류지현
 * 
 * 
 * */

public class Mainjava {
	
	/*This variable counts the number of inversion*/
	private static int inversionCount = 0;
	
	/*Using StringTokenizer, remove "," and store numbers in the array*/
	private static int[] readDataAndInitArray(String args) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(args));
		StringTokenizer readData = new StringTokenizer(reader.readLine());
		ArrayList<Integer> integerData = new ArrayList<Integer>(10000000);
		reader.close();
		
		while (readData.hasMoreTokens())
			integerData.add(Integer.parseInt(readData.nextToken(",")));
		
		int size = integerData.size();
		int[] integerDataArray = new int[size];
		for(int i = 0; i < size; i++)
			integerDataArray[i] = integerData.get(i);
		
		return integerDataArray;
			
	}
	
	/*Counting the inversion*/
	private static void mergeInversion(int[] array,int left, int mid, int right) { 

		int leftMaxIndex = mid - left + 1;
		int rightMaxIndex = right - mid;
		int[] L = new int[leftMaxIndex + 1];
		int[] R = new int[rightMaxIndex + 1];
		
		for (int i = 0; i < leftMaxIndex; i++)
			L[i] = array[left + i];
		for (int j = 0; j < rightMaxIndex; j++)
			R[j] = array[mid + j + 1];
		
		L[leftMaxIndex] = Integer.MAX_VALUE;
		R[rightMaxIndex] = Integer.MAX_VALUE;
		
		int i = 0, j = 0;
		for (int k = left; k < right + 1; k++) {
			if (L[i] <= R[j]) {
				array[k] = L[i];
				i += 1;
			}
			else {
				inversionCount += leftMaxIndex - i;
				array[k] = R[j];
				j += 1;				
			}
		}
	}
	
	/* Divide the array into two subproblem
	 * Combine the results of the left and right sides */
	private static void mergeInversionCount(int[] array, int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeInversionCount(array, left, mid);
			mergeInversionCount(array, mid + 1, right);
			mergeInversion(array, left, mid, right);
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		int[] a = readDataAndInitArray(args[0]);
		
		mergeInversionCount(a, 0, a.length-1);
		System.out.println(inversionCount);
		
	}
	
}
