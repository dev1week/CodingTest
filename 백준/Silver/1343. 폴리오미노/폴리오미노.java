import java.io.*;
import java.util.*;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    private static final String AAAA = "AAAA";
    private static final String BB = "BB";


    public static void main(String[] args) throws IOException {

        String data = buffer.readLine();

        StringTokenizer data1 = new StringTokenizer(data, "X");
        StringTokenizer data2 = new StringTokenizer(data, ".");


        List<String> commas = new ArrayList<>();
        StringBuilder result = new StringBuilder();

        //첫글자 .으로 시작하는 경우
        if(data.charAt(0)=='.'){
            result.append(data1.nextToken());
        }


        while(data1.hasMoreTokens()){
            commas.add(data1.nextToken());
        }


        int commaIdx = 0;

        boolean avail = false;

        while(data2.hasMoreTokens()) {
            String tmp = data2.nextToken();
            avail = true;
            if (tmp.length() % 4 == 0) {
                int count = tmp.length() / 4;

                for(int i=0; i<count; i++){
                    result.append(AAAA);
                }

            } else if (tmp.length() % 2 == 0 && tmp.length()>4) {
                // "XXXXXX"
                int currentLength = tmp.length();

                while(currentLength>=4){
                    result.append(AAAA);
                    currentLength-=4;
                }
                result.append(BB);

            }else if(tmp.length() % 2 ==0){
                result.append(BB);
            }else  {
                result = new StringBuilder("-1");
                break;
            }

            if(data2.hasMoreTokens()){
                result.append(commas.get(commaIdx++));
            }
        }

        //마지막글자 .으로 시작하는 경우
        if(data.charAt(data.length()-1)=='.'&&commas.size()!=0 && !result.toString().equals("-1")){
            result.append(commas.get(commas.size()-1));
        }


        if(!avail){
            result = new StringBuilder(data);
        }

        System.out.println(result);
    }


}
