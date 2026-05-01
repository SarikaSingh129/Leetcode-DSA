class Solution {
public:
    int maxRotateFunction(vector<int>& nums) {
        int n = nums.size();
        int total_sum = 0;
        int f = 0;
        for (int i = 0; i < n; ++i) {
            total_sum += nums[i];
            f += i * nums[i];
        }
        int max_val = f;
        for (int i = 1; i < n; ++i) {
            f = f + total_sum - n * nums[n - i];
            max_val = max(max_val, f);
        }
        return max_val;
    }
};