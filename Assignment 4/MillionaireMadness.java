import java.io.*;
import java.util.*;

public class MillionaireMadness {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String [] inputs = br.readLine().split(" ");
        int M = Integer.parseInt(inputs[0]);
        int N = Integer.parseInt(inputs[1]);

        Node [][] vault  = new Node[M][N];

        for(int r = 0; r < M; r++) {
            inputs = br.readLine().split(" ");
            for(int c = 0; c < N; c++) {
                vault[r][c] = new Node(r, c, Integer.parseInt(inputs[c]));
            }
        }

        PriorityQueue<Edge> edges = new PriorityQueue<>(); // Will be using Prim's Algo for this
        List<Edge> minimax = new ArrayList<>();
        edges.add(new Edge(0, vault[0][0]));

        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        while(!edges.isEmpty()) {
            Edge edge = edges.poll();
            Node current = edge.n;

            if(current.equals(vault[M-1][N-1])) {  // Means you are at SE most node
                minimax.add(edge);
                break;
            }

            if(!current.visited) {
                current.visit();
                minimax.add(edge);

                // Adding the neighbours
                for (int i = 0; i < 4; i++) {
                    int nextRow = current.r + move[i][0];
                    int nextCol = current.c + move[i][1];

                    if(nextRow < 0 || nextRow >= M || nextCol < 0 || nextCol >= N) {
                        continue; // Skip iteration if hit the end of the line
                    } else {
                        Node dst = vault[nextRow][nextCol];
                        if(dst.height - current.height < 0) {
                            edges.add(new Edge(0, dst));
                        } else {
                            edges.add(new Edge(dst.height - current.height, dst));
                        }
                    }
                }
            }
        }

        int ladder = minimax.stream()
                            .mapToInt(e -> e.weight)
                            .max()
                            .orElse(0);

        pw.println(ladder);
        pw.flush();
    }
}

class Node {
    final int r;
    final int c;
    final int height;
    boolean visited = false;

    public Node(int r, int c, int height) {
        this.r = r;
        this.c = c;
        this.height = height;
    }

    public void visit() {
        this.visited = true;
    }

    @Override
    public String toString() {
        return "(" + this.r + "," + this.c + ")" + " : " + this.height;
    }
}

class Edge implements Comparable<Edge> {
    final int weight;
    final Node n;

    public Edge(int weight, Node n) {
        this.weight = weight;
        this.n = n;
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}
