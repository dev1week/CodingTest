import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result;

    private final static char[] alphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};



    public static void main(String[] args) throws IOException {

        int t = Integer.valueOf(buffer.readLine());

        result = new StringBuilder();
        for(int test=0; test<t; test++){
            char[] query = buffer.readLine().toCharArray();
            int[] wordCount = getWordCount(query);
            bt(0,wordCount, query.length, new StringBuilder(""));

        }
        System.out.println(result);

    }


    private static int[] getWordCount(char[] data){
        int[] wordCount = new int[52];


        for(char d: data){
            int idx = d-'a';
            if(idx<0){
                //대문자인 경우
                wordCount[d-'A']++;
            }else{
                idx+=26;
                wordCount[idx]++;
            }
        }
        return wordCount;
    }

    static String arrayToString(char[] resultString){
        String result = "";

        for(char data:resultString){
            result+=(data);
        }
        return result;
    }

    static void bt(int current, int[] wordCount, int n, StringBuilder string ){
        if(current==n){

            result.append(string).append("\n");

            return;
        }


        for(int i=0; i<52; i++){
            if(wordCount[i]!=0){
                wordCount[i]--;
                string.append(alphabet[i]);
                bt(current+1, wordCount,n,string);
                string.delete(string.length()-1, string.length());
                wordCount[i]++;
            }
        }



    }






}