import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class Main {
	
	static class Graph{
		private int V;
		private int E;
		private boolean[] visited;
		private ArrayList<ArrayList<Integer>> adjList;
		
		Graph(int V, int E){
			this.V = V;
			this.E = E;
			this.visited = new boolean[V];
			adjList = new ArrayList<>();
			for(int i = 0; i < V; i++) {
				adjList.add(i,new ArrayList<>());
			}
		}
		
		public void put(int x, int y) {
			adjList.get(x).add(y);
			adjList.get(y).add(x);
		}
		
		public void print() {
			for(int i = 0; i < adjList.size(); i++) {
				for(int j = 0; j < adjList.get(i).size(); j++)
					System.out.print(adjList.get(i).get(j) + " ");
				System.out.println();
			}
		}
		
		public void dfs(int source) {
			Stack<Integer> stack = new Stack<>();
			Set<Integer> set = new TreeSet<>();
			stack.push(source);
			while(!stack.isEmpty()) {
				int v = stack.pop();
				if(!visited[v]) {
					set.add(v);
					visited[v] = true;
					for(int i = 0; i < adjList.get(v).size(); i++) {
						int dest = adjList.get(v).get(i);
						if(!visited[dest]) {
							stack.push(dest);
						}
					}
				}
			}
			if(!set.isEmpty()) {
				Iterator<Integer> it = set.iterator();
				for(int i = 0; i < set.size()-1; i++)
					System.out.print(it.next() + ",");
				System.out.println(it.next());
				
			}
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
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				g[i].put(x, y);
			}
		}
		br.close();
		
		return g;
	}
	
	public static void main(String[] args) throws IOException {
		Graph[] g = readData(args[0]);
		for(int i = 0; i < g.length; i++) {
			for(int j = 0; j < g[i].V; j++) {
				g[i].dfs(j);
			}
			System.out.println();
		}
	}

}
