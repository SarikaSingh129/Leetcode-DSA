class Solution {
public:
    vector<vector<int>> rotateGrid(vector<vector<int>>& grid, int k) {
        
        int m = grid.size();
        int n = grid[0].size();

        int layers = min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {

            vector<int> v;

            int top = layer;
            int bottom = m - layer - 1;
            int left = layer;
            int right = n - layer - 1;

            for (int j = left; j <= right; j++)
                v.push_back(grid[top][j]);

            for (int i = top + 1; i < bottom; i++)
                v.push_back(grid[i][right]);

            for (int j = right; j >= left; j--)
                v.push_back(grid[bottom][j]);

            for (int i = bottom - 1; i > top; i--)
                v.push_back(grid[i][left]);

            int sz = v.size();
            int rot = k % sz;

            rotate(v.begin(), v.begin() + rot, v.end());

            int idx = 0;

            for (int j = left; j <= right; j++)
                grid[top][j] = v[idx++];

            for (int i = top + 1; i < bottom; i++)
                grid[i][right] = v[idx++];

            for (int j = right; j >= left; j--)
                grid[bottom][j] = v[idx++];

            for (int i = bottom - 1; i > top; i--)
                grid[i][left] = v[idx++];
        }

        return grid;
    }
};