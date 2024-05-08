package leetcode.companies.facebook;

import java.util.Map;

public class MinParRemoveToMakeValid {

    public String minRemoveToMakeValid(String s) {
        int n=s.length();
        int[] occ=new int[n+1];
        for(int i=n-1;i>=0;i--) occ[i]=occ[i+1]+(s.charAt(i)==')'?1:0);
        StringBuilder sb=new StringBuilder();
        int balance=0;
        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(c==')'&&balance==0) continue;
            if(c=='('&&occ[i+1]<=balance) continue;
            if(c=='(') balance++;
            if(c==')') balance--;
            sb.append(c);
        }
        return sb.toString();
    }

}
