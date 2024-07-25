import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int n;
    static int m;


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());
        n = Integer.parseInt(tokens.nextToken());
        m = Integer.parseInt(tokens.nextToken());

        int[][] map = new int[n][m];

        for(int y=0; y<n; y++){
            String line = buffer.readLine();
            for(int x=0; x<m; x++){
                map[y][x] =line.charAt(x)-'0';
            }
        }
        int maxSize = Math.min(n, m);
        int result = getMaxSize(maxSize, map);
        System.out.println(result*result);
    }

    private static int getMaxSize(int maxSize, int[][] map){
        int result = 0;
        for(int size=1; size<=maxSize; size++){
            if(isValid(size, map)){
                result = size;
            }
        }

        return result;

    }

    private static boolean isValid(int size, int[][] map){

        for(int startY=0; startY<=n-size; startY++){
            for(int startX=0; startX<=m-size; startX++){

                if(map[startY][startX]==map[startY+size-1][startX]&&
                        map[startY+size-1][startX]==map[startY+size-1][startX+size-1]&&
                        map[startY+size-1][startX+size-1]==map[startY][startX+size-1]
                ){
                    return true;
                }
            }
        }
        return false;
    }
}
