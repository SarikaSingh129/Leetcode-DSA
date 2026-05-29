class Solution {
    public int minElement(int[] nums) {
        int minVal = Integer.MAX_VALUE;
        for (int num : nums) {
            minVal = Math.min(minVal, getDigitSum(num));
        }
        return minVal;
    }
    private int getDigitSum(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }
        return sum;
    }
}