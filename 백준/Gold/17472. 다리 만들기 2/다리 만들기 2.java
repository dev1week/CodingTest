
import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	int start, end, w; 
	
	public Edge(int start, int end, int w) {
		this.start= start;
		this.end = end;
		this.w = w; 
	}
	
	public String toString() {
		return this.start+"-"+this.w+">"+this.end;
	}
	
	@Override 
	public int compareTo(Edge o) {
		return this.w - o.w;
	}
	
}

class Point{
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y =y; 
	}
	
	public String toString() {
		return this.x+":"+this.y; 
	}
}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    
    //다리의 길이 
    	//격자에서 차지하는 칸의 수 
    	//2 이상이어야한다. (길이 1은 안됨) 
    	//가로 세로만 가능 도중에 꺾이면 안됨 
    static int n; //~10
    static int m; //~10 
    
    
    static int[][] map; 
    static int[][] nodeNum;
    
    static boolean[][] isVisited; 
    
    //간선 갯수 10*10*4 
    
    static int N;
    static ArrayList<Edge> edges;
    static int[] parents;
    
    static void init() throws IOException{
    	tokens = new StringTokenizer(buffer.readLine()); 
    	n = Integer.valueOf(tokens.nextToken()); 
    	m = Integer.valueOf(tokens.nextToken()); 
    	
    	map = new int[n][m]; 
    	nodeNum = new int[n][m];
    	isVisited = new boolean[n][m];
    	
    	for(int x=0; x<n; x++) {
    		tokens = new StringTokenizer(buffer.readLine());
    		for(int y=0; y<m; y++) {
    			map[x][y] = Integer.valueOf(tokens.nextToken());
    			if(map[x][y]==1) {
    				N++;
    				nodeNum[x][y] = N;
    			}
    		}
    	}
    	//플로이드필로채우기 
    	//그래프 번호 매칭하기 
    	//그래프 만들기 
    	//mst 만들기 
    	edges = new ArrayList<>(); 
    }
    
    static void print(int[][] arr) {
    	for(int[] ar:arr) {
    		for(int a: ar) {
    			System.out.print(a+" ");
    		}System.out.println();
    	}
    }
    
    static void makeGraph() {
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<m; y++) {
    			if(map[x][y]!=0) {
    				int nx = x;
    				int ny = y; 
    				for(int dir=0; dir<4; dir++) {
    					int distance =0; 
    					while(true) {
    						nx+=dx[dir];
    						ny+=dy[dir]; 
    						distance ++; 
    						if(!inRange(nx, ny)||map[nx][ny]==map[x][y]) {
    							break; 
    						}
    						if(map[nx][ny]!=map[x][y]&&map[nx][ny]!=0) {
    							if(distance>2) {
    								edges.add(new Edge(nodeNum[x][y], nodeNum[nx][ny], distance-1));	
    							}
    							break; 
    						}
    					}
    				}
    				
    			}
    			
    			
    		}
    	}
    }
    
    public static void main(String[] args)throws IOException{
    	
    	init();
    	parents = new int[N+1]; 

    	for(int node=0; node<=N; node++) {
    		parents[node] = node; 
    	}
    	floodFill();
    	
    	makeGraph();
    	
    	Collections.sort(edges);
    	
    	int num = 0; 
    	int result =0; 
    	
    	for(Edge e : edges) {
    		if(find(e.start)!=find(e.end)) {
    			union(e.start, e.end);
    			num ++; 
    			result += e.w; 
    			if(num ==N-1) {
    				break; 
    			}
    		}
    	}
    	
    	if(isAllConnected()) {
        	System.out.println(result);

    	}else {
    		System.out.println(-1);
    	}
    	 
    	
    	
    }
    
    static boolean isAllConnected() {
    	for(int node=1; node<N; node++) {
    		if(find(node)!=find(node+1)) {
    			return false;
    		}
    	}return true; 
    }
    
    static int find(int a) {
    	if(parents[a]==a) {
    		return a; 
    	}
    	return parents[a] = find(parents[a]);
    }
    
    static void union(int a, int b) {
    	int pA = find(a); 
    	int pB = find(b); 
    	
    	parents[pA]=pB; 
    }
    
    static void floodFill() {
    	int tmp =1;
    	for(int x=0; x<n; x++) {
    		for(int y=0; y<m; y++) {
    			if(map[x][y]!=0&&!isVisited[x][y]) {
    				bfs(x,y,tmp);
    				tmp++; 
    			}
    		}
    	}
    	
    	
    }
    
    static final int[] dx = {-1,1,0,0};
    static final int[] dy = {0,0,-1,1};
    
    static void bfs(int sx, int sy, int tmp) {
    	
    	ArrayDeque<Point> que = new ArrayDeque<>();
    	que.add(new Point(sx, sy)); 
    	isVisited[sx][sy] = true; 
    	map[sx][sy] = tmp;
    	while(!que.isEmpty()) {
    		Point cur = que.poll(); 
    		for(int dir=0; dir<4; dir++) {
    			int nx = cur.x + dx[dir];
    			int ny = cur.y + dy[dir]; 
    			if(inRange(nx, ny)&&!isVisited[nx][ny]&&map[nx][ny]==1) {
    				map[nx][ny] =tmp;
    				union(nodeNum[cur.x][cur.y], nodeNum[nx][ny]);
    				isVisited[nx][ny] = true; 
    				que.add(new Point(nx, ny));
    			}
    		}
    		
    	}
    }
    
    static boolean inRange(int x, int y) {
    	return x>=0&&y>=0&&x<n&&y<m; 
    }
    
    
    	
}