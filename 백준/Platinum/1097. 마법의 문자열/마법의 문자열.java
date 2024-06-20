import java.util.*;
import java.io.*;



class Main{
    
    private static Scanner sc = new Scanner(System.in);
    private static int N, K, size;
    private static String[] words;
    private static boolean[] check;
    
    public static void main(String[] args){
        input();
        System.out.println(getComb(""));
    }
    
    private static void input(){
        N = sc.nextInt();
        words = new String[N+5];
        check = new boolean[N+5];
        for(int i = 0; i < N; i++){
            words[i] = sc.next();
            size += words[i].length();
        }
        K = sc.nextInt();
    }
    
    private static int getComb(String word){
        int ret = 0;
        if(word.length() == size) return kmp(word);
        for(int i = 0; i < N; i++){
            if(check[i]) continue;
            check[i] = true;
            ret += getComb(word+words[i]);
            check[i] = false;
        }
        return ret;
    }
    
    private static int kmp(String word){
        int[] pi = new int[word.length()+5];
        int pIdx = 0;
        for(int idx = 1; idx < word.length(); idx++){
            while(word.charAt(idx) != word.charAt(pIdx) && pIdx > 0) pIdx = pi[pIdx-1];
            if(word.charAt(idx) == word.charAt(pIdx)) pi[idx] = ++pIdx;
        }
        if(pi[word.length()-1] % (word.length()-pi[word.length()-1]) != 0){
            if(K == 1) return 1;
            else return 0;
        }
        return K-1 == pi[word.length()-1] / (word.length()-pi[word.length()-1]) ? 1 : 0; 
    }
}