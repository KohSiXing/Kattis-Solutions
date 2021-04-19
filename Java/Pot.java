import java.util.*;

public class Pot {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int cases = sc.nextInt();
		List<Integer> intList = new ArrayList<>();
		int i = 0;
		
		while(i < cases) {
			int n = sc.nextInt();
			int pow = n % 10;
			n = (int)(n / 10);

			intList.add((int) Math.pow(n,pow));
			i++;
		}

		int ans = intList.stream()
						 .reduce(0,(x,y) -> x + y);

		System.out.println(ans);
				
	}

}