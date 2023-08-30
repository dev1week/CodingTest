
import java.io.*;
import java.util.*; 


public class Main {
	static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static int n;
    static boolean[] visited;
    static int[][] map;
    static long result_min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        
        n = Integer.parseInt(buffer.readLine());
        

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            tokens = new StringTokenizer(buffer.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(tokens.nextToken());
            }
        }

        for(int i=0; i<n; i++) {
            visited = new boolean[n];
            visited[i] = true;
            dfs(i, i, 0);
        }
        System.out.println(result_min);
    }

    public static void dfs(int start, int cur, int total){
        if (allVisited()) {
            if(map[cur][start]!=0){
                result_min = Math.min(result_min, total+map[cur][0]);
            }
            return;
        }

        for(int next=1; next<n; next++){
            if (!visited[next] && map[cur][next] != 0) {
                visited[next] = true;
                dfs(start, next, total + map[cur][next]);
                visited[next] = false;
            }
        }
    }

    public static boolean allVisited() {
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

}