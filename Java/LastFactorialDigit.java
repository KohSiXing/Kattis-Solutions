import java.util.*;
import java.util.stream.IntStream;

public class LastFactorialDigit {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int cases = sc.nextInt();
		List<Integer> intList = new ArrayList<>();
		int i = 0;
		
		while(i < cases) {
			int n = sc.nextInt();
			int ans = (IntStream.rangeClosed(1,n)
					 .reduce(1,(x,y) -> x * y)) % 10;
			intList.add(ans);
			i++;
		}

		intList.stream()
				.forEach(System.out::println);
	}

}