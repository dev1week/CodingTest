import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static boolean[][] map;

    static final boolean BLACK = true;
    static final boolean WHITE = false;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        map = new boolean[n][m];

        for(int y=0; y<n; y++){
            String line = buffer.readLine();
            for(int x=0; x<m; x++){
                if(line.charAt(x)=='B'){
                    map[y][x] = BLACK;
                }else{
                    map[y][x] = WHITE;
                }
            }
        }
        int result = Integer.MAX_VALUE;
        //시작점을 순회한다
        for(int startY=0; startY<=n-8; startY++){
            for(int startX=0; startX<=m-8; startX++){

                result = Math.min(getChangeCount(startX, startY, map[startY][startX]), result);
                result = Math.min(getChangeCount(startX, startY, !map[startY][startX]), result);
            }


        }

        System.out.println(result);


    }

    private static int getChangeCount(int startX, int startY, boolean startColor){
        int result = 0;
        boolean currentLineColor = startColor;
        boolean currentColor = startColor;

        for(int y=startY; y<startY+8; y++){
            for(int x=startX; x<startX+8; x++){
                if(currentColor!=map[y][x]) result++;
                currentColor = !currentColor;
            }

            currentLineColor = !currentLineColor;
            currentColor = currentLineColor;
        }

        return result;

    }





}
