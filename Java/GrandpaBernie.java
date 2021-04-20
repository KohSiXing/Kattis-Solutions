import java.util.*;
import java.io.*;

public class GrandpaBernie {

    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

            int n = Integer.parseInt(br.readLine());

            Bernie bernie = new Bernie(n);

            for(int i = 0; i < n; i++) {
                String [] inputs = br.readLine().split(" ");
                String country = inputs[0];
                int year = Integer.parseInt(inputs[1]);

                bernie.addCountry(country, year);
                
            }

            int q = Integer.parseInt(br.readLine());

            for(int i = 0; i < q; i++) {
                String [] inputs = br.readLine().split(" ");
                String country = inputs[0];
                int k = Integer.parseInt(inputs[1]) - 1;
                
                pw.println(bernie.getYear(country, k));
            }
            pw.flush();

    }

}

class Bernie {

      Map<String, List<Integer>> hm;
      Set<String> sortedCountries;

      public Bernie(int n) {
            this.hm = new HashMap<>(n);
            this.sortedCountries = new HashSet<>(n);
      }

      public void addCountry(String country, int year) {
            List<Integer> years = hm.get(country);
            if(years == null) {
                  years = new ArrayList<>();
                  hm.put(country, years);
            }
            years.add(year);
      }

      public int getYear(String country, int k) {
            List<Integer> years = hm.get(country);
            if(!sortedCountries.contains(country)) {
                  Collections.sort(years);
                  sortedCountries.add(country);
            }

            return years.get(k);
      }
}