import java.io.*;
import java.util.*;

public class IntegerList {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		
		int sequences = Integer.parseInt(br.readLine());
		
		while(sequences-- > 0) {
			char[] operations = br.readLine().toCharArray();
			int size = Integer.parseInt(br.readLine());
			StringBuffer sb = new StringBuffer(br.readLine());

			String [] input;
			if(size > 0) {
				input = sb.substring(1, sb.length()-1).split(",");
			} else {
				input = new String[0];
			}
			
			boolean reversed = false;
			boolean error = false;

			ArrayDeque<Integer> dq = new ArrayDeque<>();

			for(int i = 0; i < input.length; i++) {
				dq.add(Integer.parseInt(input[i]));
			}

			for(char o : operations) {
				if(o == 'R') {
					reversed = !reversed;
				} else {
					if(size == 0) {
						error = true;
						break;
					} 

					if(!reversed) {
						dq.removeFirst();
					} else {
						dq.removeLast();
					}
					size--;
				}
			}

			if(error) {
				pw.println("error");
			} else if(size != 0) {
				pw.print("[");
				for(int i = 0; i < size-1; i++) {
					if(!reversed) {
						pw.print(dq.removeFirst() + ",");
					} else {
						pw.print(dq.removeLast() + ",");
					}
				}

				if(!reversed) {
					pw.print(dq.removeFirst() + "]\n");
				} else {
					pw.print(dq.removeLast() + "]\n");
				}
			} else {
				pw.println("[]");
			}
			

			pw.flush();
		}

	}
}


