import java.io.*;

public class Ladice {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String [] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int L = Integer.parseInt(inputs[1]);

        UFDS ufds = new UFDS(L);
        while(N-- > 0) {
            inputs = br.readLine().split(" ");
            int A = Integer.parseInt(inputs[0]) - 1; //zero indexing
            int B = Integer.parseInt(inputs[1]) - 1;

            ufds.unionSet(A, B);
            int parent = ufds.findSet(A);

            if(ufds.getSize(parent) > 0) {
                pw.println("LADICA");
                ufds.decreaseSize(parent);
            } else {
                pw.println("SMECE");
            }
        }
        pw.flush();
    }
}

class UFDS {
    private int [] p;
    private int [] rank;
    private int [] size; // Size of set; Decrement one per usage

    public UFDS(int N) {
        this.p = new int[N];
        this.rank = new int[N];
        this.size = new int[N];
        for(int i = 0; i < N; i ++) {
            p[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    public int findSet(int i) {
        if(p[i] == i) {
            return i;
        } else {
            p[i] = findSet(p[i]);
            return p[i];
        }
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if(!isSameSet(i,j)) {
            int x = findSet(i);
            int y = findSet(j);

            if(rank[x] > rank[y]) {
                p[y] = x;
                size[x] += size[y];
            } else {
                p[x] = y;
                if(rank[x] == rank[y]) {
                    rank[y] = rank[y] + 1;
                }
                size[y] += size[x];
            }
        }
    }

    public int getSize(int i) {
        return size[i];
    }

    public void decreaseSize(int i) {
        this.size[i] = this.size[i] - 1;
    }
}
