import java.io.*;

import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder result = new StringBuilder();

        int t = Integer.parseInt(buffer.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(buffer.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(buffer.readLine());
            Set<String> set = new HashSet<>();
            int idx = l - f, cnt = 0;
            while (n-- > 0) {
                String tail = st.nextToken().substring(idx);
                if (set.contains(tail)) {
                    set.remove(tail);
                    cnt++;
                } else set.add(tail);
            }

            result.append(cnt).append("\n");
        }
        System.out.println(result);
    }
}