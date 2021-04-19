import java.util.*;

public class WeakVertices {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();
            sc.nextLine();

            while(n != -1) {
                int[][] adjMatrix = new int[n][n];

                for(int r = 0; r < n; r++) {
                    for(int c = 0; c < n; c++) {
                        adjMatrix[r][c] = sc.nextInt();
                    }
                    sc.nextLine();
                }

                for(int r = 0; r < n; r++) {
                    if(!isTriangle(r, n, adjMatrix)) {
                        System.out.print(r + " ");
                    } 
                }
                System.out.println();

                // Read in the next test case
                n = sc.nextInt();
                sc.nextLine();
            } 
    }

    /*
        To form a triangle
        a - b
        a - c
        b - c
        Must be present
    */
    public static boolean isTriangle(int a, int n, int[][] adjMatrix) {
        for (int b = 0; b < n; b++) {
            if (adjMatrix[a][b] == 1) {
                for (int c = b + 1; c < n; c++) {
                    if (adjMatrix[a][c] == 1 && adjMatrix[b][c] == 1) {
                        return true;
                    }
                }
            }
        }
    
        return false;
    }
}



