import java.util.*;

public class TakeTwoStones {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		

		if(n % 2 == 1) {
			System.out.println("Alice");
		} else {
			System.out.println("Bob");
		}
	}

}