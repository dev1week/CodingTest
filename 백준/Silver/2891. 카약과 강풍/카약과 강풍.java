import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int s = Integer.parseInt(tokens.nextToken());
        int r = Integer.parseInt(tokens.nextToken());


        int[] damagedTeam = Arrays.stream(buffer.readLine().split("\\s++")).mapToInt(Integer::parseInt).toArray();

        int[] redundantTeam = Arrays.stream(buffer.readLine().split("\\s++")).mapToInt(Integer::parseInt).toArray();


        int[] counts = new int[n + 1];

        Arrays.fill(counts, 1);
        counts[0] = 0;

        for (int d : damagedTeam) {
            counts[d]--;
        }

        for (int rt : redundantTeam) {
            counts[rt]++;
        }

        int result = 0;
        for (int team = 1; team <= n; team++) {
            if (counts[team] == 0) {
                if (team>1&&counts[team - 1] == 2 ) {
                    counts[team - 1]--;
                    counts[team]++;
                } else if (team < n&&counts[team + 1] == 2) {
                    counts[team + 1]--;
                    counts[team]++;
                } else {
                    result++;
                }
            }
        }
        System.out.println(result);
    }
}
