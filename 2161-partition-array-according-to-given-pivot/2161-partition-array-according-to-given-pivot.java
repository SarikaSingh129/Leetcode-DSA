class Solution {
    public int[] pivotArray(int[] nums, int pivot) {

        List<Integer> smaller = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greater = new ArrayList<>();
        for (int num : nums) {
            if (num < pivot) {
                smaller.add(num);
            }
            else if (num == pivot) {
                equal.add(num);
            }
            else{
                greater.add(num);
            }
        }

        int index = 0;
        for (int num : smaller) {
            nums[index++] = num;
        }
        for (int num : equal) {
            nums[index++] = num;
        }
        for (int num : greater) {
            nums[index++] = num;
        }
        return nums;
    }
}