import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));



    static int getTripplet(int num, int mod){
        int tmp = num;
        tmp*=num;

        tmp%=mod;

        tmp*=num;
        tmp%=mod;

        return tmp%=mod;

    }


    public static void main(String[] args)throws IOException{


        int n = Integer.valueOf(buffer.readLine()); // 참가자 수 ~5000

        //연결리스트에 넣어놓는다.

        LinkedList<Integer> list = new LinkedList<>();

        for(int person = 1; person<=n; person++){
            list.add(person);
        }

        int stage = 1;

        int currentIndex = 0;

        while(list.size()!=1){
            int offset =getTripplet(stage, list.size());


            currentIndex+=offset-1;

            if(currentIndex>=list.size()){
                currentIndex-=list.size();
            }else if(currentIndex<0){
                currentIndex+= list.size();
            }

            
            list.remove(currentIndex);
            

            stage++;
        }


        System.out.println(list.getFirst());
        //단계를 진행한다.
            //

        //1 2 3 4 5
            //2 3 4 5
                // 2 3 4
                    // 2 3
                        //

    }

}
