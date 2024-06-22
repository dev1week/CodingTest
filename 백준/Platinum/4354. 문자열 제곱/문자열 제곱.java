

import java.io.*;



public class Main {

    private static int makeTable(String str){
        int len = str.length();
        int table[] = new int[str.length()];

        int j = 0;
        int cnt = 0;

        for (int i = 1; i < len; i++) {
            while(j > 0 && str.charAt(i) != str.charAt(j)){

                j = table[j -1];
            }
            if(str.charAt(i) == str.charAt(j)){
                table[i] = ++j;
            }
        }

        if(len % (len- table[len -1]) != 0){
            return  1;
        }else{
            // ababab => 6 / (6 - 4)  => 3
            // aaaa => aaa가 접미사 접두사로
            // 4 / (4-3) => 4
            return len / (len - table[len -1]);
        }
    }
    static BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        

        while(true){ 
            String temp = br.readLine();
            if(temp.equals(".")){
                break;
            }else{
                System.out.println(makeTable(temp));
            }

        }

    }
}