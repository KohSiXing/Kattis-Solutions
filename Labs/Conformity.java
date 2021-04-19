import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Conformity {

	public static void main(String[] args) {
		    Scanner sc = new Scanner(System.in);

		    int students = sc.nextInt();
		    sc.nextLine();

		    HashMap<String, Integer> hm = new HashMap<>();

		    int max = 1;
		    for(int i = 0; i < students; i++) {
		    	String [] input = sc.nextLine().split(" ");
		    	int [] combi = Arrays.stream(input)
		    							.mapToInt(s -> Integer.parseInt(s))
		    							.toArray();

		    	Radix radix = new Radix(combi);
		    	int exp = 1;
			    int maxDigits = 3; // Given in question, code ranges from 100 - 499
	        	while(maxDigits-- > 0) {
		            combi = radix.sort(exp);
		            exp *= 10;
	        	}

	        	Student student = new Student(i, combi);

	        	if(hm.get(student.getHashCode()) != null) {
	        		hm.put(student.getHashCode(), hm.get(student.getHashCode()) + 1);
	        		max = (hm.get(student.getHashCode()) > max) ? hm.get(student.getHashCode()) : max; 
	        	} else {
	        		hm.put(student.getHashCode(), 1);
	        	}

		    }

		    int finalmax = max;
		    List<Integer> values = new ArrayList<>(hm.values());
		    int ans = values.stream()
		    				.filter(i -> i == finalmax)
		    				.reduce(0, (x, y) -> x + y);

		    System.out.println(ans);
 	}
}

class Student {
	private final int id;
	private final int [] combi;
	private String hashcode;

	public Student(int id, int [] combi) {
		this.id = id;
		this.combi = combi;
		StringBuffer hc = new StringBuffer();
		for(int i = 0; i < 5; i++) {
			hc.append(combi[i]);
		}

		//Only need 1 time convert toString() -> O(n) only incurred once
		this.hashcode = hc.toString(); 
	}

	public String getHashCode() {
		return this.hashcode;
	}
}

// Use Radix since all values are int to get O(n) since TimSort is O(n log n)
class Radix {
    private int [] integers;

    public Radix(int [] integers) {
        this.integers = integers;
    }

    public int[] sort(int exp) {
        int [] count = new int[10];
        int [] tmp = new int[integers.length];

        for(int i : integers) {
            count[(i/exp) % 10]++;
        }

        for (int i = 1; i < 10; i++)
            count[i] += count[i - 1];

        for (int i = integers.length - 1; i >= 0; i--) {
            tmp[count[(integers[i] / exp) % 10] - 1] = integers[i];
            count[(integers[i] / exp) % 10]--;
        }

        for (int i = 0; i < integers.length; i++){
            integers[i] = tmp[i];
        }

        return this.integers;
    }
}