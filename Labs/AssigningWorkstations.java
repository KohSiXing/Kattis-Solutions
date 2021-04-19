import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class AssigningWorkstations {

    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);

            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();

            Queue<Researcher> researchers = new PriorityQueue<>();

            for(int i = 0; i < n; i++) {
                researchers.add(new Researcher(i+1, sc.nextInt(), sc.nextInt()));
                sc.nextLine();
            }

            Queue<WorkStation> wsq = new PriorityQueue<>();
            int save = 0;
            int rSize = researchers.size();
            for(int i = 0; i < rSize; i++) {
                Researcher researcher = researchers.poll();
                WorkStation ws = null;

                while(wsq.size() > 0 && wsq.peek().getLockTime() < researcher.getArrival()) {
                    wsq.poll();
                }


                if(wsq.size() > 0 && researcher.getArrival() >= wsq.peek().getNextAvailableTime()) {
                    ws = wsq.poll();
                }
               

                if(ws == null) {
                    WorkStation nws = new WorkStation(researcher.getDeparture(), researcher.getDeparture() + m);
                    wsq.add(nws);
                } else {
                    ws.setNextAvailableTime(researcher.getDeparture());
                    ws.setLockTime(researcher.getDeparture() + m);
                    wsq.add(ws);
                    save++;
                }

            }

            System.out.println(save);       
    }
}

class Researcher implements Comparable<Researcher> {
    private final int id;
    private final int arrival;
    private final int departure;

    public Researcher(int id, int arrival, int stay) {
        this.id = id;
        this.arrival = arrival;
        this.departure = arrival + stay;
    }

    public int getArrival() {
        return this.arrival;
    }

    public int getDeparture() {
        return this.departure;
    }

    @Override
    public String toString() {
        return "Researcher " + id + " (" + arrival + " - " + departure + ")";
    }

    @Override
    public int compareTo(Researcher r) {
        if(this.arrival == r.arrival) {
            return this.id - r.id;
        } else {
            return this.arrival - r.arrival;
        }
    }
    
}

class WorkStation implements Comparable<WorkStation> {
    private int nextAvailableTime;
    private int lockTime;

    public WorkStation(int nextAvailableTime, int lockTime) {
        this.nextAvailableTime = nextAvailableTime;
        this.lockTime = lockTime;
    }

    public void setNextAvailableTime(int time) {
        this.nextAvailableTime = time;
    }

    public void setLockTime(int time) {
        this.lockTime = time;
    }

    public int getNextAvailableTime() {
        return this.nextAvailableTime;
    }

    public int getLockTime() {
        return this.lockTime;
    }

    @Override
    public int compareTo(WorkStation s) {
        return this.lockTime - s.lockTime;
    }
    
}
