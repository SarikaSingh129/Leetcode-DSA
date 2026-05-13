class Solution {
public:
    int minMoves(vector<int>& nums, int limit) {
        int n = nums.size();
        vector<int> diff(2 * limit + 2, 0);
        for (int i = 0; i < n / 2; i++){
            int a = nums[i];
            int b = nums[n - 1 - i];
            int low = min(a, b);
            int high = max(a, b);
            diff[2] += 2;
            diff[low + 1] -= 1;
            diff[a + b] -= 1;
            diff[a + b + 1] += 1;
            diff[high + limit + 1] += 1;
        }
        int ans = INT_MAX;
        int curr = 0;
        for (int sum = 2; sum <= 2 * limit; sum++){
            curr += diff[sum];
            ans = min(ans, curr);
        }
        return ans;
    }
};