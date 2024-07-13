import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    //상태



    public static void main(String[] args )throws IOException{
        int t= Integer.parseInt(buffer.readLine());
        StringBuilder result = new StringBuilder();

        for(int test=0; test<t; test++){
            int K = Integer.parseInt(buffer.readLine());
            int[][] d = new int[K+1][K+1];
            int[] sizes = new int[K+1];

            tokens = new StringTokenizer(buffer.readLine());

            for(int i=1; i<=K; i++){
                sizes[i] = Integer.parseInt(tokens.nextToken());
            }

            int[][] sum = new int[K+1][K+1];

            sum[0][0] = sizes[0];
            for(int i=1; i<=K; i++){
                sum[i][i] = sizes[i];
                for(int j=i; j<=K;j++){
                    if(j==1) continue;
                    sum[i][j] = sum[i][j-1]+sizes[j];
                }
            }

            for(int length =2; length<=K; length++ ){
                for(int i=1; i<=K-length+1; i++){
                    int j = i + length-1;

                    d[i][j] = Integer.MAX_VALUE;

                    for(int k=i; k<j; k++){
                        d[i][j] = Math.min(d[i][j], d[i][k]+d[k+1][j]+sum[i][j]);
                    }

                }
            }

            result.append(d[1][K]).append("\n");

        }

        System.out.println(result);



    }
}