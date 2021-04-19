import java.util.*;


/*
 * Key Formulae
 *
 * time = Distance / Speed i.e (distance / 5 m s^-1) Given that I run at a speed of 5 m/s 
 * euclidean distance = sqrt((x2-x1)^2 + (y2-y1)^2)
 */

public class HumanCannonBallRun {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            Coordinate src = new Coordinate(sc.nextDouble(), sc.nextDouble());
            sc.nextLine();
            
            Coordinate dst = new Coordinate(sc.nextDouble(), sc.nextDouble());
            sc.nextLine();
            
            int n = sc.nextInt();
            sc.nextLine();

            Coordinate [] coordinates = new Coordinate[n + 2];
            coordinates[0] = src;
            coordinates[n + 1] = dst;

            // If n == 0, this will not execute; i.e I have to run from source to destination
            for(int i = 1; i < n + 1; i++) {
            	coordinates[i] = new Coordinate(sc.nextDouble(), sc.nextDouble());
            	sc.nextLine();
            }

            double[][] adjMat = new double[n + 2][n + 2];
            for (int i = 0; i < n + 2; i++) {
	            for (int j = 0; j < n + 2; j++) {
	                if (i == j) {
	                    adjMat[i][j] = 0;
	                } else {
	                    adjMat[i][j] = Integer.MAX_VALUE;
	                }
	            }
        	}

        	Graph graph = new Graph(n + 2, adjMat, coordinates);
        	System.out.println(graph.findTime());

    } 
}


class Graph {
	private final int n;
	private double [][] adjMat;
    private Coordinate [] coordinates;

	public Graph(int n, double[][] adjMat, Coordinate[] coordinates) {
		this.n = n;
		this.adjMat = adjMat;
        this.coordinates = coordinates;
	}

	public double findTime() {
		// Checks the time taken from Source to all cannons and the Destination, esp if there are no cannons
    	for(int i = 1; i < n; i++) {
    		this.adjMat[0][i] = this.coordinates[0].distance(this.coordinates[i]) / 5;
    	}

    	for(int i = 1; i < n - 1; i++) {
    		for(int j = 1; j < n; j++) {
    			if(i == j) {
    				continue;
    			} else {
    				double distance = this.coordinates[i].distance(this.coordinates[j]);
    				this.adjMat[i][j] = Math.min((Math.abs(distance - 50) / 5.0) + 2, distance / 5.0);
    			}
    		}
    	}

    	floydWarshall();

    	return this.adjMat[0][n - 1];
	}

	public void floydWarshall() {
		for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
                }
            }
    	}
	}

}


class Coordinate {
	private final double x;
	private final double y;

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// Euclidean distance
	public double distance(Coordinate c) {
		return Math.sqrt(Math.pow(this.x - c.x, 2) + Math.pow(this.y - c.y, 2));
	}


	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}