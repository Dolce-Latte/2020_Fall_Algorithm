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
 * Parenthesis
 * */
public class Mainjava {
	
	public static char[][] readData(String args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(args));
		
		int numOfTestCase = Integer.parseInt(br.readLine());
		
		char[][] testData = new char[numOfTestCase][];
		
		for(int i = 0; i < testData.length; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			String s = token.nextToken();
			testData[i] = new char[s.length()];
			for(int j = 0; j < s.length(); j++)
				testData[i][j] = s.charAt(j);
		}
		br.close();
		return testData;
	}
	
	public static boolean parenthesis(char[] arr, int n) {
		char[][] memoi = new char[n][n];
		boolean[][] boolMemoi = new boolean[n][n];
		
		for(int i = 0; i < memoi.length; i++) {
			memoi[i][i] = arr[i];
			if(memoi[i][i] == 'a')
				boolMemoi[i][i] = true;
			else boolMemoi[i][i] = false;
		}
		
		for(int l = 1; l < memoi.length; l++) {
			for(int i = 0; i < memoi.length - l; i++) {
				int j = i + l;
				for(int k = i; k < j; k++) {
					char q = multipleTable(memoi[i][k], memoi[k+1][j]);
					if (q == 'a') {
						memoi[i][j] = q;
						boolMemoi[i][j] = true;
						break;
					}
					else {
						memoi[i][j] = q;
						boolMemoi[i][j] = false;
					}
				}
			}
		}
		return boolMemoi[n-1][n-1];
	}
	
	private static char multipleTable(char left, char right) {
		if(left == 'b' && right == 'a')
			return 'a';
		else
			return 'b';
	}
	
	public static void main(String[] args) throws IOException {
		char[][] data = readData(args[0]);
		
		for(int i = 0; i < data.length; i++) {
			boolean result = parenthesis(data[i],data[i].length);
			System.out.println(result);
		}
	}

}
