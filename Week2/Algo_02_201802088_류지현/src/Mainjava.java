import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Stack;

/**
 * Mainjava.java
 * 
 * 알고리즘 02반
 * 201802088 류지현
 * 
 * @author Ryujihyun;
 * 
 * Divide and Conquer
 * Maxima of a Point Set
 * */

public class Mainjava {
	
	private static Stack<Point> pointStack = new Stack<>();
	
	/*FileInputStream, InputStream, StrreamTokenizer를 이용하여 
	 * text 파일의 내용 읽어서 Array에 저장 */
	public static Point[][] readDataAndInitArray(String args) throws IOException {
		FileInputStream fileStream = new FileInputStream(new File(args));
		InputStreamReader reader = new InputStreamReader(fileStream);
		StreamTokenizer token = new StreamTokenizer(reader);
		token.nextToken();
		
		int testCase = (int)token.nval;
		Point[][] pointArray = new Point[testCase][];
		int raw = 0;
		
		while((token.nextToken() != StreamTokenizer.TT_EOF)) {
			int numOfPoints = (int) token.nval;
			int column = 0;
			pointArray[raw] = new Point[numOfPoints];
			while((column < numOfPoints) && (token.nextToken() != StreamTokenizer.TT_EOF)) {
				int xCoordinate = (int) token.nval;
				token.nextToken();
				int yCoordinate = (int) token.nval;
				pointArray[raw][column++] = new Point(xCoordinate,yCoordinate);
			}
			raw++;
		}
		return pointArray;
	}
	
	/*Pointarray를 divide하고 conquer한다.*/
	public static void dividePointArray(Point[] points, int left, int right) {
		if(left < right) {
			int mid = (left + right) / 2;
			dividePointArray(points, mid + 1, right);
			dividePointArray(points, left, mid);
			findMaximalPointArray(points, left, right);
		}
	}
	
	//각 Point의 y좌표를 비교해서 Stack에 저장
	public static void findMaximalPointArray(Point[] points, int left, int right) {
		for(int i = right; i >= left; i--) {
			if(pointStack.isEmpty())
				pointStack.add(points[i]);
			else {
				int p1 = pointStack.peek().getY();
				int p2 = points[i].getY();
				if (p1 < p2) 
					pointStack.add(points[i]);
			}
		}
	}
	
	//실행 method
	public static void maximalPointTest(Point[][] points, int testCase) {
		for(int i = 0; i < testCase; i++) {
			if ((points[i].length / 2) == 0) {
				dividePointArray(points[i], 0, points[i].length-1);
				printCoordinate();
			}
			else {
				pointStack.add(points[i][points[i].length-1]);
				dividePointArray(points[i], 0, points[i].length-1);
				printCoordinate();
			}
		}
	}
	
	//결과 출력
	public static void printCoordinate() {
		while(pointStack.empty() == false) {
			Point temp = pointStack.pop();
			int x = temp.getX();
			int y = temp.getY();
			System.out.println(x + " " + y);
		}
		System.out.println();
	}
	
	public static void main(String[] args) throws IOException {
		Point[][] dataOfPoints = readDataAndInitArray(args[0]);
		int numberOfCase = dataOfPoints.length;
		
		maximalPointTest(dataOfPoints, numberOfCase);
			
	}

}
