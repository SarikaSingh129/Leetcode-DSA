class Solution {

    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        return dfs(arr, start, visited);
    }
    private boolean dfs(int[] arr, int index, boolean[] visited){
        if(index < 0 || index >= arr.length){
            return false;
        }
        if(visited[index]){
            return false;
        }
        if(arr[index] == 0){
            return true;
        }
        visited[index] = true;
        boolean forward = dfs(arr, index + arr[index], visited);
        boolean backward = dfs(arr, index - arr[index], visited);
        return forward || backward;
    }
}