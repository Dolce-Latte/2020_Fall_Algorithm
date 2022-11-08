import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static class Edge implements Comparable<Edge>{
		private int _source; 
		private int _destination;
		private int _weight;
		public Edge(int source, int destination, int weight) {
			this._source = source;
			this._destination = destination;
			this._weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if(this._weight >= o._weight)
				return -1;
			else
				return 1;
		}
	}
	
	static class Graph{
		private int _vertex;
		private int _edge;
		private int _size;
		private ArrayList<Edge> edgeArray = new ArrayList<>();
		private int[] parent;
		
		public Graph(int vertex, int edge){
			this._vertex = vertex;
			this._edge = edge;
			this._size = vertex;
			parent = new int[vertex];
			for(int i = 0; i < parent.length; i++) {
				parent[i] = i;
			}
		}
		
		public void addEdge(Edge edge) {
			edgeArray.add(edge);
		}
		
		public int kruskal() {
			int result = 0;
			int count = 0;
			
			Collections.sort(edgeArray,Collections.reverseOrder());
			
			for(Edge edge : edgeArray) {
				if(weightedUnion(edge._source, edge._destination)) {
					System.out.println(edge._source + " " + edge._destination);
					result += edge._weight;
					if(++count == _vertex - 1) return result;
				}
			}
			return result;
		}
		
		private boolean weightedUnion(int source, int destination) {
			int sourceRoot = collapsingFind(source);
			int destinationRoot = collapsingFind(destination);
			
			if(sourceRoot == destinationRoot) return false;
			parent[sourceRoot] = destinationRoot;
			return true;
		}
		
		private int collapsingFind(int vertex) {
			if(vertex == parent[vertex]) return vertex;
			return parent[vertex] = collapsingFind(parent[vertex]);
		}
		
	}
	
	public static Graph[] readData(String args) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(args));
		
		int numOfTestCase = Integer.parseInt(br.readLine());
		
		Graph[] g = new Graph[numOfTestCase];
		
		for(int i = 0; i < g.length ; i++) {
			int v = Integer.parseInt(br.readLine());
			int e = Integer.parseInt(br.readLine());
			g[i] = new Graph(v, e);
			for(int j = 0; j < e; j++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int source = Integer.parseInt(st.nextToken());
				int destination = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				g[i].addEdge(new Edge(source,destination,weight));
			}
		}
		br.close();
		
		return g;
	}
	
	
	public static void main(String[] args) throws IOException {
		Graph[] g = readData(args[0]);
		for(int i = 0; i < g.length; i++) {
			System.out.println(g[i].kruskal());
		}		
	}

}
