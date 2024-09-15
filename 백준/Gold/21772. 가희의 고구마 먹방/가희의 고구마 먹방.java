import java.io.*;
import java.util.*;

class Point{
    int x, y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return this.x+":"+this.y;
    }
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static char[][] map;

    private final static char SWEET = 'S';
    private final static char START = 'G';
    private final static char BLANK = '.';
    private final static char BLOCK = '#';

    private static int limitTime;
    private static int n;
    private static int m;

    private static Point start;

    private static int result = 0;

    private static int[] dx = {-1,1,0,0,0};
    private static int[] dy = {0,0,-1,1,0};

    private static void dfs(int currentTime, Point currentPoint, int sweetCount){
        if(currentTime>limitTime){

            return;
        }
        result = Math.max(result, sweetCount);

        for(int dir=0; dir<5; dir++){
            int nx =currentPoint.x + dx[dir];
            int ny= currentPoint.y + dy[dir];
            Point nextPoint = new Point(nx, ny);

            if(inRange(nx, ny)&&isValid(nx, ny)){
                if(map[nx][ny]==SWEET){
                    map[nx][ny] = BLANK;
                    dfs(currentTime+1, nextPoint, sweetCount+1);
                    map[nx][ny] = SWEET;
                }else{
                    dfs(currentTime+1, nextPoint, sweetCount);
                }
            }
        }
    }

    private static boolean inRange(int x, int y){
        return x>=0&&y>=0&&x<n&&y<m;
    }

    //장애물이 아님
    private static boolean isValid(int x, int y){
        return map[x][y]!=BLOCK;
    }


    public static void main(String[] args)throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        n= Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());
        limitTime = Integer.parseInt(tokens.nextToken());

        map = new char[n][m];



        for(int x=0; x<n; x++){
            String line = buffer.readLine();
            for(int y=0; y<m; y++){
                map[x][y] = line.charAt(y);
                if(map[x][y]==START){
                    start = new Point(x, y);
                }
            }
        }


        dfs(0, start, 0);


        System.out.println(result);



    }

    private static void print(char[][] map){
        for(char[] ma:map){
            for(char m : ma){
                System.out.print(m);
            }System.out.println();
        }
    }
}
