import java.awt.*;
import java.math.BigInteger;
import java.util.*;
import java.io.*;
import java.util.List;

public class Main {

    private static final BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer tokens;


    public static void main(String[] args)throws IOException {
        int n = Integer.parseInt(buffer.readLine());

        Meeting[] meetings = new Meeting[n];

        for(int i=0; i<n; i++){
            tokens= new StringTokenizer(buffer.readLine());
            int start = Integer.parseInt(tokens.nextToken());
            int end = Integer.parseInt(tokens.nextToken());
            int cnt = Integer.parseInt(tokens.nextToken());
            meetings[i] = new Meeting(start, end, cnt);
        }

        Arrays.sort(meetings);

        int[] previousMeetings = new int[n];
        for(int i=0; i<n; i++){
            int idx =-1;

            int l = 0;
            int r = i-1;

            while(r>=l){
                int mid = (l+r)/2;
                if(meetings[mid].end<=meetings[i].start){
                    idx = mid;
                    l = mid+1;
                }else{
                    r = mid-1;
                }
            }

            previousMeetings[i] = idx;
        }

        //미팅 배열을 정렬한다.

        //System.out.println(Arrays.toString(previousMeetings));

        //previousMeetings
        //이분 탐색으로 해당 미팅의 바로 직전 미팅들을 탐색하여 배열화 한다.

        int[] dp = new int[n];
        dp[0] = meetings[0].cnt;
        for(int i=1; i<n; i++){
            int totCnt = meetings[i].cnt;

            if(previousMeetings[i]!=-1){
                totCnt+=dp[previousMeetings[i]];
            }

            dp[i] = Math.max(dp[i-1], totCnt);
        }
        //dp[i] = i번째 미팅까지 고려했을 때 최대인원수
        System.out.println(dp[n-1]);
        //dp[0] = meetings[0].cnt;

        //dp[i] = Math.max(dp[i-1], dp[previousMeetings[i]]+meetings[i].cnt;




    }
}

class Meeting implements Comparable<Meeting>{
    int start, end,cnt;

    public Meeting(int start, int end, int cnt) {
        this.start = start;
        this.end = end;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Meeting o) {
        return this.end-o.end;
    }
}

