import java.io.*;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class Akcija {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int remain = n % 3;
		List<Integer> intList = new ArrayList<>();
		
		long sum = 0;

		for(int i = 0; i < n; i++) {
			intList.add(Integer.parseInt(br.readLine()));
		}

		Collections.sort(intList);

		for(int i = intList.size() - 1; i > remain; i-=3){
			sum += intList.get(i - 1);
            sum += intList.get(i);
        }

		for(int i = 0; i < remain; i++) {
			sum += intList.get(i);
		}
		System.out.println(sum);
	}
}

