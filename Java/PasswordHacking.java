import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class PasswordHacking {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		List<Password> kali = IntStream.rangeClosed(1,n)
										.mapToObj(i -> {
											try {
												return new Password(br.readLine());
											} catch(Exception e) {
												return new Password("metasploit 12.7001");
											}
										}).collect(Collectors.toList());

		Collections.sort(kali);

		int tries = 1;
		double total = 0;
		for(Password k : kali) {
			total += tries * k.prob();
			tries++;
		}

		System.out.println(total);
	}
}

class Password implements Comparable<Password> {
	private final String password;
	private final double prob;

	public Password(String input) {
		String [] tokens = input.split(" ");
		this.password = tokens[0];
		this.prob = Double.parseDouble(tokens[1]);
	}

	public double prob() {
		return this.prob;
	}

	@Override
	public int compareTo(Password p) {
		if(p.prob > this.prob) {
			return 1;
		} else if (this.prob > p.prob) {
			return -1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return "\n The quieter you become, the more you are able to hear\n" + this.password + " : " + this.prob;
	}
}

