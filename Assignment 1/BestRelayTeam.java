import java.util.*;

public class BestRelayTeam {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int runners = sc.nextInt();
		sc.nextLine();

		int runner = 0;
		List<Runner> rlist = new ArrayList<>();
		while(runner < runners) {
			String string = sc.nextLine();
			String [] data = string.split(" ");

			String name = data[0];
			double first, others;
			first = Double.parseDouble(data[1]);
			others = Double.parseDouble(data[2]);

			rlist.add(new Runner(name, first, others));
			
			runner++;
		}

		Collections.sort(rlist);

		double finalTime = 100000;
		List<String> finalTeam = new ArrayList<>();
		for(int i = 0; i < runners; i++) {
			double runTime = rlist.get(i).getFirst();
			List<String> currentTeam = new ArrayList<>();
			currentTeam.add(rlist.get(i).toString());

			for(int j = 0; currentTeam.size() < 4; j++) {
				if(i == j) {
					continue;
				} else {
					runTime += rlist.get(j).getOthers();
					currentTeam.add(rlist.get(j).toString());
				}
			}

			if(runTime < finalTime) {
				finalTime = runTime;
				finalTeam = currentTeam;
			}
		}

		System.out.println(finalTime);
		finalTeam.stream()
					.forEach(System.out::println);
	}

}


class Runner implements Comparable<Runner> {
	private final String name;
	private final Double first;
	private final Double others;

	public Runner(String name, double first, double others) {
		this.name = name;
		this.first = first;
		this.others = others;
	}

	public double getFirst() {
		return this.first;
	}

	public double getOthers() {
		return this.others;
	}

	@Override
	public int compareTo(Runner r) {
		if(this.others != r.others) {
			return this.others.compareTo(r.others);
		} else if(this.first != r.first) {
			return this.first.compareTo(r.first);
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		return this.name;
	}
}