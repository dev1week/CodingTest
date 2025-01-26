import org.w3c.dom.Node;

import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    static final int ROOT_LEVEL = 1;

    static final int MAX_NUM = 100;

    private static boolean isRoot(int idx, int[] indegree){
        return indegree[idx] ==0;
    }


    public static void main(String[] args)throws IOException{

        tokens = new StringTokenizer(buffer.readLine());

        int n = Integer.parseInt(tokens.nextToken());
        int m = Integer.parseInt(tokens.nextToken());


        int idx =0;
        Map<String, Integer> nameToIdx = new HashMap<>();


        double[] level = new double[MAX_NUM];
        int[] indegree = new int[MAX_NUM];

        List<String>[] graph = initGraph();


        idx = addRoot(idx, level, nameToIdx);
        makeGraph(idx, indegree, graph, n , nameToIdx);


        Queue<String> que = initQueue(nameToIdx, indegree);


        getLevel(que, indegree, level, nameToIdx, graph);


        System.out.println(getResult(level, nameToIdx, m));


        //위상정렬을 수행한다.
            //indegree가 0인 노드를 큐에 넣는다.

            //큐를 계속순회한다.
                //현재 노드의 자식을 순회한다.
                    //자식 노드의 indegree를 줄인다.
                    //자식 노드의 Indegree가 0인 경우 큐에 넣는다.
                    //항렬을 계산한다.
                        //현재 노드의 항렬이 0인 경우 스킵한다.
                        //부모 노드의 항렬 +1을 자식 노드에 더한다.


        //주장하는 사람들의 이름 중 가장 작은 값의 이름을 구한다.






    }

    private static String getResult(double[] level, Map<String, Integer> nameToIdx, int m)throws IOException {
        double maxLevel = 0;
        String result = "";
        for(int query=0; query<m; query++){
            String queryName = buffer.readLine();
            if(maxLevel<level[nameToIdx.getOrDefault(queryName, MAX_NUM-1)]){
                maxLevel = level[nameToIdx.get(queryName)];
                result = queryName;
            }
        }
        return result;
    }

    private static void getLevel(Queue<String> que, int[] indegree, double[] level, Map<String, Integer> nameToIdx, List<String>[] graph) {
        while(!que.isEmpty()){
            String currentName = que.poll();
            for(String child: graph[nameToIdx.get(currentName)]){
                indegree[nameToIdx.get(child)]--;
                if(indegree[nameToIdx.get(child)]==0){
                    que.add(child);
                }
                if(level[nameToIdx.get(currentName)]!=0){
                    level[nameToIdx.get(child)]+=(level[nameToIdx.get(currentName)]*0.5);
                }
            }
        }
    }

    private static Queue<String> initQueue(Map<String, Integer> nameToIdx, int[] indegree) {
        Queue<String> que = new LinkedList<>();
        for(String person : nameToIdx.keySet()){
            if(isRoot(nameToIdx.get(person), indegree)){
                que.add(person);
            }
        }
        return que;
    }

    private static int addRoot( int idx, double[] level,Map<String, Integer> nameToIdx) throws IOException{
        String rootName = buffer.readLine();
        nameToIdx.put(rootName, idx);

        level[idx] = ROOT_LEVEL;
        idx++;

        return idx;
    }

    private static int checkDuplicatedAndAddName(String name, Map<String, Integer> nameToIdx, int idx){
        if(nameToIdx.containsKey(name)) return idx;


       nameToIdx.put(name, idx);
        idx++;
        return idx;
    }

    private static void makeGraph(int idx, int[] indegree, List<String>[] graph, int n, Map<String, Integer> nameToIdx )throws IOException {

        for(int i=0; i<n; i++){
            tokens = new StringTokenizer(buffer.readLine());

            String currentName = tokens.nextToken();
            String parent1 = tokens.nextToken();
            idx = checkDuplicatedAndAddName(parent1, nameToIdx, idx);
            String parent2 = tokens.nextToken();
            idx = checkDuplicatedAndAddName(parent2, nameToIdx, idx);
            idx = checkDuplicatedAndAddName(currentName, nameToIdx, idx);

            indegree[nameToIdx.get(currentName)]+=2;
            graph[nameToIdx.get(parent1)].add(currentName);
            graph[nameToIdx.get(parent2)].add(currentName);
        }
    }

    private static List<String>[] initGraph() {
        List<String>[] graph = new List[MAX_NUM];


        for(int i=0; i<MAX_NUM; i++){
            graph[i] = new ArrayList<>();
        }

        return graph;
    }
}