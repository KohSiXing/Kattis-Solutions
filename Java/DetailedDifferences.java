import java.util.*;

public class DetailedDifferences {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.nextLine();
		

		for(int i = 0; i < n; i++) {
			StringBuffer sb = new StringBuffer();

			String first = sc.nextLine();
			
			String second = sc.nextLine();

			for(int j = 0; j < first.length(); j++) {
				if(first.charAt(j) == second.charAt(j)) {
					sb.append(".");
				} else {
					sb.append("*");
				}
			}

			System.out.println(first);
			System.out.println(second);
			System.out.println(sb.toString() + "\n");


		}
	}

}