import java.io.*;
import java.util.*;
public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());
        int n = Integer.parseInt(tokens.nextToken()), m = Integer.parseInt(tokens.nextToken());
        long[][] numbers = new long[n][n];

        long[][] diff = new long[n+1][n+1];

        for(int x=0; x<n; x++){
            tokens = new StringTokenizer(buffer.readLine());
            for(int y=0; y<n; y++){
                numbers[x][y] = Integer.parseInt(tokens.nextToken());
            }
        }



        for(int query=0; query<m-1; query++){
            tokens = new StringTokenizer(buffer.readLine());
            int type = Integer.parseInt(tokens.nextToken());
            int x1 = Integer.parseInt(tokens.nextToken());
            int y1 = Integer.parseInt(tokens.nextToken());

            int x2 = Integer.parseInt(tokens.nextToken());
            int y2 = Integer.parseInt(tokens.nextToken());

            int data = Integer.parseInt(tokens.nextToken());
            diff[x1][y1] += data;
            diff[x2+1][y2+1] += data;

            diff[x2+1][y1] -=data;
            diff[x1][y2+1] -=data;
        }




        //가로로 누적합
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                diff[x][y+1] += diff[x][y];
            }
        }


        //세로로 누적합
        for(int y=0; y<n; y++){
            for(int x=0; x<n; x++){
                diff[x+1][y] += diff[x][y];
            }
        }


        tokens = new StringTokenizer(buffer.readLine());
        long sum = 0;
        int type = Integer.parseInt(tokens.nextToken());
        int x1 = Integer.parseInt(tokens.nextToken());
        int y1 = Integer.parseInt(tokens.nextToken());

        int x2 = Integer.parseInt(tokens.nextToken());
        int y2 = Integer.parseInt(tokens.nextToken());


        for(int x=x1; x<=x2; x++){
            for(int y=y1; y<=y2; y++){
                long last = (numbers[x][y]+diff[x][y]);
                sum += last;
            }
        }
        System.out.println(sum);


    }

    private static void print(long[][] arr){
        for(long[] ar:arr){
            System.out.println(Arrays.toString(ar));
        }
    }

}