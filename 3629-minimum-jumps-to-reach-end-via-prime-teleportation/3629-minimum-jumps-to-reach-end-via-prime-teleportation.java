class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int[] spf = new int[max + 1];
        for (int i = 2; i <= max; i++) {
            if (spf[i] == 0) {
                for (int j = i; j <= max; j += i) {
                    if (spf[j] == 0) {
                        spf[j] = i;
                    }
                }
            }
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int val = nums[i];
            Set<Integer> primes = new HashSet<>();
            while (val > 1) {
                int p = spf[val];
                primes.add(p);
                while (val % p == 0) {
                    val /= p;
                }
            }
            for (int p : primes) {
                map.computeIfAbsent(p, k -> new ArrayList<>()).add(i);
            }
        }
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.offer(0);
        visited[0] = true;
        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int idx = q.poll();
                if (idx == n - 1) {
                    return steps;
                }
                if (idx - 1 >= 0 && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                    q.offer(idx - 1);
                }
                if (idx + 1 < n && !visited[idx + 1]) {
                    visited[idx + 1] = true;
                    q.offer(idx + 1);
                }
                int val = nums[idx];
                if (val > 1 && spf[val] == val) {
                    if (map.containsKey(val)) {
                        for (int next : map.get(val)) {
                            if (!visited[next]) {
                                visited[next] = true;
                                q.offer(next);
                            }
                        }
                        map.remove(val);
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}