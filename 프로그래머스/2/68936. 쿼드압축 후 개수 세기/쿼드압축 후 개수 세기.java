class Solution {
    private static int[] compress(int data){
        if(data==1){
            return new int[] {0,1};
        }else {
            return new int[]{1, 0};
        }
    }

    private static int[] getCount(int positionX, int positionY, int offset, int[][] arr){

        if(offset==1){
            return compress(arr[positionX][positionY]);
        }


        int zeroCount = 0;
        int oneCount = 0;
        for(int x=positionX;x<positionX+offset;x++){
            for(int y=positionY;y<positionY+offset;y++){
                if(arr[x][y]==1){
                    oneCount++;
                }else{
                    zeroCount++;
                }
                if(oneCount == offset*offset){

                    return compress(arr[x][y]);
                }else if(zeroCount == offset*offset){

                    return compress(arr[x][y]);
                }
            }
        }

        int[] count1= getCount(positionX, positionY, offset/2, arr);
        int[] count2 = getCount(positionX+offset/2, positionY, offset/2, arr);
        int[] count3 = getCount(positionX, positionY+offset/2, offset/2, arr);
        int[] count4 = getCount(positionX+offset/2,positionY+offset/2,offset/2,arr );

        return new int[] {count1[0]+count2[0]+count3[0]+count4[0], count1[1]+count2[1]+count3[1]+count4[1]};

    }
    
    public int[] solution(int[][] arr) {
        int[] answer = getCount(0,0,arr.length, arr);
        return answer;
    }
}