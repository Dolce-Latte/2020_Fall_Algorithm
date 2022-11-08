import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Mainjava.java
 * 
 * 알고리즘 02반
 * 201802088 류지현
 * 
 * @author Ryujihyun;
 * 
 * Dynamic Programming
 * Sum SubSet
 * */
public class Mainjava {
	
	static int[] sum;
	public static int[][] readData(String args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(args));
		
		int numOfTestCase = Integer.parseInt(br.readLine());
		
		int[][] testData = new int[numOfTestCase][];
		sum = new int[numOfTestCase];
		ArrayList<Integer> tmp = new ArrayList<>();
		
		for(int i = 0; i < testData.length; i++) {
			int target = Integer.parseInt(br.readLine());
			sum[i] = target;
			StringTokenizer token = new StringTokenizer(br.readLine(), ",");
			
			while(token.hasMoreTokens())
				tmp.add(Integer.parseInt(token.nextToken()));
			
			testData[i] = new int[tmp.size()];
			for(int j = 0; j < testData[i].length; j++)
				testData[i][j] = tmp.remove(0);
		}
		br.close();
		return testData;
	}
	
	public static boolean sumSubSet(int[] array, int n, int S) {
		boolean[][] memoi = new boolean[n + 1][S + 1];
		
		for(int i = 0; i <= n; i++)
			memoi[i][0] = true;
		
		for(int i = 1; i <= S; i++)
			memoi[0][i] = false;
		
		for(int i = 1; i <= S; i++) {
			for(int j =1; j <= n; j++) {
				if(array[j-1] > i)
					memoi[j][i] = memoi[j-1][i];
				else
					memoi[j][i] = memoi[j-1][i-array[j-1]] || memoi[j-1][i];
			}
		}
		
		
		return memoi[n][S];
	}
	
	
	
	public static void main(String[] args) throws IOException {
		int[][] data = readData(args[0]);
		
		sumSubSet(data[0], data[0].length, sum[0]);
		
		
		for(int i = 0; i < data.length; i++) {
			System.out.println(sumSubSet(data[i], data[i].length, sum[i]));
		}
	}

}
