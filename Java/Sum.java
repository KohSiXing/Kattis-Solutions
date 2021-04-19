import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Sum {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			int n = Integer.parseInt(br.readLine());

			List<Summations> list = new ArrayList<>();
			for(int i = 1; i <= n; i++) {
				String input = br.readLine();
				String [] tokens = input.split(" ");
				list.add(new Summations(i, Integer.parseInt(tokens[1])));
			}
			
			list.stream()
				.forEach(System.out::println);
			
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}

class Summations {
	private final int id;
	private final int s1;
	private final int s2;
	private final int s3;

	Summations(int id, int n) {
		this.id = id;
		this.s1 = IntStream.rangeClosed(1, n)
						  .sum();

		this.s3 = IntStream.rangeClosed(1,n)
							.map(i -> 2 * i)
							.sum();
		this.s2 = this.s3 - n;

	}

	@Override
	public String toString() {
		return this.id + " " + this.s1 + " " + this.s2 + " " + this.s3;
	}
}