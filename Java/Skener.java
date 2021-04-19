import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Skener {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			String messages = br.readLine();
			String [] tokens = messages.split(" ");
			int R = Integer.parseInt(tokens[0]);
			int C = Integer.parseInt(tokens[1]);
			int Zr = Integer.parseInt(tokens[2]);
			int Zc = Integer.parseInt(tokens[3]);


			int count = 0;

			List<String> results = new ArrayList<>();
			while(count < R) {
				StringBuilder message = new StringBuilder(br.readLine());

				StringBuilder sb = new StringBuilder("");
				for(int i = 0; i < C; i++) {
					String s = "" + message.charAt(i);
					s = s.repeat(Zc);
					sb.append(s);
				}

				results.addAll(IntStream.rangeClosed(1,Zr)
					 	 .mapToObj(i -> new String(sb.toString()))
					 	 .collect(Collectors.toList()));

				count++;
			}

			results.stream()
					.forEach(System.out::println);

		} catch(Exception e) {
			System.out.println(e);
		}
	}
}