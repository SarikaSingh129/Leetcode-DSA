class Solution {

    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] reachable = new boolean[n];
        reachable[0] = true;
        int reachableCount = 0;
        for (int i = 1; i < n; i++) {
            if (i - minJump >= 0 && reachable[i - minJump]){
                reachableCount++;
            }
            if (i - maxJump - 1 >= 0 && reachable[i - maxJump - 1]){
                reachableCount--;
            }
            if (s.charAt(i) == '0' && reachableCount > 0){
                reachable[i] = true;
            }
        }
        return reachable[n - 1];
    }
}