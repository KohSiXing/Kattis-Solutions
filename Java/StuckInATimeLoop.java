import java.util.*;
import java.util.stream.IntStream;

public class StuckInATimeLoop {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int count = sc.nextInt();

		IntStream.rangeClosed(1,count)
				 .mapToObj(i -> new String(i + " Abracadabra"))
				 .forEach(System.out::println);
	}

}