import java.io.*;
import java.util.*;

public class Dominos {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int testcases = Integer.parseInt(br.readLine());

        while(testcases-- > 0) {
            String [] inputs = br.readLine().split(" ");
            int n = Integer.parseInt(inputs[0]);
            int m = Integer.parseInt(inputs[1]);

            Graph graph = new Graph(n);
            while(m-- > 0) {
                inputs = br.readLine().split(" ");
                int x = Integer.parseInt(inputs[0]) - 1;
                int y = Integer.parseInt(inputs[1]) - 1;

                graph.addEdge(x, y);
            }

            graph.toposort();
            graph.DFS();

            pw.println(graph.getComponents());
            pw.flush();
        }
    }
}

class Graph {
    private final int n;
    private boolean [] visited;
    private ArrayList<ArrayList<Integer>> adjList;
    private int [] sorted;
    private int components;

    public Graph(int n) {
        this.n = n;
        this.components = 0;
        this.adjList = new ArrayList<>(n);
        this.sorted = new int[n];

        for(int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void DFS() {
        this.visited = new boolean[n];

        for(int i = 0; i < n; i++) {
            if(!this.visited[sorted[i]]) {
                DFS(sorted[i]);
                this.components++;
            }
        }
    }

    private void DFS(int i) {
        this.visited[i] = true;
        for(int neighbour : adjList.get(i)) {
            if(!visited[neighbour]) {
                DFS(neighbour);
            }
        }
    }

    public void toposort(int n, Stack<Integer> stack) {
        this.visited[n] = true;

        Iterator<Integer> it = adjList.get(n).iterator();
        while (it.hasNext()) {
            int i = it.next();
            if (!this.visited[i])
                toposort(i, stack);
        }

        stack.push(n);
    }

    public void toposort() {
        this.visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!this.visited[i]) {
                toposort(i, stack);
            }
        }

        for(int i = 0; i < n; i++) {
            sorted[i] = stack.pop();
        }
    }

    public int getComponents() {
        return this.components;
    }

    public void addEdge(int i, int n) {
        adjList.get(i).add(n);
    }

}