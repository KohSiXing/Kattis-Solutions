import java.util.*;

public class Atori {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		String string = sc.nextLine();

		List<String> names = Arrays.asList(string.split("-"));

		String name = names.stream()
			 .reduce("", (x,y) -> x + y.substring(0,1));

		System.out.println(name);
	}

}