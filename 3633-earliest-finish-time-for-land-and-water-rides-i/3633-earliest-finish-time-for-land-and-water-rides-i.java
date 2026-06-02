class Solution {

    public int earliestFinishTime(
        int[] landStartTime,
        int[] landDuration,
        int[] waterStartTime,
        int[] waterDuration
    ) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < landStartTime.length; i++) {
            int finishLand =
                landStartTime[i] + landDuration[i];
            for (int j = 0; j < waterStartTime.length; j++) {
                int startWater =
                    Math.max(finishLand, waterStartTime[j]);
                int finishWater =
                    startWater + waterDuration[j];
                ans = Math.min(ans, finishWater);
            }
        }
        for (int i = 0; i < waterStartTime.length; i++) {

            int finishWater =
                waterStartTime[i] + waterDuration[i];
            for (int j = 0; j < landStartTime.length; j++) {
                int startLand =
                    Math.max(finishWater, landStartTime[j]);
                int finishLand =
                    startLand + landDuration[j];
                ans = Math.min(ans, finishLand);
            }
        }
        return ans;
    }
}