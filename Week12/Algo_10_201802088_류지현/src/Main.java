import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Graph{
		private int V;
		private int E;
		private boolean[] visited;
		private ArrayList<ArrayList<Integer>> adjList;
		private int[] outDegree;
		Graph(int V, int E){
			this.V = V;
			this.E = E;
			this.visited = new boolean[V];
			this.outDegree = new int[V];
			adjList = new ArrayList<>();
			for(int i = 0; i < V; i++) {
				adjList.add(i,new ArrayList<>());
			}
		}
		
		public void put(int x, int y) {
			adjList.get(x).add(y);
		}
		
		public void setOutDegree() {
			for(int i = 0; i < outDegree.length; i++) {
				this.outDegree[i] = this.adjList.get(i).size();
			}
		}
		
		public void print() {
			for(int i = 0; i < adjList.size(); i++) {
				for(int j = 0; j < adjList.get(i).size(); j++)
					System.out.print(adjList.get(i).get(j) + " ");
				System.out.println();
			}
		}
		
		public void dfsAll() {
			for(int i = 0; i < this.adjList.size(); i++) {
				if(!this.visited[i])
					dfs(i);
			}
		}
		
		private void dfs(int source) {
			System.out.print(source + " ");
			this.visited[source] = true;
			for(int i = 0; i < this.adjList.get(source).size(); i++) {
				int dest = adjList.get(source).get(i);
				if(!this.visited[dest])
					dfs(dest);
			}
		}
		
		public void topologySort() {
			Queue<Integer> q = new LinkedList<>();
			
			for(int i = 0; i < this.outDegree.length; i++) {
				if(outDegree[i] == 0)
					q.offer(i);
			}
			
			while(!q.isEmpty()) {
				int num = q.poll();
				
				System.out.print(num + " ");
				
				for(int i = 0; i < this.adjList.get(num).size(); i++) {
					this.outDegree[this.adjList.get(num).get(i)]--;
					
					if(this.outDegree[this.adjList.get(num).get(i)] == 0) {
						q.offer(this.adjList.get(num).get(i));
					}
				}
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
			g[i].dfsAll();
			System.out.println();
			g[i].topologySort();
			System.out.println();
		}
	}

}
