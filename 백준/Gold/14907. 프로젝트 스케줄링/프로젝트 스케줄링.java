import java.io.*;
import java.util.*;



public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args)throws IOException{
        List<Character>[] graph =  initGraph();
        int[] costs = new int[27];
        int[] indegree = new int[27];
        addEdges(graph, costs, indegree);

        Queue<Integer> que = new LinkedList<>();
        int[] minCosts = new int[27];

        for(int node=0; node<=26; node++){
            if(isExist(node, costs)&&isRootNode(node, indegree)){
                que.add(node);
                minCosts[node] = costs[node];
            }
        }

        while(!que.isEmpty()){
            int current = que.poll();

            for(char child: graph[current]){
                int childIdx = charToIdx(child);
                indegree[childIdx]--;
                if(isRootNode(childIdx, indegree)){
                    que.add(childIdx);
                }
                minCosts[childIdx] = Math.max(minCosts[childIdx], minCosts[current]+costs[childIdx]);
            }
        }






        System.out.println(Arrays.stream(minCosts).max().getAsInt());



    }

    private static boolean isRootNode(int node, int[] indegree) {
        return indegree[node]==0;
    }

    private static boolean isExist(int node,int[] costs) {
        return costs[node] != 0;
    }

    private static void addEdges(List<Character>[] graph, int[] costs, int[] indegree) throws IOException {
        String input;
        while((input = buffer.readLine())!=null){
            if(input.isEmpty())break;
            tokens = new StringTokenizer(input);

            char node = tokens.nextToken().charAt(0);

            int cost = Integer.parseInt(tokens.nextToken());
            costs[charToIdx(node)] = cost;

            if(tokens.hasMoreTokens()){
                String parents = tokens.nextToken();

                for(char parent : parents.toCharArray()){

                    graph[charToIdx(parent)].add(node);
                    indegree[charToIdx(node)]++;
                }
            }
        }
    }

    private static int charToIdx(char parent) {
        return parent - 'A';
    }

    private static List<Character>[] initGraph() {
        List<Character>[] graph = new List[27];
        for(int node=0; node<=26; node++){
            graph[node] = new ArrayList<>();
        }
        return graph;
    }

}