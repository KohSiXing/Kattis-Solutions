import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class KattisQuest {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int N = sc.nextInt();
            sc.nextLine();

            TreeMap<Integer, PriorityQueue<Gold>> quests = new TreeMap<>();

            while(N-- > 0) {
                String command = sc.next();

                if(command.equals("add")) {
                    int E = sc.nextInt();
                    int G = sc.nextInt();
                    PriorityQueue<Gold> pq;
                    if(quests.containsKey(E)) {
                        pq = quests.get(E);
                        pq.add(new Gold(G));
                        quests.put(E, pq);
                    } else {
                        pq = new PriorityQueue<>();
                        pq.add(new Gold(G));
                        quests.put(E, pq);
                    }
                } else {
                    int X = sc.nextInt();
                    long goldEarned = 0;

                    while(X > 0) {
                        PriorityQueue<Gold> pq;
                        Integer key = quests.floorKey(X); //Get the largest possible key <= X

                        if(key != null) {
                            pq = quests.get(key);
                            goldEarned += pq.poll().getGold();
                            X -= key;

                            if(pq.size() == 0) { // Remove key if pq of gold is empty
                                quests.remove(key);
                            }

                        } else {
                           break;
                        }
                    }

                    System.out.println(goldEarned);
                }

                sc.nextLine();
            }

    }
}

class Gold implements Comparable<Gold> {
    private final int gold;

    public Gold(int gold) {
        this.gold = gold;
    }

    public int getGold() {
        return this.gold;
    }

    @Override
    public int compareTo(Gold g) {
        return g.gold - this.gold;
    }

}

