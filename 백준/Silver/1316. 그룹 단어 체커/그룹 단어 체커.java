

import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static Map<Character, Integer> counter;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(buffer.readLine());
        String[] words = new String[n];
        for(int i=0; i<n; i++){
            words[i] = buffer.readLine();
        }

        //모든 문자에 대해서 각 문자가 연속해서 나타나는 경우

        //문자의 길이가 최대 100, n은 최대 100
        int result = 0;
        //문자를 하나씩 잘라 map에 0의 value로 넣어놓는다.
        for(String word: words){

            if(isGroupWord(word)){
                result++;
            }

        }

        System.out.println(result);


        //문자를 하나씩 잘라 map에 마지막 인덱스를 저장한다.
            //가지고 와서 현재 인덱스와 비교해본다.
                //0일 때 상관 없음
                //현재 인덱스 -1 == 마지막 인덱스 상관없음
                //그 외의 경우 false


    }


    private static boolean isGroupWord(String word){
        char[] chars = word.toCharArray();
        counter = new HashMap<>();;
        for(char c: chars){
            counter.put(c,-1);
        }

        for(int i=0; i<word.length(); i++){


            int lastIdx = counter.get(word.charAt(i));
            //System.out.println(word.charAt(i)+" "+lastIdx);
            counter.put(word.charAt(i),i);
            //System.out.println(counter);

            if(lastIdx != -1&&lastIdx!=i-1){
                //System.out.println("실패");
                return false;
            }

        }

        return true;
    }
}





