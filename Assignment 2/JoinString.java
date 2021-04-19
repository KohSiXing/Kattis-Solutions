import java.io.*;
import java.util.*;

public class JoinString {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		HashMap<Integer, Node> hm = new HashMap<>();

		for(int i = 0; i < n; i++) {
			hm.put(i, new Node(br.readLine()));
		}

		int j = 0;
		int index = 0;
		while(j < n-1) {
			String input = br.readLine();
			String [] tokens = input.split(" ");

			// value - 1 to convert to zero indexing
			int first = Integer.parseInt(tokens[0]) - 1;
			int second = Integer.parseInt(tokens[1]) - 1;

			hm.get(first).addNeighbour(second);
			index = first;
			j++;
		}

		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		printNodes(pw, hm, index);
		pw.flush();
	}

	public static void printNodes(PrintWriter pw, HashMap<Integer, Node> hm, int index) {
		pw.print(hm.get(index).toString());
		for(int i : hm.get(index).getNeighbours()) {
			printNodes(pw, hm, i);
		}
	}

}

class Node {
	private final String name;
	private List<Integer> list = new ArrayList<>();

	public Node(String name) {
		this.name = name;
	}

	public void addNeighbour(int i) {
		list.add(i);
	}

	public List<Integer> getNeighbours() {
		return this.list;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

