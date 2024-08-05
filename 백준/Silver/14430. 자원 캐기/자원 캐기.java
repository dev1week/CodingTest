

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        int height = Integer.parseInt(tokens.nextToken());
        int width = Integer.parseInt(tokens.nextToken());

        int[][] map = new int[height][width];
        int[][] d = new int[height][width];
        for(int x=0; x<height; x++){
            tokens = new StringTokenizer(buffer.readLine());
            for(int y=0; y<width; y++){
                map[x][y] = Integer.parseInt(tokens.nextToken());
            }
        }


        d[0][0] = map[0][0];

        for(int x=1; x<height; x++){
            d[x][0] += (map[x][0]+d[x-1][0]);
        }

        for(int y=1; y<width; y++){
            d[0][y] += (map[0][y]+d[0][y-1]);
        }

        for(int x=1; x<height; x++){
            for(int y=1; y<width; y++){
                d[x][y] =map[x][y]; 
                d[x][y] += Math.max(d[x-1][y], d[x][y-1]);
            }
        }

        System.out.println(d[height-1][width-1]);

    }
}
