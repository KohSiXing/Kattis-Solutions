import java.util.*;
import java.io.*;

public class VirtualFriends {

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

            int cases = Integer.parseInt(br.readLine());

            for(int i = 0; i < cases; i++) {
                  int F = Integer.parseInt(br.readLine());
                  Map<String, Integer> hm = new HashMap<>();
                  UFDS ufds = new UFDS(100000);
                  int counter = 0;
                  for(int f = 0; f < F; f++) {
                        String[] inputs = br.readLine().split(" ");
                        String first = inputs[0];
                        String second = inputs[1];

                        int x,y;
                        if (hm.containsKey(first)) {
                             x = hm.get(first);
                        } else {
                             x = counter;
                             hm.put(first, x);
                             counter++;
                        }

                        if (hm.containsKey(second)) {
                             y = hm.get(second);
                        } else {
                             y = counter;
                             hm.put(second, y);
                             counter++;
                        }

                        ufds.unionSet(x,y);
                        pw.println(ufds.getSize(ufds.findSet(x)));
                        pw.flush();
                  }
            }

    }

}

class UFDS {
    protected int [] p;
    protected int [] rank;
    protected int [] size; 

    public UFDS(int n) {
        this.p = new int[n];
        this.rank = new int[n];
        this.size = new int[n];
        for(int i = 0; i < n; i ++) {
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

}


