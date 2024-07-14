import java.util.*; 

class Solution {
    public String solution(String newId) {
        
        
        //대문자 전부 소문자 치환 
        newId = newId.toLowerCase();
        //소문자, 숫자 -,_,.를 제외한 모든 문자 제거 
        newId = newId.replaceAll("[^a-z0-9\\-_.]", "");
        //연속된. 단일.으로 변경하기 
        newId = newId.replaceAll("\\.+",".");
        //.처음과 끝에 있다면 제거하기 
        newId = newId.replaceAll("^\\.+|\\.+$", "");
        //빈문자열일 경우 a 넣기 
        if(newId.length()==0){
            newId = "a";
        }
        if(newId.length()>=16){
            newId = newId.substring(0,15).replaceAll("\\.+$", "");
        }
        if(newId.length()<=2){
            while(newId.length()<3){
                newId += newId.charAt(newId.length()-1);
            }
        }
        //16자 이상일 경우 첫 15개의 문자를 제외한 나머지 문자를 모두 제거 
            //제거후 .이 맨끝에 올 경우 . 제거 
        //2자 이하일 경우 마지막 문자를 3이 될때까지 반복해서 끝에 붙임 
        
        
        
        return newId;
    }
}