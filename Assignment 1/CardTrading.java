import java.io.*;
import java.util.*;
import java.util.stream.*;

public class CardTrading {

	public static void main(String[] args) throws IOException {
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			String [] tokens = input.split(" ");
			int N = Integer.parseInt(tokens[0]);
			int T = Integer.parseInt(tokens[1]);
			int K = Integer.parseInt(tokens[2]);

			List<Integer> deck = new ArrayList<>();

			// Only count the deck if there is one or more cards
			if(N > 0) {
				deck = Arrays.asList(br.readLine().split(" ")).stream()
							 .map(s -> Integer.parseInt(s))
							 .collect(Collectors.toList());
			} 
			
	        // Stores the card value as key and value is a card object for its prices
	        int cValue = 1;
	        List<Card> cards = new ArrayList<>();
	        while(cValue <= T) {
	        	input = br.readLine();
				tokens = input.split(" "); 
	        	long bValue = Long.parseLong(tokens[0]);
	        	long sValue = Long.parseLong(tokens[1]);

	        	cards.add(new Card(cValue, bValue, sValue));
	        	cValue++;
	        }

	        for(int i : deck) {
	        	cards.get(i-1).addCount();
			}

	        long profit = 0;
			Collections.sort(cards);

	        if(N < 1) {
				for(int i = 0; i < K; i++) {
					profit -= cards.get(i).buyPrice();
				}
			} else {
				for(int i = 0; i < K; i++) {
					profit -= cards.get(i).buyPrice();
				}

				for(int i = K; i < cards.size(); i++) {
					profit += cards.get(i).sellPrice();
				}

			}
	        

	        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	        pw.println(profit);
			pw.flush();
	}
}


class Card implements Comparable<Card> {
	// Buy and Sell are relative to the standpoint of Anthony
	private int value; // The number on the card
	private long buy; // How much Anthony pay to buy from Cora for this card
	private long sell; // How much Anthony sells this card
	private int count = 0;
	
	public Card(int value, long buy, long sell) {
		this.value = value;
		this.buy = buy;
		this.sell = sell;
	}

	public int value() {
		return this.value;
	}

	public long mod() {
		return (2 - count) * this.buy + count * this.sell;
	}

	public long buyPrice() {
		return (2 - count) * this.buy;
	}

	public long sellPrice() {
		return count * this.sell;
	}

	public void addCount() {
		this.count++;
	}

	public int getCount() {
		return this.count;
	}

	@Override
	public int compareTo(Card c) {
		return (int) (this.mod() - c.mod());
	}

	@Override
	public String toString() {
		return "" + this.value;
	}
}