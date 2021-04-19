import java.io.*;

public class Teque2B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(br.readLine());

        Teque teque = new Teque();

        for(int i = 0; i < n; i++) {
            String [] tokens = br.readLine().split(" ");
            String command = tokens[0].strip();
            int value = Integer.parseInt(tokens[1]);

            if(command.equals("push_back")){
                teque.push_back(value);
            } else if (command.equals("push_front")) {
                teque.push_front(value);
            } else if (command.equals("push_middle")) {
                teque.push_middle(value);
            } else {
                pw.println(teque.get(value));
                pw.flush();
            }

        }
    }
}

class Teque {
    private Deque left;
    private Deque right;

    public Teque() {
        left = new Deque();
        right = new Deque();
    }

    public void push_back(int i) {
        right.addLast(i);
        balance();
    }

    public void push_front(int i) {
        left.addFirst(i);
        balance();
    }

    public void push_middle(int i) {
       left.addLast(i);
       balance();
    }

    public void balance() {
        while(left.size() > right.size()) {
            right.addFirst(left.removeLast());
        }
        while(left.size() < right.size()) {
            left.addLast(right.removeFirst());
        }
    }

    public int get(int idx) {
        if(idx >= left.size()) {
            idx -= left.size();
            return right.get(idx);
        } else {
            return left.get(idx);
        }
    }
}

class Deque {

    private final int INIT_SIZE = 500000; // since the problem is 10^6, 2 deques at least (10 ^ 6) / 2
    private int [] integers;
    private int front;
    private int rear;
    private int size; // Number of elements in the array

    public Deque() {
        integers = new int[INIT_SIZE];
        front = -1;
        rear = 0;
        size = 0;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void addFirst(int i) {
        if(isEmpty()) {
            front = 0;
            rear = 0;
        } else if (front == 0) {
            front = INIT_SIZE - 1;
        } else {
            front--;
        }
        size++;
        integers[front] = i;
    }

    public void addLast(int i) {
        if(isEmpty()) {
            front = 0;
            rear = 0;
        } else {
            rear = (rear + 1) % INIT_SIZE;
        }
        size++;
        integers[rear] = i;
    }

    public Integer removeFirst() {
        if(isEmpty()) {
            return null;
        }

        int result = integers[front];
        if (front == rear) {
            front = -1;
            rear = 0;
        } else {
            front = (front + 1) % INIT_SIZE;
        }
        size--;
        return result;
    }

    public Integer removeLast() {
        if(isEmpty()) {
            return null;
        }

        int result = integers[rear];
        if (front == rear) {
            front = -1;
            rear = 0;
        } else if (rear == 0)
            rear = INIT_SIZE - 1;
        else
            rear = rear - 1;

        size--;
        return result;
    }

    public Integer get(int index) {
        if(isEmpty()) {
            return null;
        } else {
            index = Math.floorMod(index + front, INIT_SIZE);
            return integers[index];
        }
    }

    public int size() {
        return size;
    }
}
