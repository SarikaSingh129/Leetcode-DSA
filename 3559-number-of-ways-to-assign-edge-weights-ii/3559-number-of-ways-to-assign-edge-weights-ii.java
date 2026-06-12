import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;
    static final int LOG = 17;
    List<Integer>[] graph;
    int[][] parent;
    int[] depth;
    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        parent = new int[LOG][n + 1];
        depth = new int[n + 1];
        for (int i = 0; i < LOG; i++) {
            Arrays.fill(parent[i], -1);
        }
        dfs(1, -1);
        for (int k = 1; k < LOG; k++) {
            for (int v = 1; v <= n; v++) {
                if (parent[k - 1][v] != -1) {
                    parent[k][v] = parent[k - 1][ parent[k - 1][v] ];
                }
            }
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            if (u == v) {
                ans[i] = 0;
                continue;
            }
            int lca = getLCA(u, v);
            int dist = depth[u] + depth[v] - 2 * depth[lca];
            ans[i] = modPow(2, dist - 1);
        }
        return ans;
    }
    private void dfs(int node, int par) {
        parent[0][node] = par;
        for (int nei : graph[node]) {
            if (nei == par) continue;
            depth[nei] = depth[node] + 1;
            dfs(nei, node);
        }
    }
    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }
        for (int k = LOG - 1; k >= 0; k--) {
            if (parent[k][u] != -1 &&
                depth[parent[k][u]] >= depth[v]) {
                u = parent[k][u];
            }
        }
        if (u == v) return u;
        for (int k = LOG - 1; k >= 0; k--) {
            if (parent[k][u] != -1 &&
                parent[k][u] != parent[k][v]) {
                u = parent[k][u];
                v = parent[k][v];
            }
        }
        return parent[0][u];
    }
    private int modPow(long base, long exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return (int) result;
    }
}