
import java.io.*;
import java.util.*;

class Main{


    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;



    //n개의 점

    //두 점을 선택해서 연결하는 선분을 긋는다.

    //사이클을 완성하는 순간 게임을 종료한다.

    //몇번째에서 사이클이 완성되었는지, 끝나지 않았는지 판단하기

    static int n; //점의 개수 5*10^5
    static int m; //진행된 횟수 10^6
    static int[] parents;


    static boolean isConnected(int x, int y){
        if(find(x)==find(y)) return false;
        parents[find(y)] = find(x);
        return true;
    }

    static int find(int x){
        if(parents[x]==x)return x;


        return parents[x] =find(parents[x]);
    }



    public static void main(String[] args) throws IOException{


        tokens = new StringTokenizer(buffer.readLine());

        n = Integer.valueOf(tokens.nextToken());
        m = Integer.valueOf(tokens.nextToken());

        parents = new int[n];
        
        for(int i=0; i<n; i++) {
        	parents[i] = i; 
        }


        int result = 0;
        for(int game=1; game<=m; game++){
            tokens = new StringTokenizer(buffer.readLine());
            int x= Integer.valueOf(tokens.nextToken());
            int y= Integer.valueOf(tokens.nextToken());
            if(!isConnected(x,y)){
                result = game;
                break;
            }
        }

        System.out.println(result);

    }
}