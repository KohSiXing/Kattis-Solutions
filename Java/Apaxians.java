import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Apaxians {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		try {
			StringBuilder message = new StringBuilder(br.readLine());
			StringBuilder result = new StringBuilder();
			
			char prev = message.charAt(0);
			result.append(prev);
			for(int i = 1; i < message.length(); i++) {
				if(prev != message.charAt(i)) {
					prev = message.charAt(i);
					result.append(message.charAt(i));
				} 
			}

			
			System.out.println(result.toString());
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}