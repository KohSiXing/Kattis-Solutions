import java.io.*;
import java.util.*;
import java.util.stream.*;

public class T9Spelling {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] dict = T9Dict();
		//Arrays.asList(dict).stream().forEach(System.out::println);
		try {
			int messages = Integer.parseInt(br.readLine());
			List<String> T9Text = new ArrayList<>();

			int mCount = 0;
			while(mCount < messages) {
				StringBuilder message = new StringBuilder(br.readLine());
				StringBuilder converted = new StringBuilder();

				String prev = "";
				for(int i = 0; i < message.length(); i++) {
					String currentChar = dict[message.charAt(i)];
					if(prev.equals(currentChar.substring(0,1))) {
						converted.append(" " + currentChar);
					} else {
						converted.append(currentChar);
					}
					
					prev = currentChar.substring(currentChar.length() - 1);
				}
				T9Text.add(converted.toString());
				mCount++;
			}

			IntStream.range(0, T9Text.size())
					 .mapToObj(i -> new StringBuilder("Case #")
					 				.append(i + 1)
					 				.append(": ")
					 				.append(T9Text.get(i)).toString())
					 .forEach(System.out::println);

		} catch(Exception e) {
			System.out.println(e);
		}
	}

	public static String [] T9Dict() {
		String [] dict = new String[256];
		dict[32] = "0";
		int currentNum = 2;
		int counter = 0;
		int max;
		for(int i = 97; i < 123; i++) {
			if(currentNum != 7 && currentNum != 9) {
				max = 3;
			} else {
				max = 4;
			}

			// If it hits the max number of keys i.e. 3 for an increment is made
			if(counter >= max) {
				currentNum++;
				counter = 0;
			} 

			String s = "" + currentNum;
			dict[i] = Stream.generate(() -> s)
							.limit(counter + 1)
							.reduce((x,y) -> x + y)
							.get();
			counter++;
		}

		return dict;
	}
}