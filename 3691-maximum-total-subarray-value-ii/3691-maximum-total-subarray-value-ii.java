import java.util.*;

class Solution {
    static class Node {
        long val;
        int l, r;
        Node(long val, int l, int r) {
            this.val = val;
            this.l = l;
            this.r = r;
        }
    }
    int[][] maxST;
    int[][] minST;
    int[] lg;
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;
        build(nums);
        PriorityQueue<Node> pq =
            new PriorityQueue<>((a, b) -> Long.compare(b.val, a.val));
        for (int l = 0; l < n; l++) {
            long value = getMax(l, n - 1) - getMin(l, n - 1);
            pq.offer(new Node(value, l, n - 1));
        }
        long ans = 0;
        while (k-- > 0 && !pq.isEmpty()) {
            Node cur = pq.poll();
            ans += cur.val;
            if (cur.r - 1 >= cur.l) {
                int newR = cur.r - 1;
                long value =
                    getMax(cur.l, newR) - getMin(cur.l, newR);
                pq.offer(new Node(value, cur.l, newR));
            }
        }
        return ans;
    }
    private void build(int[] nums) {
        int n = nums.length;
        lg = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            lg[i] = lg[i / 2] +1;
        }
        int K = lg[n] + 1;
        maxST = new int[K][n];
        minST = new int[K][n];
        for (int i = 0; i < n; i++) {
            maxST[0][i] = nums[i];
            minST[0][i] = nums[i];
        }
        for (int j = 1; j < K; j++) {
            for (int i = 0; i + (1 << j) <= n; i++) {
                maxST[j][i] = Math.max(
                    maxST[j - 1][i],
                    maxST[j - 1][i +(1 << (j - 1))]
                );
                minST[j][i] = Math.min(
                    minST[j - 1][i],
                    minST[j - 1][i + (1 << (j - 1))]
                );
            }
        }
    }
    private int getMax(int l, int r) {
        int j = lg[r - l + 1];
        return Math.max(
            maxST[j][l],
            maxST[j][r - (1 << j) + 1]
        );
    }
    private int getMin(int l, int r) {
        int j = lg[r - l + 1];
        return Math.min(
            minST[j][l],
            minST[j][r - (1 << j) + 1]
        );
    }
}