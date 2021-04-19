import java.io.*;
import java.util.*;
import java.util.stream.*;

public class ExactlyElectrical {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Coordinates source = new Coordinates(br.readLine());
		Coordinates destination = new Coordinates(br.readLine());
		int t = Integer.parseInt(br.readLine());
		int d = source.distanceTo(destination);

		if(t >= d && (t - d) % 2 == 0) {
			System.out.println("Y");
		} else {
			System.out.println("N");
		}
	}
}

class Coordinates {

	public final int x;
	public final int y;

	public Coordinates(String string) {
		String [] tokens = string.split(" ");
		x = Integer.parseInt(tokens[0]);
		y = Integer.parseInt(tokens[1]);
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	/** Cannot use Euclidean distance since no diagonals
	 *  e.g from (3,3) to (4,4)
	 * 	    (3,3) -> (3,4) -> (4,4) 
	 *      not (3,3) -> (4,4) directly
	 */
	public int distanceTo(Coordinates c) {
		return Math.abs(this.x - c.x) + Math.abs(this.y - c.y);
	}

	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
