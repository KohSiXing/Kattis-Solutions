import java.io.*;
import java.util.*;

public class LostMap {

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

            int n = Integer.parseInt(br.readLine());

            int[][] adjMat = new int[n][n];

            for(int i = 0; i < n; i++) {
                String [] input = br.readLine().split(" ");
            	for(int j = 0; j < n; j++) {
                    adjMat[i][j] = Integer.parseInt(input[j]);
                }
            }


            Graph graph = new Graph(n, adjMat);
            graph.prim(pw);
    } 
}


class Graph {
	private final int n;
	private int [][] adjMat;
    private PriorityQueue<IntegerTriplet> pq;
    private boolean [] visited;

	public Graph(int n, int[][] adjMat) {
		this.n = n;
		this.adjMat = adjMat;
        this.visited = new boolean [n];
        this.pq = new PriorityQueue<>();
	}

    
	public void addEdges(int i) {
        visited[i] = true;
        for(int j = 0; j < n; j++) {
            if(i == j || visited[j]) {
                continue;
            } else {
                 pq.offer(new IntegerTriplet(i, j, adjMat[i][j]));
            }
        }
    }

    public void prim(PrintWriter pw) {

        addEdges(0);

        while(!pq.isEmpty()) {
            IntegerTriplet it = pq.poll();
            if (!visited[it.getDst()]) { // we have not connected this vertex yet
                addEdges(it.getDst());
                pw.println(it.getPair());
                pw.flush();
            }
        }

    }

}

class IntegerTriplet implements Comparable<IntegerTriplet> {
    private final int u; // Src
    private final int v; // Dst
    private final int w; // Weight between u and v

    public IntegerTriplet(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public String getPair() {
        int src = u + 1;
        int dst = v + 1;
        return src + " " + dst; 
    }

    public int getSrc() {
        return this.u;
    }

    public int getDst() {
        return this.v;
    }

    @Override
    public int compareTo(IntegerTriplet it) {
        if(this.w != it.w) {
            return this.w - it.w;
        } else if (this.v != it.v) {
            return this.v - it.v;
        } else {
            return this.u - it.u;
        }
    }
}
