import java.util.*;

public class NumberFun {

	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		int cases = sc.nextInt();
		sc.nextLine();
		
		List<String> outcomes = new ArrayList<>();
		Arithmetic ar;

		int i = 0;
		while(i < cases) {
			String string = sc.nextLine();
			String [] integers = string.split(" ");

			int first,second,soln;
			first = Integer.parseInt(integers[0]);
			second = Integer.parseInt(integers[1]);
			soln = Integer.parseInt(integers[2]);

			boolean flag = false;
			if(first < second) {
				ar = new Arithmetic(first, second, soln);
				flag = ar.operate();
			} else {
				ar = new Arithmetic(second, first, soln);
				flag = ar.operate();
			}

			if(flag) {
				outcomes.add("Possible");
			} else {
				outcomes.add("Impossible");
			}

			i++;
		}

		outcomes.stream()
				.forEach(System.out::println);

	}


}

class Arithmetic {
	private final double small;
	private final double big;
	private final int soln;

	Arithmetic(double small, double big, int soln) {
		this.small = small;
		this.big = big;
		this.soln = soln;
	}

	boolean operate() {
		if (this.big + this.small == this.soln) {
			return true;
		} else if (this.big - this.small == this.soln) {
			return true;
		} else if (this.big * this.small == this.soln) {
			return true;
		} else if (this.big / this.small == this.soln) {
			return true;
		} else {
			return false;
		}
		
	}

}