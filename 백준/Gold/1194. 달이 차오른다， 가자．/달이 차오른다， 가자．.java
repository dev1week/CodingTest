import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.xml.stream.events.StartDocument;

public class Main {

	static class point {
		int r, c;
		int key;
		int len;

		public point(int r, int c, int key, int len) {
			super();
			this.r = r;
			this.c = c;
			this.key = key;
			this.len = len;
		}

	}

	static int[] dr = { 1, -1, 0, 0 };
	static int[] dc = { 0, 0, 1, -1 };
	static int N;
	static int M;
	static char ch[][];

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
//		System.out.println("n m" + N + " " + M);

		ch = new char[N][M];
		point start = null;

		for (int i = 0; i < N; i++) {
			String str = br.readLine();

			for (int j = 0; j < M; j++) {
				ch[i][j] = str.charAt(j);
//				System.out.print("chij" + ch[i][j]);
				if (ch[i][j] == '0') {
					start = new point(i, j, 0, 0);
				}
				// System.out.println();
			}

		}

		int v = findroute(start);

		System.out.println(v);
	}

	static int findroute(point start) {
		// key 6개 -> 2의 5승.. 비트마스킹.

		boolean[][][] visit = new boolean[N][M][64];

		Queue<point> q = new LinkedList<>();
		q.add(new point(start.r, start.c, 0, 1));
//		System.out.println("start" + start.r + " " + start.c);
		visit[start.r][start.c][0] = true;

		while (!q.isEmpty()) {
			point p = q.poll();
//			System.out.println("r c" + p.r + " " + p.c + " " + p.key);
			visit[p.r][p.c][p.key] = true;

			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				int nkey = p.key;
				int nlen = p.len;

				if (isvalid(nr, nc) && !visit[nr][nc][nkey] && ch[nr][nc] != '#') {

					if (ch[nr][nc] == '1') {
						return nlen;
					}

					if (ch[nr][nc] >= 'a' && ch[nr][nc] <= 'z') {
//						System.out.println("incap");
						visit[nr][nc][nkey] = true;
						int bitmask = 1 << (ch[nr][nc] - 'a');
						nkey = nkey | bitmask;
						q.add(new point(nr, nc, nkey, nlen + 1));
//						System.out.println("capadd" + nr + " " + nc);

					}

					else if (ch[nr][nc] >= 'A' && ch[nr][nc] <= 'Z') {

						int cur = 1 << (ch[nr][nc] - 'A');
//						System.out.println("in big" + (ch[nr][nc] - 'A') + " " + nkey);
//						System.out.println("cur" + cur);
//						System.out.println("(1 & cur) " + (1 & cur));
//						System.out.println("Math.pow(2, ch[nr][nc] - 'A')) " + Math.pow(2, ch[nr][nc] - 'A'));

						if ((nkey & cur) == Math.pow(2, ch[nr][nc] - 'A')) {

							q.add(new point(nr, nc, nkey, nlen + 1));
						}

					} else {

						q.add(new point(nr, nc, nkey, nlen + 1));
						visit[nr][nc][nkey] = true;

					}

				}

			}
		}

		return -1;

	}

	static boolean isvalid(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}