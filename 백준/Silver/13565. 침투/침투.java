import java.io.*;
import java.util.*;


class Point{
    int x;
    int y;


    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }



    @Override
    public String toString(){
        return x+":"+y;
    }
}

public class Main {

    final static int BLACK = 1;
    final static int WHITE = 0;

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int[][] map;
    static boolean[][] isVisited;

    static int n;
    static int m;

    static void init() throws IOException{

        tokens = new StringTokenizer(buffer.readLine());

        m = Integer.valueOf(tokens.nextToken());
        n = Integer.valueOf(tokens.nextToken());

        map = new int[m][n];
        isVisited = new boolean[m][n];


        for(int x=0; x<m; x++){
            String line = buffer.readLine();
            for(int y=0; y<n; y++){
                map[x][y] = Integer.valueOf(line.charAt(y)-'0');
            }
        }


    }


    static final int[] DX = {-1,1,0,0};
    static final int[] DY = {0,0,-1,1};

    static boolean isPossible(int nx, int ny){
        return inRange(nx, ny)&&map[nx][ny]==WHITE&&!isVisited[nx][ny];
    }

    static boolean inRange(int x, int y){
        return x>=0&&y>=0&&x<m&&y<n;
    }


    static void bfs(Point start){

        Queue<Point> que = new LinkedList<>();

        que.add(start);
        isVisited[start.x][start.y] = true;
        while(!que.isEmpty())
        {
            Point current = que.poll();

            for(int d=0; d<4; d++){
                int nx = current.x + DX[d];
                int ny = current.y + DY[d];

                if(isPossible(nx, ny)){
                    que.add(new Point(nx, ny));
                    isVisited[nx][ny] = true;
                }
            }


        }


    }

    public static void main(String[] args)throws IOException{

        init();

        //바깥쪽에서 전류 흘려주기
        for(int y=0; y<n; y++){
            if(!isVisited[0][y]&&map[0][y]==WHITE){
                bfs(new Point(0, y));
            }
        }


        boolean isConnected = false;
        //안쪽에 전류가 있는지 검사하기
        for(int y=0; y<n; y++){
            if(isVisited[m-1][y]){
                isConnected = true;
                break;
            }
        }

        if(isConnected){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }


    }

    static void print(int[][] map){
        for(int[] ma : map){
            for(int m : ma){
                System.out.print(m+" ");
            }System.out.println();
        }
    }

    static void print(boolean[][] map){
        for(boolean[]ma: map){
            for(boolean m : ma){
                if(m){
                    System.out.print(1);
                }else{
                    System.out.print(0);
                }
            }System.out.println();
        }
    }
}
