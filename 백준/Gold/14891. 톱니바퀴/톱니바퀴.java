import java.io.*;


import java.util.*;

class Main {
    static int[][] wheel = new int[4][8];

   
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        for (int i = 0; i < 4; i++) {
            line = br.readLine();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
        int K = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            turnLogic(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int answer = 0;
        if (wheel[0][0] == 1) answer += 1;
        if (wheel[1][0] == 1) answer += 2;
        if (wheel[2][0] == 1) answer += 4;
        if (wheel[3][0] == 1) answer += 8;
        System.out.println(answer);
    }

    public static void turnLogic(int wheelNum, int dir) {
        int[] dx = {-1, 1};
        Queue<Turn> searchQ = new ArrayDeque<>();
        Queue<Turn> q = new ArrayDeque<>();
        searchQ.add(new Turn(wheelNum - 1, dir));
        q.add(new Turn(wheelNum - 1, dir));
        boolean[] visit = new boolean[4];
        visit[wheelNum - 1] = true;
        while (!searchQ.isEmpty()) {
            Turn cur = searchQ.poll();
            for (int i = 0; i < 2; i++) {
                int newWheel = cur.wheel + dx[i];
                if (newWheel >= 4 || newWheel < 0 || visit[newWheel]) continue;
                visit[newWheel] = true;
                if (i == 0 && wheel[cur.wheel][6] != wheel[newWheel][2]) {
                    searchQ.add(new Turn(newWheel, -cur.dir));
                    q.add(new Turn(newWheel, -cur.dir));
                } else if (i == 1 && wheel[cur.wheel][2] != wheel[newWheel][6]) {
                    searchQ.add(new Turn(newWheel, -cur.dir));
                    q.add(new Turn(newWheel, -cur.dir));
                }
            }
        }
        while (!q.isEmpty()) {
            Turn cur = q.poll();
            turnWheel(cur.wheel, cur.dir);
        }
    }

    public static void turnWheel(int wheelNum, int dir) {
        int[] target = wheel[wheelNum];
        if (dir == 1) {
            int move = target[0];
            for (int i = 0; i < 7; i++) {
                int second = target[i + 1];
                target[i + 1] = move;
                move = second;
            }
            target[0] = move;
        } else if (dir == -1) {
            int move = target[7];
            for (int i = 7; i > 0; i--) {
                int second = target[i - 1];
                target[i - 1] = move;
                move = second;
            }
            target[7] = move;
        }

    }
}

class Turn {
    int wheel, dir;

    public Turn(int wheel, int dir) {
        this.wheel = wheel;
        this.dir = dir;
    }
}