import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class ShatteredCake {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int w = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());
		List<CakeSlice> cakeList = IntStream.rangeClosed(1,n)
													 .mapToObj(i -> {
													 	try {
													 		return new CakeSlice(i, br.readLine());
													 	} catch(Exception e) {
													 		return new CakeSlice(i, "0 0");
													 	}
													 })
													 .collect(Collectors.toList());

		int totalArea = cakeList.stream().map(s -> s.area()).reduce(0,(x, y) -> x + y, Integer::sum);
		System.out.println(totalArea / w);
	}
}

class CakeSlice {

	public final int slice;
	public final int area;

	public CakeSlice(int slice, String string) {
		String [] tokens = string.split(" ");
		this.slice = slice;
		this.area = Integer.parseInt(tokens[0]) * Integer.parseInt(tokens[1]);
	}

	public int area() {
		return this.area;
	}

	@Override
	public String toString() {
		return "Cake Slice " + this.slice + " with area of " + this.area;
	}
}
