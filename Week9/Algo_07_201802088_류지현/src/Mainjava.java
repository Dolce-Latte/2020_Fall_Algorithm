import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Mainjava {
	
	public static int[][] readData(String args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(args));
		
		int numOfTestCase = Integer.parseInt(br.readLine());
		
		int[][] data = new int[numOfTestCase][3];
		
		for(int i = 0; i < data.length; i++)
			for(int j = 0; j < data[i].length; j++)
				data[i][j] = Integer.parseInt(br.readLine());
		br.close();
		
		return data;
	}
	public static int exchageCoin(int[] data, int p) {
		int result = 0;
		int n = data.length - 1;
		for(int i = n; i >= 0; i--) {
			result += p/data[i];
			p = p%data[i];
		}
		
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		int[][] data = readData(args[0]);
		
		for(int i = 0; i < data.length; i++) {
			int c = data[i][0];
			int k = data[i][1];
			int p = data[i][2];
			int[] coin = new int[k+1];
			coin[0] = 1;
			for(int j = 1; j <= k; j++)
				coin[j] = coin[j-1] * c;
			
			int result = exchageCoin(coin, p);
			System.out.println(result);
		}
	}

}
