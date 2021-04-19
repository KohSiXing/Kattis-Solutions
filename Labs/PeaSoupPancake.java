import java.util.*;
import java.util.stream.*;

public class PeaSoupPancake {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int max_rest = sc.nextInt();
		sc.nextLine();

		int rest = 0;
		String result = "Anywhere is fine I guess";
		while(rest < max_rest) {
			int items = sc.nextInt();
			sc.nextLine();
			String rest_name = sc.nextLine();
			List<String> menu = IntStream.rangeClosed(1, items)
										 .mapToObj(i -> new String(sc.nextLine()))
										 .collect(Collectors.toList());

			if(menu.contains("pea soup") && menu.contains("pancakes")) {
				result = rest_name;
				break;
			} 

			rest++;
		}

		System.out.println(result);
	}

}