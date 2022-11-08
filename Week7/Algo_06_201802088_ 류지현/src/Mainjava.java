import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Mainjava {
	public static int MAX = Integer.MAX_VALUE;
	public static char[][] dataString;
	public static int[][] dataIndex;
	public static void readData(String args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(args));
		
		int numOfTestCase = Integer.parseInt(br.readLine());
		
		dataString = new char[numOfTestCase][];
		dataIndex = new int[numOfTestCase][];
		
		for(int i = 0; i < numOfTestCase*2; i++) {
			StringTokenizer token = new StringTokenizer(br.readLine());
			String s = token.nextToken();
			if(i%2 == 0) {
				int k = i/2;
				dataString[k] = new char[s.length()];
				for(int j = 0; j < s.length(); j++)
					dataString[k][j] = s.charAt(j);
			}
			else {
				int k = i/2;
				dataIndex[k] = new int[s.length()];
				for(int j = 0; j < s.length(); j++)
					dataIndex[k][j] = s.charAt(j) - '0';
			}
		}
		br.close();
	}
	
	public static int cutString(int[] cost) {
		int n = cost.length;
		int[][] dp = new int[n][n];
		int[] sum = new int[n];
		
		for(int i = 1; i < n; i++) sum[i] = sum[i-1] + cost[i];
		
		for(int l = 1; l < n; l++) {
			for(int i = 1; i + l < n; i++) {
				int j = i + l;
				dp[i][j] = MAX;
				for(int k = i; k < j; k++)
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + sum[j] - sum[i-1]);
			}
		}
		return dp[1][n-1];
	}
	
	public static int[] cutting(char[] c_, int[] i_) {
		int count = 0;
		String s = "";
		
		for(int i = 0; i < c_.length; i++)
			s += c_[i];
		for(int i = 0; i < i_.length; i++)
			if(i_[i] != 0) count++;
		
		int[] len = new int[count+2];
		count = 1;
		int j = 0;
		for(int i = 1; i < i_.length; i++) {
			if(i_[i-1] != 0) {
				int l = s.substring(j, i).length();
				len[count++] = l;
				j = i;
			}
		}
		len[count] = s.substring(j).length();
		
		return len;
	}
	
	public static void main(String[] args) throws IOException {
		readData(args[0]);
		
		for(int i = 0; i < dataString.length; i++) {
			int[] lenData = cutting(dataString[i], dataIndex[i]);
			System.out.println(cutString(lenData));
		}
	}

}
