import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));


    static LinkedList<Integer> moveLeft(LinkedList<Integer> deque){

        int data = deque.getFirst();

        deque.removeFirst();

        deque.addLast(data);

        return deque;
    }


    static LinkedList<Integer> moveRight(LinkedList<Integer> deque){
        int data = deque.getLast();

        deque.removeLast();

        deque.addFirst(data);

        return deque;

    }

    static boolean needToMoveRight(int target, LinkedList<Integer> deque){
        return deque.size()/2<deque.indexOf(target);
    }

    static boolean needToPoll(int target, LinkedList<Integer>deque){
        return target == deque.getFirst();
    }


    static LinkedList<Integer> initDeque(int queueSize){
        LinkedList<Integer> deque= new LinkedList<>();
        for(int number=1; number<=queueSize; number++){
            deque.add(number);
        }

        return deque;
    }

    static int[] initTarget(int targetSize)throws IOException{
        StringTokenizer tokens = new StringTokenizer(buffer.readLine());


        int[] targets = new int[targetSize];

        for(int i=0; i<targetSize; i++){
            targets[i] = Integer.valueOf(tokens.nextToken());

        }

        return targets;
    }


    public static void main(String[] args)throws IOException{
        StringTokenizer tokens = new StringTokenizer(buffer.readLine());


        int n = Integer.valueOf(tokens.nextToken()); // 큐의 크기 ~50
        int m = Integer.valueOf(tokens.nextToken()); // 뽑아내는 갯수 ~50


        int[] targets = initTarget(m);

        LinkedList<Integer> deque = initDeque(n);

        // 1 2 3 4 5 6 7 8 9 10
        // 2의 인덱스가 size/2보다 작으므로 2번 연산을 진행한다.
        // 2 3 4 5 6 7 8 9 10 1
        //poll한다.
        // 3 4 5 6 7 8 9 10 1
        // 9의 인덱스가 size/2보다 크므로 3번 연산을 수행한다.


        //1 2 3 4   2  4/2 = 2 => 2번 연산
        //1 2 3 4 5 2 5/2 = 2  => 2번 연산


        //target을 순회한다.
            //가장 첫번째 요소와 비교한다.
                //같다
                    // 없앤다.
                //다르다
                    // 인덱스를 전체 사이즈와 비교한다.
                        //크다
                            //3번 연산 수행
                        //작거나 같다
                            //2번 연산 수행
        int result = 0;


        for(int target : targets){
            while(!needToPoll(target, deque)){
                result++;
                if(needToMoveRight(target, deque)){
                    deque = moveRight(deque);
                }else{
                    deque = moveLeft(deque);
                }
            }
            deque.removeFirst();

        }

        System.out.println(result);

    }

}
