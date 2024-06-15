import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    //새로운 수열 생성하기
        //들어온 연결리스트만큼 순회
        //현재 위치 + 위치+1 합하기
            //합한 값이 9가 넘어갈 경우 0으로 배열에 저장하기
    //배열 반환하기

    private static List<Integer> getNewNumbers(List<Integer> numbers){
        List<Integer> newNumbers = new ArrayList<>();

        for(int i=0; i<numbers.size()-1; i++){
            int newNumber = numbers.get(i)+numbers.get(i+1);
            if(newNumber>9){
                newNumbers.add(newNumber-10);
            }else{
                newNumbers.add(newNumber);
            }
        }

        return newNumbers;
    }





    public static void main(String[] args)throws IOException{

        String phoneNumber1 = buffer.readLine();
        String phoneNumber2 = buffer.readLine();

        List<Integer> numbers = new ArrayList<>();

        for(int i=0; i<phoneNumber2.length()+phoneNumber2.length(); i++){
            if(i%2==0){
                numbers.add(Integer.valueOf(phoneNumber1.charAt(i/2)-'0'));
            }else{
                numbers.add(Integer.valueOf(phoneNumber2.charAt(i/2)-'0'));
            }
        }




        while(numbers.size()>2){
            numbers = getNewNumbers(numbers);
        }

        System.out.println(getResult(numbers));

        //연결리스트로 만든다.

        //연결리스트 길이 만큼 순회한다.
            //새로운 합으로된 수열을 생성한다.


    }

    private static String getResult(List<Integer> numbers){
        StringBuilder result = new StringBuilder();
        if(numbers.get(0)==0){
            result.append("0");
        }else{
            result.append(numbers.get(0));
        }

        return result.append(numbers.get(1)).toString();


    }



}
