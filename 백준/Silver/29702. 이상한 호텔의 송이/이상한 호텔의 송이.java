
import java.io.*;
import java.util.*;




public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {


        long t = Long.parseLong(buffer.readLine());
        StringBuilder result = new StringBuilder();

        int isVisited = 0;

        for(int test=0; test<t; test++){
            long room = Long.parseLong(buffer.readLine());
            while(room>0){

                long floor = 1;

                for(int testFloor=60; testFloor>=0; testFloor--){
                    if(((room>>testFloor)&1)==1){
                        floor += testFloor;
                        break;
                    }
                }

                long mask = (1L<<(floor-1))-1;

                long roomNumber = mask&room;
                roomNumber++;

//                System.out.println(floor+" "+roomNumber);
                String roomNumberString = String.valueOf(roomNumber);

                int zeroPaddingSize = 18 - roomNumberString.length();

                StringBuilder zeroPadding = new StringBuilder();

                for(int i=0; i<zeroPaddingSize; i++){
                    zeroPadding.append("0");
                }

                result.append(floor).append(zeroPadding.toString()).append(roomNumberString).append("\n");


                room /=2;

            }


        }
        System.out.println(result);

    }








}