import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    //상태



    public static void main(String[] args )throws IOException{
        int n = Integer.parseInt(buffer.readLine());

        int[][] d = new int[n+1][10];

        //d[자릿수][끝나는 숫자]
        for(int i=0; i<=9; i++){
            d[1][i] = 1;
        }

        //d[i][1] = 0;
        //d[i][2] = d[i-1][1]
        //d[i][3] = d[i-1][1] + d[i-1][2];
        //d[i][j] = d[i-1][1] + ... + d[i-1][j-1];

        for(int i=2; i<=n; i++){

            for(int j=0; j<=9; j++){
                int sum = 0;
                for(int k=0; k<=j; k++){
                    sum+=d[i-1][k];
                    sum%=10007;
                }
                d[i][j] = sum;
            }

        }


        int result = 0;

        for(int i=0; i<=9; i++){
            result+=d[n][i];
        }

        System.out.println(result%10007);



    }
}