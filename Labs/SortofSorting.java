import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class SortofSorting {

	public static void main(String[] args) throws IOException {
		    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

			int n = Integer.parseInt(br.readLine());

			while(n > 0) {
				List<Student> studentList = IntStream.rangeClosed(1,n)
													 .mapToObj(i -> {
													 	try {
													 		return new Student(i, br.readLine());
													 	} catch(Exception e) {
													 		return new Student(i, "");
													 	}
													 })
													 .filter(Predicate.not(s -> s.toString().equals("")))
													 .collect(Collectors.toList());

				Collections.sort(studentList); 

				studentList.stream().forEach(pw::println);
				pw.println("");
				pw.flush();

				n = Integer.parseInt(br.readLine());
			}

			

	        
	}
}


class Student implements Comparable<Student> {
	
	private final int id;
	private final String name; 
	
	public Student(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int compareTo(Student s) {
		String n1 = this.name.substring(0,2);
		String n2 = s.name.substring(0,2);
		if(!(n1.equals(n2))) {
			return n1.compareTo(n2);
		} else {
			return this.id - s.id;
		}
		
	}

	@Override
	public String toString() {
		return this.name;
	}
}