import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result= new StringBuilder();;
    static int N;




    //백트래킹으로 전체 조합을 찾는다.(전체 숫자, 현재 갑 계산, 현재 표현식, 현재 뽑아야하는 숫자, 현재 부호 )
        //(전체숫자, 현재값+현재뽑아야하는 숫자, 현재표현식+현재뽑아야하는숫자, 현재 뽑아야하는 숫자+1)
        //(전체 숫자, 현재값-현재뽑아야하는 숫자, 현재표현식-현재뽑아야하는 숫자, 현재 뽑아야하는 숫자+1)
        //(전체숫자, 현재값*10+현재뽑아야하는 숫자, 현재 표현식 현재뽑아야하는 숫자, 현재 뽑아야하는 숫자+1)

    private static void search(int current, int num, int sum, int operation, StringBuilder expression){
        if(current==N){

            sum +=(num*operation);

            if(sum==0){
                result.append(expression.toString()).append("\n");
            }
            return;
        }

        //사이에 빈칸
        search(current+1, num*10+(current+1), sum, operation, expression.append(" ").append(current+1));
        expression.delete(expression.length()-2, expression.length());
        //사이에 +
        search(current+1, current+1, sum+(num*operation), 1,  expression.append("+").append(current+1));
        expression.delete(expression.length()-2, expression.length());
        //사이에 -
        search(current+1, current+1, sum+(num*operation), -1,  expression.append("-").append(current+1));
        expression.delete(expression.length()-2, expression.length());


    }


    public static void main(String[] args) throws IOException {
        int t = Integer.valueOf(buffer.readLine());

        for(int test=1; test<=t; test++){
            N = Integer.valueOf(buffer.readLine());
            search(1,1,0,1, new StringBuilder("1"));
            result.append("\n");

        }
        System.out.println(result); 

    }

}