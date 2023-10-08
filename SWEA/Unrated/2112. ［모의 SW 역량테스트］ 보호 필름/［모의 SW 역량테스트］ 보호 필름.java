import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 완탐!
// 모든 경우의 수 <= 3가지, 
//  1: 특정 막에 약 사용 X
//  2: 특정 막에 약 A 사용
//  3: 특정 막에 약 B 사용
public class Solution {
	
	static int T, D, W, K, min;
	static int[][] film, copy; // copy 는 완탐과정에서 film 배열을 변경해 본후, 원복용도
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			film = new int[D][W];
			copy = new int[D][W];
			
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = copy[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 풀이
			if( K == 1 ) { // 약품을 사용하지 않아도 모두 통과
				sb.append("#").append(t).append(" ").append(0).append("\n");
				continue;
			}
			min = Integer.MAX_VALUE;
			dfs( 0, 0 ); // 맨 위, 약품은 사용안한 상태
			
			sb.append("#").append(t).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	// idx 막 시작~끝
	// cnt 약품 몇 번 사용
	// 바닥(마지막)까지 따지면 check 후 min 갱신
	static void dfs(int idx, int cnt) {
		// 현재 idx까지 기준으로 유효성 검사
		if( check() ) { // idx 까지 따졌는데 모두 검사 통과
			min = Math.min(min, cnt);
			return;
		}
		
		// 기저조건
		if( idx == D ) return;
		
		// 가지치기
		if( min <= cnt ) return;
		
		// 약품을 사용하지 않음
		dfs( idx + 1, cnt); // cnt는 변화 X
		
		// 약품 A 를 사용
		// idx 막(row) 0 변경
		Arrays.fill(film[idx], 0);
		dfs( idx + 1, cnt + 1);
		
		// 약품 B 를 사용
		// idx 막(row) 1 변경
		Arrays.fill(film[idx], 1);
		dfs( idx + 1, cnt + 1);
		
		// 현재  film[idx] 원복
		film[idx] = Arrays.copyOf(copy[idx], W);
	}
	
	// 전체 film 배열을 검증
	// 옆으로 가면서 ( 바깥쪽 for )
	//   밑으로 가면서 검증
	static boolean check() {
		
		for (int j = 0; j < W; j++) {
			
			// 각 열별로 성공 여부
			boolean success = false;
			int cnt = 1; // 시작 숫자 
			for (int i = 1; i < D; i++) {// 2막
				if( film[i][j] == film[i-1][j] ) cnt++;
				else cnt = 1;
				
				if( cnt == K ) {
					success = true;
					break; // 아래로 더 따질 필요 X
				}
				// success <= true
			}
			
			if( !success ) return false;
		}
		return true;
	}
}
