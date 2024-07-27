import java.io.*;
import java.util.*;


class Point {
    int x, y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Player extends Point{
    int dps, distance;

    public Player(int x, int y) {
        super(x, y);
    }


    @Override
    public String toString(){
        return "dps 총합 : "+this.dps+", 최단 경로: "+this.distance+" 최초 시작위치는 "+x+":"+y;
    }
}

class Boss extends Point{
    int hp;


    public Boss(int x, int y){
        super(x,y);
    }
}


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static char[][] map;

    static int m;
    static int n;

    static Map<Character, Player> idToPlayer = new HashMap<>();
    static TreeMap<Integer, List<Player>> distanceToPlayer = new TreeMap<>();
    static final char BLOCK = 'X';

    static Boss boss;


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        m = Integer.parseInt(tokens.nextToken());
        n = Integer.parseInt(tokens.nextToken());
        int p = Integer.parseInt(tokens.nextToken());

        map = new char[m][n];

        for(int x=0; x<m; x++){
            String line = buffer.readLine();
            for(int y=0; y<n; y++){
                map[x][y] = line.charAt(y);
                if(map[x][y]=='B'){
                    boss = new Boss(x,y);
                }
                else if(map[x][y]!='X'&&map[x][y]!='.'){
                    idToPlayer.put(map[x][y], new Player(x, y));
                }
            }
        }


        //id - 플레이어 정보 Map<id, player>를 만든다.
        for(int player=0; player<p; player++){
            tokens = new StringTokenizer(buffer.readLine());

            char id = tokens.nextToken().charAt(0);
            int dps = Integer.parseInt(tokens.nextToken());

            idToPlayer.get(id).dps = dps;
        }
        boss.hp = Integer.parseInt(buffer.readLine());

        //각 플레이어별 최단 도달 시간을 구한다.
        for(char id : idToPlayer.keySet()){
            getMinimumDistance(id);
        }

//        System.out.println(distanceToPlayer);
        //도달시간 - id가 만들어져있는 Map<도달 시간, List<id>를 만든다.

        int totDps = 0;
        int count = 0;

        for(int time= distanceToPlayer.firstKey(); time<=distanceToPlayer.lastKey(); time++){
            List<Player> currentPlayers = distanceToPlayer.getOrDefault(time, new ArrayList<Player>());

            for(Player currentPlayer : currentPlayers){
                totDps += currentPlayer.dps;
                count++;
            }

            boss.hp -= totDps;
            if(boss.hp<=0){
                break;
            }
        }

        System.out.println(count);



        //보스 체력이 0이 될 때까ㅣㅈ
            //현재시간 +
            // 도착한 사람 리스트 -> dpstotal 계산
            //체력 - dpsTotal
            //체력<=0 도착한 사람의 리스트 크기를 반환한다.

    }

    private static final int[] dx = {-1,1,0,0};
    private static final int[] dy = {0,0,-1,1};
    private static void getMinimumDistance(char id){
        Player start = idToPlayer.get(id);

        int[][] distances = new int[m][n];
        boolean[][] isVisited = new boolean[m][n];

        Queue<Point> que = new LinkedList<>();
        isVisited[start.x][start.y] = true;
        que.add(start);

        while(!que.isEmpty()){
            Point current = que.poll();

            for(int d=0; d<4; d++){
                int nX = current.x + dx[d];
                int nY = current.y+dy[d];

                if(isMovable(nX, nY, isVisited)){
                    distances[nX][nY] += (distances[current.x][current.y]+1);
                    que.add(new Point(nX, nY));
                    isVisited[nX][nY] = true;
                }
            }
        }
        idToPlayer.get(id).distance = distances[boss.x][boss.y];

        List list = distanceToPlayer.getOrDefault(distances[boss.x][boss.y], new ArrayList<>());
        list.add(idToPlayer.get(id));

        distanceToPlayer.put(distances[boss.x][boss.y], list);
    }

    private static boolean isMovable(int x, int y, boolean[][] isVisited){
        return isValid(x,y)&&map[x][y]!=BLOCK&&!isVisited[x][y];
    }

    private static boolean isValid(int x, int y){
        return x>=0&&y>=0&&x<m&&y<n;
    }




}