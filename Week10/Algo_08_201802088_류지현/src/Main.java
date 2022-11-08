import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static int[][] readData(String args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(args));
		
		int numOfTestCase = Integer.parseInt(br.readLine());
		
		int[][] data = new int[numOfTestCase][];
		
		for(int i = 0; i < data.length; i++) {
			int numOfElement = Integer.parseInt(br.readLine());
			data[i] = new int[numOfElement];
			StringTokenizer token = new StringTokenizer(br.readLine(),",");
			for(int j = 0; j < data[i].length; j++) {
				data[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		
		return data;
	}
	
	public static int minimumCost(int[] data) {
		int len = Arrays.stream(data).sum();
		int cost = 0;
		int prev = 0;
		Arrays.sort(data);
		
		for(int i = 0; i < data.length; i++) {
			if(len - data[i] == 0) continue;
			cost += prev + data[i];
			prev += data[i];
			len -= data[i];
			
		}
		return cost;		
	}
	
	public static void print(int[] data) {
		for(int i = 0; i < data.length-1; i++)
			System.out.print(data[i]+",");
		System.out.println(data[data.length-1]);
	}
	
	public static void main(String[] args) throws IOException {
		int[][] data = readData(args[0]);
		for(int i = 0; i < data.length; i++) {
			int result = minimumCost(data[i]);
			System.out.println(result);
			print(data[i]);
		}
		
	}

}
