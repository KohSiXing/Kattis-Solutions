import java.io.*;
import java.lang.Math;

public class GCPC {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        String [] inputs = br.readLine().split(" ");
        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        AVL avl = new AVL();

        Team [] teams = new Team[n];
        for(int i = 0; i < n; i++) {
            teams[i] = new Team(i + 1);
            avl.insert(teams[i]);
        }

        while(m-- > 0) {
            inputs = br.readLine().split(" ");
            int t = Integer.parseInt(inputs[0]) - 1; // 0 - indexing
            int p = Integer.parseInt(inputs[1]);

            // Update the Node (Team) which just scored
            avl.delete(teams[t]);
            teams[t].solve(p);
            avl.insert(teams[t]);

            pw.println(avl.rank(teams[0]));
        }
        pw.flush();
    }
}

class AVL {
    Node root;

    public AVL() {
        root = null;
    }

    public int bf(Node T) {
        if(T == null) {
            return 0;
        }

        int left = T.left == null ? 0 : T.left.height;
        int right = T.right == null ? 0 : T.right.height;

        return left - right;
    }


    public Team findMin(Node T) {
        if (T.left == null) {
            return T.key;
        } else {
            return findMin(T.left);
        }
    }

    public void delete(Team v) { root = delete(root, v); }

    public Node delete(Node T, Team v) {
        if (T == null) {
            return T;              // cannot find the item to be deleted
        }

        int score = v.compareTo(T.key);
        if (score > 0) {
            T.right = delete(T.right, v);
        } else if (score < 0) {
            T.left = delete(T.left, v);
        } else {
            if (T.left == null && T.right == null) {
                T = null;
            } else if (T.left == null) {
                T.right.parent = T.parent;
                T = T.right;
            } else if (T.right == null) {
                T.left.parent = T.parent;
                T = T.left;
            } else {
                Team successorV = successor(v);
                T.key = successorV;
                T.right = delete(T.right, successorV);
            }
        }

        if(T != null) {
            T.updateInfo();
            T = rebalance(T);
        }

        return T;
    }

    public void insert(Team v) { root = insert(root, v); }

    public Node insert(Node T, Team v) {
        if (T == null) {
            return new Node(v);
        }

        int score = v.compareTo(T.key);
        if (score > 0) {
            T.right = insert(T.right, v);
            T.right.parent = T;
        } else {
            T.left = insert(T.left, v);
            T.left.parent = T;
        }


        T.updateInfo();
        T = rebalance(T);

        return T;
    }

    public int rank(Team v) {
        return rank(root, v);
    }

    public int rank(Node T, Team v) {
        int score = v.compareTo(T.key);
        if (score < 0) {
            return rank(T.left, v);
        } else if (score > 0) {
            int leftSize = T.left == null ? 0 : T.left.size;
            return leftSize + rank(T.right, v) + 1;
        } else {
            int leftSize = T.left == null ? 0 : T.left.size;
            return leftSize + 1;
        }
    }

    public Node rebalance(Node T) {
        int bf = bf(T);

        if(bf <= -2) {
            if(bf(T.right) > 0) {
                T.right = rotateRight(T.right);
            }
            T = rotateLeft(T);
        } else if (bf >= 2) {
            if(bf(T.left) < 0) {
                T.left = rotateLeft(T.left);
            }
            T = rotateRight(T);
        } else {
            ; // pass if -2 < bf < 2
        }

        return T;
    }

    public Node rotateLeft(Node T) {
        if(T.right != null) {
            Node w = T.right;
            w.parent = T.parent;

            if (T.parent == null) {
                root = w;
            }

            T.parent = w;
            T.right = w.left;

            if(w.left != null) {
                w.left.parent = T;
            }

            w.left = T;

            T.updateInfo();
            w.updateInfo();

            return w;
        } else {
            return null;
        }
    }

    public Node rotateRight(Node T) {
        if(T.left != null) {
            Node w = T.left;
            w.parent = T.parent;

            if (T.parent == null) {
                root = w;
            }

            T.parent = w;
            T.left = w.right;

            if(w.right != null) {
                w.right.parent = T;
            }

            w.right = T;

            T.updateInfo();
            w.updateInfo();

            return w;
        } else {
            return null;
        }
    }

    public Node search(Node T, Team v) {
        if (T == null) {
            return null;                     // not found
        }

        int score = v.compareTo(T.key);
        if (score == 0) {
            return T;                        // found
        } else if (score > 0) {
            return search(T.right, v);       // search to the right
        } else {
            return search(T.left, v);        // search to the left
        }
    }

    public Team successor(Team v) {
        Node vPos = search(root, v);
        return vPos == null ? null : successor(vPos);
    }

    public Team successor(Node T) {
        if (T.right != null) { // this subtree has right subtree
            return findMin(T.right);  // the successor is the minimum of right subtree
        } else {
            Node parent = T.parent;
            Node current = T;

            while ((parent != null) && (current == parent.right)) {
                current = parent; // continue moving up
                parent = current.parent;
            }
            return parent == null ? null: parent.key;  // this is the successor of T
        }
    }

}

class Node {
    Team key;
    Node left;
    Node right;
    Node parent;
    int height;
    int size;

    public Node(Team key) {
        this.key = key;
        this.height = 0;
        this.size = 1;
    }

    // Math.Max won't work directly since null will cause an error
    public void updateInfo() {
        if (this.left == null && this.right == null) {
            this.height = 0;
            this.size = 1;
        } else if (this.left == null) {
            this.height = this.right.height + 1;
            this.size = this.right.size + 1;
        } else if (this.right == null) {
            this.height = this.left.height + 1;
            this.size = this.left.size + 1;
        } else {
            this.height = Math.max(this.left.height, this.right.height) + 1;
            this.size = this.left.size + this.right.size + 1;
        }
    }

    @Override
    public String toString() {
        return "" + this.key;
    }

}

class Team implements Comparable<Team> {
    private final int id;
    private int solved;
    private int penalty;

    public Team(int id) {
        this.id = id;
        this.solved = 0;
        this.penalty = 0;
    }

    public void solve(int penalty) {
        this.solved++;
        this.penalty += penalty;
    }

    @Override
    public int compareTo(Team t) {
        if(this.solved != t.solved) {
            return t.solved - this.solved; // The one with more problems solved wins
        } else if (this.penalty != t.penalty) {
            return this.penalty - t.penalty; // The one with less penalty wins
        } else {
            return this.id - t.id; // If all else the same, the one with lower ID wins
        }
    }

    @Override
    public String toString() {
        return id + "";
    }
}