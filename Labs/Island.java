import java.util.*;

public class Island {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int r = sc.nextInt();
            int c = sc.nextInt();

            sc.nextLine();

            String[][] map = new String[r][c];

            for(int i = 0; i < r; i++) {
            	map[i] = sc.nextLine().split("");
            } 

            Graph graph = new Graph(r, c, map);
            int components = graph.DFS();
            System.out.println(components);
    }

}


class Graph {
	private final int r;
	private final int c;
	private String [][] map;
	private boolean [][] visited;

	public Graph(int r, int c, String[][] map) {
		this.r = r;
		this.c = c;
		this.map = map;
		this.visited = new boolean[r][c];
	}

	public int DFS() {
		int components = 0;
		for(int i = 0; i < this.r; i++) {
        	for(int j =0; j < this.c; j++) {
        		if(this.map[i][j].equals("L") && !this.visited[i][j]) {
        			DFS(i,j);
        			components++;
        		}
            }
        }
        return components;
	}

	public void DFS(int i, int j) {
		Stack<Coordinates> stack = new Stack<>();
		stack.push(new Coordinates(i,j));

		while(!stack.empty()) {
			//add neighbours, similar to Millionaire Madness only top, bottom, left and right are legit moves
			Coordinates current = stack.pop();
			if(!visited[current.r][current.c]) {
                visited[current.r][current.c] = true;

                // Adding the neighbours check if any land or clouds are present since a W will break apart the islands
                if(current.r > 0 && !this.map[current.r - 1][current.c].equals("W")) { // Add above neighbour
                    stack.push(new Coordinates(current.r - 1, current.c));
                }

                if(current.r < this.r - 1 && !this.map[current.r + 1][current.c].equals("W")) { // Add below neighbour
                    stack.push(new Coordinates(current.r + 1, current.c));
                }

                if(current.c < this.c - 1 && !this.map[current.r][current.c + 1].equals("W")) { // Add right neighbour
                    stack.push(new Coordinates(current.r, current.c + 1));
                }

                if(current.c > 0 && !this.map[current.r][current.c - 1].equals("W")) { // Add left neighbour
                    stack.push(new Coordinates(current.r, current.c - 1));
                }
            }
		}
	}
}

class Coordinates {
	final int r;
	final int c;

	public Coordinates(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "[" + r + ", " + c +  "]";
	}
}

