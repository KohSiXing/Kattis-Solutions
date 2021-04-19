import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class CoconutSplat {

	public static void main(String[] args) throws IOException {
		    Scanner sc = new Scanner(System.in);

			int syllabus = sc.nextInt();
			int players = sc.nextInt();
			sc.nextLine();

			// 0-1 belongs to player 1, 2-3 to player 2 (Use int division to get player no)
			List<Hand> hands = IntStream.range(0, players * 2)
										 .mapToObj(i -> new Hand(i))
										 .collect(Collectors.toList());

			int idx = 0;
			int noOfHands = hands.size();
			while(noOfHands > 1) {
				int s = 1;
				while(s <= syllabus) {
					if(idx >= hands.size()) {
						idx = idx % hands.size();
					}

					if(hands.get(idx).getState() == 4) {
						idx++;
						continue;
					} else if(hands.get(idx).getState() == 1) {
						if(s == syllabus) {
							hands.set(idx, hands.get(idx).setState(2));
							hands.set(idx + 1, hands.get(idx + 1).setState(2));
						} else {
							idx += 2;
						}
					} else if(hands.get(idx).getState() == 2) {
						if(s == syllabus) {
							hands.set(idx, hands.get(idx).setState(3));
						} 
						idx++;
					} else {
						if(s == syllabus) {
							hands.set(idx, hands.get(idx).setState(4));
							noOfHands--;
						} 
						idx++;
					}
					s++;
				}

			}
			
			int winner = hands.stream()
				 .filter(h -> h.getState() != 4)
				 .findFirst()
				 .get().getId();

			winner = (winner / 2) + 1;

			System.out.println(winner);
				
	        
	}
}


/**
 * Try Hand instead of Player - Player need right and left
 * Assumption is that winner will only win with 1 hand
 */
class Hand {
	private final int id;
	private int state;

	public Hand(int id) {
		this.id = id;
		this.state = 1;
	}

	public int getState() {
		return this.state;
	}

	public Hand setState(int state) {
		this.state = state;
		return this;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "" + id;
	}

}