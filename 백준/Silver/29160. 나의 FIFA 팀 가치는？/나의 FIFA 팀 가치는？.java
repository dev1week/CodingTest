
import java.io.*;
import java.util.*;


class Player implements Comparable<Player>
{

    int position, value;


    public Player(int position, int value){
        this.position = position;
        this.value = value;
    }

    public int compareTo(Player o){
        return o.value-this.value;
    }

    public void decreaseValue(){
        if(this.value==0)return;
        this.value--;
    }



}

public class Main {
    static BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));

    static StringTokenizer tokens;

    static final int TEAM_SIZE = 11;

    public static void main(String[] args)throws IOException {
       //포지션 번호 1~11

        //3월
            //같은 포지션 중 가치가 가장 높은 선수로 선발
            //여러명일 경우 아무나 택함
            //없을 경우 해당 포지션은 공석
        //8월
            //현재 팀에 있는 선수의 가치 --
            //0보다 떨어지지 않음
        //1월
            //3월처럼 선발선수 재구성



        //k년 12월이 되었을 때의 선수 가치 합은?

        Player[] team =new Player[TEAM_SIZE+1];

        PriorityQueue<Player>[] candidatePlayers = new PriorityQueue[TEAM_SIZE+1];

        for(int position=0; position<=TEAM_SIZE; position++){
            candidatePlayers[position] = new PriorityQueue<>();
        }

        tokens = new StringTokenizer(buffer.readLine());


        int n = Integer.valueOf(tokens.nextToken());
        int k = Integer.valueOf(tokens.nextToken());

        init(n, candidatePlayers);

        for(int i=0; i<k; i++){



            //1년 시작
            //3월
                //각 포지션 순회하면서
                    //가치가 가장 높은 선수 팀에 넣기
                    //원래 팀 인원이랑 다를 경우
                        //팀에 있는 사람을 큐에 넣기
                        //큐에 있는 사람 팀에 데려오기
            select(team, candidatePlayers);
            //8월
                //팀에 있는 모든 선수의 가치 1씩 떨어뜨리기
            decreaseAllPlayers(team);
            //11월
                //3월 방식대로
            select(team, candidatePlayers);
        }

        System.out.println(getTotalTeamValue(team));

        //팀의 선발 선수 가치 총합 구하기
    }

    private static int getTotalTeamValue(Player[] team){
        int result = 0;
        for(Player player: team){
            if(player==null)continue;
            result += player.value;
        }
        return result;
    }

    private static void decreaseAllPlayers(Player[] currentPlayers){
        for(Player currentPlayer: currentPlayers){
            if(currentPlayer==null)continue;
            currentPlayer.decreaseValue();
        }

    }

    private static void select(Player[] team, PriorityQueue<Player>[] candidatePlayers){

        for(int position=1; position<=TEAM_SIZE; position++){
            if(team[position]==null) {
                team[position] = candidatePlayers[position].poll();
                continue;
            }

            Player currentPlayer = team[position];

            Player candidatePlayer = candidatePlayers[position].poll();
            if(candidatePlayer==null)continue;
            if(currentPlayer.value<candidatePlayer.value){
                team[position] = candidatePlayer;
                candidatePlayers[position].add(currentPlayer);
            }else{
                candidatePlayers[position].add(candidatePlayer);
            }
        }

    }


    static void init(int n, PriorityQueue<Player>[] candidatePlayers)throws IOException{
        for(int i=0; i<n; i++){
            tokens = new StringTokenizer(buffer.readLine());

            int position = Integer.valueOf(tokens.nextToken());
            int value = Integer.valueOf(tokens.nextToken());

            candidatePlayers[position].add(new Player(position, value));
        }
    }



}
