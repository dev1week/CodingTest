import java.io.*;
import java.util.*;

public class Main {
        static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer tokens;



    //곰곰티콘 인사
        //입장할 때마다 진행


    //ENTER
        //새로운 사람이 입장했음

    //나머지는 유저의 닉네임



    //곰곰티콘이 사용된 횟수 구하기


        public static void main(String[] args) throws IOException{

            int n = Integer.valueOf(buffer.readLine());  //채팅방의 기록수
            int result = 0;
            Set<String> count = new HashSet<>();
            for(int log=1; log<=n; log++){
                String data = buffer.readLine();
                //ENTER가 들어온 경우
                if("ENTER".equals(data)){
                    result += count.size();
                    count.clear();
                }else{
                    count.add(data);
                }
            }
            result+=count.size();
            System.out.println(result);



        }


}