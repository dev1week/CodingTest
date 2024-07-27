import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static int n;

    static final int[] dx = {-1,1,0,0,-1,1,-1,1};
    static final int[] dy = {0,0,-1,1,-1,1,1,-1};

    static PriorityQueue<Integer>[][] treeMap;

    static int[][] nutrientMap;
    static int[][] addNutrient;

    static int numOfTree;





    public static void main(String[] args) throws IOException{
        tokens = new StringTokenizer(buffer.readLine());

        n= Integer.parseInt(tokens.nextToken());

        numOfTree = Integer.parseInt(tokens.nextToken());

        int lastYear = Integer.parseInt(tokens.nextToken());


        nutrientMap = new int[n][n];
        treeMap = new PriorityQueue[n][n];
        addNutrient = new int[n][n];

        for(int x=0; x<n; x++){
            tokens = new StringTokenizer(buffer.readLine());
            for(int y=0; y<n; y++){
                addNutrient[x][y] = Integer.parseInt(tokens.nextToken());
            }
        }

        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                treeMap[x][y] = new PriorityQueue<>();
                nutrientMap[x][y] = 5;
            }
        }


        for(int tree=0; tree<numOfTree; tree++){
            tokens = new StringTokenizer(buffer.readLine());
            int x = Integer.parseInt(tokens.nextToken());
            int y = Integer.parseInt(tokens.nextToken());
            int age = Integer.parseInt(tokens.nextToken());
            treeMap[x-1][y-1].add(age);
        }



        for(int year=0; year<lastYear; year++){
            //봄 자신의 나이만큼 양분을 섭취, 나이 1 증가, 나이가 어린 나무부터 양분 섭취, 못먹을 경우 죽음
            //여름 죽은 나무가 양분을 뿌림 총합(나이/2)
            //가을 나이가 5의 배수인 나무가 번식한다. 인접한 8칸에 나이가 1인 나무가 생긴다.
            //겨울 -> 땅에 양분을 추가한다.
            springAndSummer();

            autumnAndWinter();

            
        }

        int result = 0;

        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                result += treeMap[x][y].size();
            }
        }
        System.out.println(result);
    }

    static void print(){
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                if(treeMap[x][y].size()!=0){
                    System.out.println(x+":"+y+treeMap[x][y]);
                }
            }
        }
        for(int x=0; x<n; x++){
            System.out.println(Arrays.toString(nutrientMap[x]));
        }
    }

    static void autumnAndWinter(){
        for(int x=0; x<n; x++){
            for(int y=0; y<n;y++){
                List<Integer> trees = new ArrayList<>(treeMap[x][y]);

                for(int tree: trees){
                    if(tree%5==0){
                        for(int d=0; d<8; d++){
                            int nextX = x+dx[d];
                            int nextY = y+dy[d];
                            if(isValid(nextX, nextY)){
                                treeMap[nextX][nextY].add(1);
                            }
                        }
                    }
                }

                nutrientMap[x][y] += addNutrient[x][y];
            }
        }
    }

    private static boolean isValid(int x, int y){
        return x>=0&&y>=0&&x<n&&y<n;
    }

    static void springAndSummer(){
        for(int x=0; x<n; x++){
            for(int y=0; y<n; y++){
                PriorityQueue<Integer> afterTrees = new PriorityQueue<>();
                int nutrientFromDead = 0;
                while(!treeMap[x][y].isEmpty()){
                    int currentTree = treeMap[x][y].poll();

                    if(currentTree>nutrientMap[x][y]){
                        nutrientFromDead += (currentTree/2);
                    }else{
                        nutrientMap[x][y] -= currentTree;
                        afterTrees.add(currentTree+1);
                    }

                }
                treeMap[x][y] = afterTrees;
                nutrientMap[x][y] += nutrientFromDead;
            }
        }
    }






}