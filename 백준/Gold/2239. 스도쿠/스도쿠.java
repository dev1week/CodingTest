import java.util.Scanner;

public class Main {
    static int[][] board = new int[9][9];

    public static void main(String[] args) {
        init(); // 스도쿠 초기화
        solve(0, 0); // 스도쿠 풀이 시작
    }

    static void init() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 9; i++) {
            String line = scanner.next();
            for (int j = 0; j < 9; j++) {
                board[i][j] = line.charAt(j) - '0'; // 문자열을 정수로 변환
            }
        }
        scanner.close();
    }

    static boolean isValid(int row, int col, int num) {
        // 행과 열에 중복된 숫자가 있는지 확인
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        // 3x3 박스에 중복된 숫자가 있는지 확인
        int boxStartRow = row - (row % 3);
        int boxStartCol = col - (col % 3);
        for (int i = boxStartRow; i < boxStartRow + 3; i++) {
            for (int j = boxStartCol; j < boxStartCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    static void solve(int row, int col) {
        if (row == 9) {
            // 모든 행을 확인했으면 결과 출력
            printBoard();
            System.exit(0); // 첫 번째 답을 찾으면 프로그램 종료
            return;
        }

        if (board[row][col] == 0) {
            // 현재 위치가 0이면 (빈 칸이면)
            for (int num = 1; num <= 9; num++) {
                if (isValid(row, col, num)) {
                    // 숫자가 유효하면 해당 위치에 숫자를 넣고 다음 위치로 이동
                    board[row][col] = num;
                    int nextRow = col == 8 ? row + 1 : row;
                    int nextCol = col == 8 ? 0 : col + 1;
                    solve(nextRow, nextCol);
                    board[row][col] = 0; // 백트래킹
                }
            }
        } else {
            // 현재 위치에 숫자가 이미 채워져 있으면 다음 위치로 이동
            int nextRow = col == 8 ? row + 1 : row;
            int nextCol = col == 8 ? 0 : col + 1;
            solve(nextRow, nextCol);
        }
    }

    static void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}