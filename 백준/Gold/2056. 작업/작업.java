import org.w3c.dom.Node;

import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    private final static int ROOT = 1;
    private static int[] parents;
    private static int[] costs;

    private static List<Integer>[] getGraph(int n ){
        List<Integer>[] graph = new List[n+1];
        for(int node=0; node<=n; node++){
            graph[node] = new ArrayList<>();
        }
        return graph;
    }


    private static int[] getInputNodes(int n, List<Integer>[] graph) throws IOException{
        int[] inputNodes = new int[n+1];
        for(int node=1; node<=n; node++){
            tokens = new StringTokenizer(buffer.readLine());

            int cost = Integer.parseInt(tokens.nextToken());
            int parentNum = Integer.parseInt(tokens.nextToken());

            for(int edge=0; edge<parentNum; edge++){
                int parent = Integer.parseInt(tokens.nextToken());
                graph[parent].add(node);

                inputNodes[node]++;
            }
            costs[node] = cost;
        }

        return inputNodes;
    }

    public static void main(String[] args) throws IOException {


        int n = Integer.parseInt(buffer.readLine());


        costs = new int[n+1];
        List<Integer>[] graph = getGraph(n);
        int[] inputNodes = getInputNodes(n, graph);

        //indegree에서 0인 노드를 큐에 차례대로 집어 넣는다.
        int[] dp = getCost(graph, inputNodes, n);


        System.out.println(Arrays.stream(dp).max().getAsInt());
        
        //큐를 순회한다.
            //현재 노드의 자식을 순회한다.
                //해당 노드의 indegree 배열을 감소시킨다.
                //dp[자식] = Math.max(dp[자식], dp[현재] + 자식.소모시간);
                //해당 노드의 indegree 가 0인 경우 큐에 넣는다.

        //dp배열을 순회하여 최댓값을 찾는다.
    }


    private static int[] getCost(List<Integer>[] graph, int[] inputNodes, int n){
        Queue<Integer> que = new LinkedList<>();
        int[] dp = new int[n+1];
        for(int work=1; work<=n; work++){
            if(inputNodes[work]==0){
                que.add(work);
                dp[work] = costs[work];
            }
        }

        while(!que.isEmpty()){
            int current = que.poll();

            for(int child : graph[current]){
                dp[child] = Math.max(dp[child], dp[current]+costs[child]);
                inputNodes[child]--;
                if(inputNodes[child]==0){
                    que.add(child);
                }
            }
        }

        return dp;
    }
}