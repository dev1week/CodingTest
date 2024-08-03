
import java.io.*;
import java.util.*;




public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;


    private static final char ALIVE = '*';
    private static final char BLANK = '.';

    private static int height;
    private static int width;
    private static int k;


    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());

        height = Integer.parseInt(tokens.nextToken());
        width = Integer.parseInt(tokens.nextToken());

        int t = Integer.parseInt(tokens.nextToken());

        tokens = new StringTokenizer(buffer.readLine());
        k= Integer.parseInt(tokens.nextToken());
        int a = Integer.parseInt(tokens.nextToken());
        int b = Integer.parseInt(tokens.nextToken());


        char[][] map = new char[height][width];

        for(int x=0; x<height; x++){
            String line = buffer.readLine();
            for(int y=0; y<width; y++){
                map[x][y] = line.charAt(y);
            }
        }


        for(int time=0; time<t; time++){
            map = simulation(map, a,b);
        }
        
        System.out.println(print(map));




        //현재 칸에 생명이 있는가
            //있다
                //주위에 a개 이상 b 이하의 생명이 있는가ㅣ
                    //살아남음
                //a개 미만에 생명이 있는가
                    //죽음
                //b 초과의 생명이 있는가
                    //죽음
            //없다
                //a<x<=b -> 생명이 생긴다.

    }

    private static String print(char[][] map){
        StringBuilder result = new StringBuilder();

        for(char[] ma: map){
            for(char m : ma){
                result.append(m);
            }result.append("\n");
        }

        return result.toString();

    }

    private static int getCount(char[][] map, int startX, int startY){
        int count = 0;


        for(int x=startX-k; x<=startX+k; x++){
            for(int y=startY-k; y<=startY+k; y++){
                if(x==startX&&y==startY)continue;
                if(isValid(x,y)&&map[x][y]==ALIVE){
                    count++;
                }
            }
        }
//        System.out.println(startX+":"+startY+"칸 주변의 생명 수 : "+count);


        return count;
    }

    private static boolean isValid(int x, int y){
        return x>=0&&y>=0&&x<height&&y<width;
    }


    private static char[][] simulation(char[][] map, int a, int b){
        char[][] nextMap = new char[height][width];
        for(char[] ma: nextMap){
            Arrays.fill(ma, BLANK);
        }

        for(int x=0; x<height; x++){
            for(int y=0; y<width; y++){
                int count = getCount(map,x,y);
                if(map[x][y]==ALIVE){
                    if(count<a){
                        nextMap[x][y] = BLANK;
                    }else if(count>b){
                        nextMap[x][y]  = BLANK;
                    }else{
                        nextMap[x][y] = ALIVE;
                    }
                }else if(map[x][y]==BLANK){
                    if(count>a&&count<=b){
                        nextMap[x][y] = ALIVE;
                    }
                }

            }
        }

        return nextMap;
    }



}