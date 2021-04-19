import java.io.*;
import java.util.*;
import java.util.stream.*;

public class WhatDoesTheFoxSay {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int cases = Integer.parseInt(br.readLine());
		
		while(cases-- > 0) {
			String [] inputs = br.readLine().split(" ");
			List<String> sounds = Arrays.asList(inputs);

			String s = "";

			do {
				s = br.readLine();
				String string  = s.split(" ")[2];
				sounds = sounds.stream()
							   .filter(str -> !str.equals(string))
							   .collect(Collectors.toList());
			} while (!s.equals("what does the fox say?"));
			
			for(String sound : sounds) {
				pw.print(sound + " ");
			}
			pw.println();
			pw.flush();
		}

	}
}


