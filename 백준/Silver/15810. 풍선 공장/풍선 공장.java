import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;

    public static void main(String[] args) throws IOException {
        tokens = new StringTokenizer(buffer.readLine());
        long n = Long.parseLong(tokens.nextToken()); // 스태프 인원수
        long target = Long.parseLong(tokens.nextToken()); // 풍선 갯수
        long[] neededTimes = Arrays.stream(buffer.readLine().split("\\s+")).mapToLong(Long::parseLong).toArray();

        // 다 만들어지는 최소 시간을 구한다.
        System.out.println(getMinTime(neededTimes, target));
    }

    private static long getMinTime(long[] neededTimes, long target) {
        long l = 1;
        long h = Arrays.stream(neededTimes).min().getAsLong() * target;

        while (l < h) {
            long mid = (l + h) / 2;

            if (isValid(neededTimes, target, mid)) {
                h = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }

    private static boolean isValid(long[] neededTimes, long target, long mid) {
        long totCount = 0;
        for (long neededTime : neededTimes) {
            totCount += (mid / neededTime);
            if (totCount >= target) {
                return true;
            }
        }
        return totCount >= target;
    }
}