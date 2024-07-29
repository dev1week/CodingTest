import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    static int unUsed = 1;
    static final int MAX = 1000005;
    static int[] prev = new int[MAX];
    static int[] next= new int[MAX];
    static char[] data = new char[MAX];

    //addr 뒤에 노드를 추가합니다.
    static void insert(int addr, char character){

        data[unUsed] = character;
        prev[unUsed] = addr;
        next[unUsed] = next[addr];

        //순서가 중요하다.
        if(next[addr]!=-1) prev[next[addr]] = unUsed;
        next[addr] = unUsed;
        unUsed++;
    }

    //addr 위치를 삭제한다.
    static void remove(int addr){
        if(prev[addr]!=-1)next[prev[addr]] = next[addr];
        if(next[addr]!=-1)prev[next[addr]] = prev[addr];
    }

    static String traverse(){
        int current = next[0];
        StringBuilder result = new StringBuilder();
        while(current!=-1){
            result.append(data[current]);
            current = next[current];
        }
        result.append("\n");

        return result.toString();
    }


    public static void main(String[] args) throws IOException {
        String line = buffer.readLine();

        Arrays.fill(prev,-1);
        Arrays.fill(next,-1);

        int cursor = 0;

        for(int i=0; i<line.length(); i++){
            insert(cursor, line.charAt(i));
            cursor = next[cursor];
        }

        int commandNum = Integer.parseInt(buffer.readLine());

        for(int commandIdx=0; commandIdx<commandNum; commandIdx++){
            tokens = new StringTokenizer(buffer.readLine());

            char command = tokens.nextToken().charAt(0);

            if(command=='P'){
                char character = tokens.nextToken().charAt(0);
                insert(cursor, character);
                if(next[cursor]!=-1)cursor = next[cursor];
            }else if(command=='B'){
                if(prev[cursor]!=-1){
                    remove(cursor);
                    cursor = prev[cursor];
                }
            }else if(command=='L'){
                //커서를 왼쪽으로 한칸 옮긴다.
                if(prev[cursor]!=-1) cursor = prev[cursor];
            }else if(command=='D'){
                //커서를 오른쪽으로 한칸 옮긴다.
                if(next[cursor]!=-1) cursor = next[cursor];
            }

        }


        System.out.println(traverse());
    }


}




