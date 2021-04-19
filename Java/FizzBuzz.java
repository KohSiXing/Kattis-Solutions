import java.io.*;
import java.util.*;
import java.util.stream.*;

public class FizzBuzz {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			String line = br.readLine();
			String[] input = line.split(" ");
			int X = Integer.parseInt(input[0]);
			int Y = Integer.parseInt(input[1]);
			int N = Integer.parseInt(input[2]);
			for(int i = 1; i <= N; i++) {
				StringBuilder result = new StringBuilder("");
				result.append(FactorX(i, X)).append(FactorY(i, Y));

				if(result.toString().equals("")) {
					System.out.println(i);
				} else {
					System.out.println(result);
				}
			}

		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public static String FactorX(int i, int X) {
		return i % X == 0 ? "Fizz" : "";
	}

	public static String FactorY(int i, int Y) {
		return i % Y == 0 ? "Buzz" : "";
	}

}