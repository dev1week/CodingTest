//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.io.*;
import java.util.*;

class Point{
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private static char GARO = '-';
    private static char SERO = '|';

    private static int[][] dirs1 ={
            {0,-1},
            {0,1}
    };

    private static int[][] dirs2 ={
            {1,0},
            {-1,0}
    };

    private static int n,m;


    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        char[][] map = new char[n][m];
        boolean[][] isVisited = new boolean[n][m];


        for(int x=0; x<n; x++){
            String line = buffer.readLine();
            for(int y=0; y<m; y++){
                map[x][y] = line.charAt(y);
            }
        }


        int result = 0;
        for(int x=0; x<n; x++){
            for(int y=0; y<m; y++){
                if(isVisited[x][y])continue;
                if(map[x][y]==GARO){
                    bfs(isVisited, dirs1, new Point(x,y), map);
                }else if(map[x][y]==SERO){
                    bfs(isVisited, dirs2, new Point(x,y), map);
                }
                result++;
            }
        }

        System.out.println(result);


    }

    private static void bfs(boolean[][] isVisited, int[][] dirs, Point start, char[][] map) {
        isVisited[start.x][start.y] = true;
        Queue<Point> que= new LinkedList<>();
        que.add(start);

        while(!que.isEmpty()){
            Point current = que.poll();

            for(int[] dir: dirs){
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];
                if(isValid(nx, ny)&&!isVisited[nx][ny]&&map[nx][ny]==map[start.x][start.y]){
                    que.add(new Point(nx,ny));
                    isVisited[nx][ny] = true;
                }
            }
        }
    }

    private static boolean isValid(int nx, int ny) {
        return nx>=0&&nx<n&&ny>=0&&ny<m;
    }
}