import java.io.*;
import java.util.*; 

public class Main{
    public static void main(String[] args)throws IOException{
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokens = new StringTokenizer(buffer.readLine());
        int size = Integer.valueOf(tokens.nextToken());
        int queryNum = Integer.valueOf(tokens.nextToken());
        int[][] a = new int[size][size];
        int[][] s = new int[size+1][size+1];
        for(int y=0; y<size; y++){
            tokens = new StringTokenizer(buffer.readLine());
            for(int x=0; x<size; x++){
                a[y][x] = Integer.valueOf(tokens.nextToken());
            }
        }

        for(int x =1; x<=size; x++){
            s[1][x] = s[1][x-1] + a[0][x-1];
        }
        for(int y=1; y<=size; y++){
            s[y][1] = s[y-1][1] +a[y-1][0];
        }
        for(int y=2; y<=size; y++){
            for(int x=2; x<=size; x++){
                s[y][x] = s[y-1][x] +s[y][x-1] -s[y-1][x-1] +a[y-1][x-1];
            }
        }

        for(int q =0; q<queryNum; q++){
            tokens = new StringTokenizer(buffer.readLine());
            int x1 = Integer.valueOf(tokens.nextToken());
            int y1 = Integer.valueOf(tokens.nextToken());
            int x2 =Integer.valueOf(tokens.nextToken());
            int y2 =Integer.valueOf(tokens.nextToken());
            System.out.println(s[x2][y2]-s[x1-1][y2]-s[x2][y1-1]+s[x1-1][y1-1]);
        }


    }


}