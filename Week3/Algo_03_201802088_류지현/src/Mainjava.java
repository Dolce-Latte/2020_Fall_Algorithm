import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Mainjava {
	public static Stack<Tuple> dataStack = new Stack<>();
	
	/**
	 * Read testdata using BufferedReader
	 * And using StrigTokenizer
	 * Data form Tuple (index, data)
	 */
	public static Tuple[][] readData(String args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(args));
		
		int numOfTestCase = Integer.parseInt(br.readLine());
		
		Tuple[][] testData = new Tuple[numOfTestCase][];
		
		for(int i = 0; i < testData.length; i++) {
			int length = Integer.parseInt(br.readLine());
			testData[i] = new Tuple[length];
			StringTokenizer token = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < testData[i].length; j++) {
				int y = Integer.parseInt(token.nextToken());
				testData[i][j] = new Tuple(j, y);
			}
		}
		br.close();		
		return testData;		
	}
	
	/**
	 * Dataarray divide tow part -> left, right
	 * and merge
     */
	public static void longestIndexArray(Tuple[] array, int left, int right) {
		if(left < right) {
			int mid = (left + right) / 2;
			
			longestIndexArray(array, left, mid);
			longestIndexArray(array, mid + 1, right);			
			longestIndex(array, left, right);
		}
	}
	
	/**
	 * if dataStack is empty then push data
	 * else compare two data
	 */
	private static void longestIndex(Tuple[] array, int left, int right) {
		for(int i = left; i <= right ; i++) {
			if(dataStack.empty())
				dataStack.push(array[i]);
			else {
				if(compareTupleData(dataStack.peek(), array[i]))
					dataStack.push(array[i]);
			}
		}
	}
	
	/**
	 * Compare two data tuple
	 * */
	private static boolean compareTupleData(Tuple leftTuple, Tuple rightTuple) {
		if (leftTuple.getY() < rightTuple.getY())
			if((rightTuple.getX() - leftTuple.getX()) == 1)
				return true;
		return false;
	}
	
	/**
	 * Print result
	 */
	public static void printResult(Tuple[][] array, int size) {
		for(int i = 0; i < array.length; i++) {
			longestIndexArray(array[i], 0, array[i].length - 1);
			System.out.println(dataStack.size() - 1);
			dataStack.clear();
		}
	}
	
	public static void main(String[] args) throws IOException {
		Tuple[][] dataArray = readData(args[0]);
		
		printResult(dataArray, dataArray.length);
		
	}

}
