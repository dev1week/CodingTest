import java.io.*;
import java.util.*;

class Word implements Comparable<Word>{
    String word;
    int cnt;

    public Word(String word, int cnt){
        this.word = word;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Word o) {
        if (this.cnt != o.cnt) {
            return o.cnt - this.cnt;
        }

        if (this.word.length() != o.word.length()) {
            return o.word.length() - this.word.length();
        }

        return this.word.compareTo(o.word);
    }

    @Override
    public String toString(){
        return this.word;
    }

    public void increaseCnt(TreeSet<Word> words){
        words.remove(this);
        this.cnt++;
        words.add(this);
    }


}


public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException{


        TreeSet<Word> words = new TreeSet<>();
        Map<String, Word> dict = new HashMap<>();

        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());

        for(int i=0; i<n; i++){
            String word = buffer.readLine();
            if(word.length()>=m){
                if(dict.containsKey(word)){
                    dict.get(word).increaseCnt(words);
                }else{
                    Word wordData = new Word(word, 1);
                    dict.put(word, wordData);
                    words.add(wordData);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        while(!words.isEmpty()){
            result.append(words.pollFirst()).append("\n");
        }

        System.out.println(result);
        //단어의 길이가 m이 되는지 확인한다.
            //이상일 경우에만 트리에 넣는다.

    }
}