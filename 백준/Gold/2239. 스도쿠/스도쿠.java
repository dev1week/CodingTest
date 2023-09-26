 

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main  {

	static StringBuilder sb;
	static int[][] ans;
	static ArrayList<int[]> list;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int N;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

//		char[][] arr = new char[9][9];
		ans = new int[9][9];
		list = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			String str = br.readLine();
			for (int j = 0; j < 9; j++) {
				ans[i][j] = str.charAt(j) - '0';
				if (ans[i][j] == 0)
					list.add(new int[] { i, j });
			}
		}

//
//		for (int i = 0; i < list.size(); i++)
//			System.out.println("list" + list.get(i)[0]);
		solve(0);
		System.out.println(sb.toString());
	}
// 

	static void solve(int depth) {

		if (depth == list.size()) {
//			System.out.println("!!");

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(ans[i][j]);
				}
				sb.append('\n');
			}
			flag = true;
			return;
		}

		int r = list.get(depth)[0]; // list 에서 값을 꺼냈으므로 ans[r][c] 는 다 0 이다.
		int c = list.get(depth)[1];

		boolean[] visit = new boolean[10];
		int nr = (r / 3) * 3;
		int nc = (c / 3) * 3;

		for (int i = nr; i < nr + 3; i++) { // 스도쿠에서 9등분 하여 네모난부분 .. 현재칸이 r,c 인데 여기에 다른 수랑 겹치면 안되니까 visit 을 true 로 해준다.
			for (int j = nc; j < nc + 3; j++) {
				int v = ans[i][j];
				if (v != 0)
					visit[v] = true;
			}
		}

		for (int i = 0; i < 9; i++) {
			int v = ans[r][i]; // 가로
			if (v != 0)
				visit[v] = true;
			v = ans[i][c];// 세로
			if (v != 0)
				visit[v] = true;

		}

		for (int i = 1; i <= 9; i++) {

			if (visit[i] == false) {
				if (flag)
					return;
				ans[r][c] = i;
//				System.out.println("r c" + r + " " + c + " ans " + ans[r][c]);
				solve(depth + 1);
				ans[r][c] = 0;

			}

		}

	}

}
