class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[] dp = new int[n];
        int ans = 1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, dfs(i, arr, d, dp));
        }
        return ans;
    }
    public int dfs(int index, int[] arr, int d, int[] dp) {
        if (dp[index] != 0) {
            return dp[index];
        }
        int max = 1;
        for (int i = index + 1; i <= Math.min(index + d, arr.length - 1); i++) {

            if (arr[i] >= arr[index]) {
                break;
            }
            max = Math.max(max, 1 + dfs(i, arr, d, dp));
        }
        for (int i = index - 1; i >= Math.max(index - d, 0); i--) {

            if (arr[i] >= arr[index]) {
                break;
            }
            max = Math.max(max, 1 + dfs(i, arr, d, dp));
        }
        dp[index] = max;
        return max;
    }
}