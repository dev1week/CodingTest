import java.io.*;
import java.util.*;

class Com {
    int idx, lv, wt;

    public Com(int idx, int lv, int wt){
        this.idx = idx;
        this.lv = lv;
        this.wt = wt;
    }

    public String toString(){
        return this.idx+"번 컴퓨터의 계층은 "+ lv+"이고 "+this.wt+"시간 동안 작동합니다.";
    }

    public int getTransferTimeTo(Com child) {
        int absNumberDiff = Math.abs(child.idx-this.idx);
        return absNumberDiff*absNumberDiff;
    }
}


public class Main {

    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    //컴퓨터
        //고유 번호 (1~n)
        //계급과 동작속도 (양의 정수)
        //가장 낮은 계급은 1

    //컴퓨터간 전송 시간
        //고유 번호로 결정
        //서로 번호의 차 제곱

    //한단계 낮은 컴퓨터가 먼저 실행되어야 실행 가능

    //제일 낮은 계급의 컴퓨터는 시스템 시동 동시에 시작




    public static void main(String[] args) throws IOException{

        int n = Integer.parseInt(buffer.readLine());
        List<Com>[] lvToCom = initGraph(n);

        int[] indegree = new int[n+1];
        List<Com>graph[] = getGraph(n, lvToCom, indegree);

        int[] totalWts = getTotalWts(graph, lvToCom, indegree, n);

        System.out.println(Arrays.stream(totalWts).max().getAsInt());


    }

    private static int[] getTotalWts(List<Com>[] graph, List<Com>[] lvToCom, int[] indegree, int n) {
        int totWts[] = new int[n+1];
        //그래프를 활용해 위상 정렬을 수행한다.
            //루트인 컴퓨터만 큐에 넣는다.
        Queue<Com> que = new LinkedList<>();

        for(Com root: lvToCom[1]){
            totWts[root.idx] = root.wt;
            que.add(root);
        }

        while(!que.isEmpty()){
            Com parent = que.poll();

            for(Com child : graph[parent.idx]){
                indegree[child.idx]--;
                if(indegree[child.idx]==0){
                    que.add(child);
                }
                totWts[child.idx] = Math.max(totWts[child.idx], getCurrentLvWt(totWts, child, parent));
            }
        }

        return totWts;
            //큐를 순회한다.
            //해당 컴퓨터의 하위 계층을 수행한다.
            //max(자식 컴퓨터의 실행 시간, 부모 컴퓨터의 처리 완료 시간 + 전송 시간 + 자식 컴퓨터의 작동 시간)

        //컴퓨터의 작업 완료시간 중 최대값을 찾는다.
    }

    private static int getCurrentLvWt(int[] totWts, Com child, Com parent) {

        return totWts[parent.idx] + child.wt+ parent.getTransferTimeTo(child);

    }

    private static List<Com>[] getGraph(int n, List<Com>[] lvToCom, int[] indegree) throws IOException{
        // 그래프를 만든다.


        //레벨별 컴퓨터 배열을 만든다.
        for(int idx=1; idx<=n; idx++){
            Com com = getComputer(idx, lvToCom);
        }

        List<Com>[] graph = initGraph(n);
        for(int lv=2; lv<=n; lv++){
            List<Com> parents = lvToCom[lv-1];
            List<Com> childs = lvToCom[lv];

            for(Com parent : parents) {
                for (Com child : childs) {
                    addChild(indegree, child, graph[parent.idx]);
                }
            }
        }


        return graph;
    }

    private static void addChild(int[] indegree, Com child, List<Com> graph) {
        graph.add(child);
        indegree[child.idx]++;
    }

    private static List<Com>[] initGraph(int n) {
        List<Com>[] graph = new List[n +1];
        for(int i = 0; i<= n; i++){
            graph[i] = new ArrayList<>();
        }
        return graph;
    }

    private static Com getComputer(int idx, List<Com>[] lvToCom) throws IOException {
        tokens= new StringTokenizer(buffer.readLine());
        int lv = Integer.parseInt(tokens.nextToken());
        int wt = Integer.parseInt(tokens.nextToken());
        Com com =  new Com(idx, lv, wt);
        lvToCom[lv].add(com);
        return com;
    }
}