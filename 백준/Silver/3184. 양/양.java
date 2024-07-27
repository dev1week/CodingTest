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

    static int r;
    static int c;
    static char[][] map;
    static boolean[][] isVisited;

    static final char BLOCK = '#';
    static final char SHEEP = 'o';
    static final char WOLF = 'v';

    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};


    static int totSheepCount;
    static int totWolfCount;

    public static void main(String[] args) throws IOException {

        tokens = new StringTokenizer(buffer.readLine());

        r = Integer.parseInt(tokens.nextToken());
        c = Integer.parseInt(tokens.nextToken());


        map = new char[r][c];
        isVisited = new boolean[r][c];

        for(int x=0; x<r; x++){
            String line = buffer.readLine();
            for(int y=0; y<c; y++){
                map[x][y] = line.charAt(y);
            }
        }


        for(int x=0; x<r; x++){

            for(int y=0; y<c; y++){
                if(isVisited[x][y]||map[x][y]==BLOCK)continue;

                count(new Point(x, y));
            }
        }
        System.out.println(totSheepCount+" "+totWolfCount);

        //위치를 순회한다.
            //방문 안했으면 해당 영역에서의 늑대와 양의 수를 센다.
            //이긴 쪽에 tot을 넣어준다.

    }

    static void count(Point start){

        Queue<Point> que = new LinkedList<>();
        isVisited[start.x][start.y] = true;
        que.add(start);

        int sheepCount = 0;
        int wolfCount = 0;

        while(!que.isEmpty()){
            Point current = que.poll();

            if(map[current.x][current.y]==SHEEP){
                sheepCount++;
            }else if(map[current.x][current.y]==WOLF){
                wolfCount++;
            }

            for(int d=0; d<4; d++){
                int nX = current.x+ dx[d];
                int nY = current.y+ dy[d];

                if(isValid(nX, nY)){
                    isVisited[nX][nY] = true;
                    que.add(new Point(nX, nY));
                }
            }

        }

        if(sheepCount>wolfCount){
            totSheepCount += sheepCount;
        }else{
            totWolfCount += wolfCount;
        }

    }

    static boolean isValid(int x, int y){
        return x>=0&&y>=0&&x<r&&y<c&&map[x][y]!=BLOCK&&!isVisited[x][y];
    }



}