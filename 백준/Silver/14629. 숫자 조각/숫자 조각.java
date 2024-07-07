import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static long result;
    static long diff = Long.MAX_VALUE;
    static long n;
    static boolean[] isVisited = new boolean[10];
    static int length;



    //상태 -> 현재 선택중인 자릿수, 현재 값

    //종료조건
        //햔제 선택중인 자릿수가 주어진 자릿수보다 커졌을 경우


    //초기값
        //처음에 주어진 수의 가장 첫자리 글자로 시작하기

    //재귀식 현재 값 * 10 + 다음 선택값
    

    private static void bt(int current, long currentValue){



        long tmpDiff = Math.abs(currentValue-n);
        if(tmpDiff==diff&&currentValue<result){
            diff = tmpDiff;
            result = currentValue;
        }

        if(tmpDiff<diff){
            diff = tmpDiff;
            result = currentValue;
        }

        if(current==10){
            return;
        }



        for(int next=0; next<=9; next++){
            if(!isVisited[next]){
                isVisited[next] = true;
                bt(current+1, currentValue*10+next);
                isVisited[next] = false;
            }
        }




    }


    public static void main(String[] args) throws IOException {
        n = Long.parseLong(buffer.readLine());


        bt(0, 0);

        System.out.println(result);


    }




}