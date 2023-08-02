import java.io.*; 

import java.util.*; 
class Main {
 
    static int N;
    static int[][] map;
    static int max;
    static int[][][][] d ={
            {
                {
                    {0,0,0,0},
                    {0,1,2,3}
                },
                {
                    {0,1,2,3},
                    {0,0,0,0}
                }
            },
            {
                {
                    {0,0,1,1},
                    {0,1,1,2}
                },
                {
                    {0,1,1,2},
                    {0,0,-1,-1}
                }
            },
            {
                {
                    {0,0,0,1},
                    {0,1,2,2}
                },
                {
                    {0,1,2,2},
                    {0,0,0,-1}
                },
                {
                    {0,1,1,1},
                    {0,0,1,2}
                },
                {
                    {0,0,1,2},
                    {0,1,0,0}
                }
            },
            {
                {
                    {0,1,1,1},
                    {0,-1,0,1}
                },
                {
                    {0,1,1,2},
                    {0,0,1,0}
                },
                {
                    {0,0,0,1},
                    {0,1,2,1}
                },
                {
                    {0,1,1,2},
                    {0,-1,0,0}
                }
            },
            {
                {
                    {0,0,1,1},
                    {0,1,1,0}
                }
            }
        
    };
    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int t = 1;
        
        while((N = Integer.parseInt(br.readLine().trim())) != 0) {
            map = new int[N][N];
            max = Integer.MIN_VALUE;
            
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken().trim());
                }
            }
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int a = 0; a < 5; a++) {
                        for (int b = 0; b < d[a].length; b++) {
                            int sum = 0;
                            boolean ok = true;
                            for (int c = 0; c < 4; c++) {
                                int x = d[a][b][0][c];
                                int y = d[a][b][1][c];
                                if(!isRange(i+x, j+y)) {
                                    ok = false;
                                    break;
                                }
                                sum += map[i+x][j+y];
                            }
                            if(ok) {
                                max = Math.max(max, sum);
                            }
                        }
                    }
                }
            }
            System.out.println((t++)+". "+max);
        }
    }
    
    static boolean isRange(int x, int y) {
        if( x < 0 || x >= N || y < 0 || y >= N) return false;
        return true;
    }
    
    static int getValue(int x, int y) {
        return map[x][y];
    }
}