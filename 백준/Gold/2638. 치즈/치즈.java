import java.io.*;
import java.util.*;

class Point{
    int x, y;


    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private static final int[] dx = {-1,1,0,0};
    private static final int[] dy = {0,0,-1,1};
    static final int AIR = 0;
    static final int INNER_AIR= 2;
    static final int CHEESE = 1;

    static int[][] map;
    static int n;
    static int m;

    private static void fill(int startX, int startY, int count, boolean[][] isVisited){
        if(map[startX][startY]==CHEESE){
            isVisited[startX][startY] = true;
            map[startX][startY] = CHEESE;
            return;
        }


        Queue<Point> que = new LinkedList<>();

        que.add(new Point(startX, startY));
        isVisited[startX][startY] = true;
        map[startX][startY]= count;

        while(!que.isEmpty()){
            Point current = que.poll();


            for(int d=0; d<4; d++){
                int nX = current.x+dx[d];
                int nY = current.y+dy[d];

                if(inRange(nX, nY)&&!isVisited[nX][nY]&&map[current.x][current.y]==map[nX][nY]){
                    map[nX][nY] = count;
                    que.add(new Point(nX, nY));
                    isVisited[nX][nY] = true;
                }
            }

        }


    }

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        map = new int[n][m];


        for(int x=0; x<n; x++){
            map[x] = Arrays.stream(buffer.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }



        int totTime = 0;
        for(int time=0; time<100; time++){
            boolean[][] isVisited = new boolean[n][m];
            int[][] newMap = new int[n][m];
            int totCheese = 0;
            int count = 0;

            for(int x=0; x<n; x++){
                for(int y=0; y<m; y++){
                    if(isVisited[x][y])continue;
                    isVisited[x][y] = true;
                    fill(x,y,count,isVisited);
                    count++;
                }
            }
//            System.out.println("채우기");
//            for(int[] ma: map){
//                System.out.println(Arrays.toString(ma));
//            }
//            System.out.println();


            for(int x=0; x<n; x++){
                for(int y=0; y<m; y++){
                    if(map[x][y]==AIR||map[x][y]>=INNER_AIR){
                        continue;
                    }
                    if(getAirSideCount(x,y)<2) {
                        newMap[x][y] = CHEESE;
                        totCheese++;
                    }
                }
            }
            map = newMap;
            totTime++;
//            System.out.println("녹고 난 직후");
//            for(int[] ma: map){
//                System.out.println(Arrays.toString(ma));
//            }
//            System.out.println();


            if(totCheese==0)break;

        }



        System.out.println(totTime);
    }

    private static int getAirSideCount(int x, int y){
        int count = 0;

        for(int d=0; d<4; d++){
            int nX = x + dx[d];
            int nY = y+ dy[d];
            if(inRange(nX, nY)&&map[nX][nY]==AIR){

                count++;
            }
        }
        return count;
    }

    private static boolean inRange(int x, int y){

        return x>=0&&y>=0&&x<n&&y<m;

    }




}