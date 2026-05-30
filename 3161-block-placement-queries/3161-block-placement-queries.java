class Solution {

    class SegmentTree {

        int[] tree;

        SegmentTree(int n) {
            tree = new int[4 * n];
        }

        void update(int node, int l, int r, int idx, int val) {

            if (l == r) {
                tree[node] = val;
                return;
            }

            int mid = (l + r) / 2;

            if (idx <= mid) {
                update(node * 2, l, mid, idx, val);
            } else {
                update(node * 2 + 1, mid + 1, r, idx, val);
            }

            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }

        int query(int node, int l, int r, int ql, int qr) {

            if (qr < l || r < ql) {
                return 0;
            }

            if (ql <= l && r <= qr) {
                return tree[node];
            }

            int mid = (l + r) / 2;

            return Math.max(
                query(node * 2, l, mid, ql, qr),
                query(node * 2 + 1, mid + 1, r, ql, qr)
            );
        }
    }

    public List<Boolean> getResults(int[][] queries) {

        int MAX = 50000;

        TreeSet<Integer> set = new TreeSet<>();

        set.add(0);
        set.add(MAX);

        SegmentTree seg = new SegmentTree(MAX + 1);

        seg.update(1, 0, MAX, MAX, MAX);

        List<Boolean> ans = new ArrayList<>();

        for (int[] q : queries) {

            if (q[0] == 1) {

                int x = q[1];

                if (set.contains(x)) {
                    continue;
                }

                Integer prev = set.lower(x);
                Integer next = set.higher(x);

                seg.update(1, 0, MAX, next, 0);

                seg.update(1, 0, MAX, x, x - prev);

                seg.update(1, 0, MAX, next, next - x);

                set.add(x);

            } else {

                int x = q[1];
                int sz = q[2];

                Integer prev = set.floor(x);

                int best = seg.query(1, 0, MAX, 0, prev);

                best = Math.max(best, x - prev);

                ans.add(best >= sz);
            }
        }

        return ans;
    }
}