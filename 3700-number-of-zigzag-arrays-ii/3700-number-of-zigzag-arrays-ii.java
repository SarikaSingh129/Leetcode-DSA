class Solution {
    static final long MOD = 1_000_000_007L;
    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;
        long[][] transition = new long[size][size];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (j < i) {
                    transition[i][m + j] = 1;
                }
                if (j > i) {
                    transition[m + i][j] = 1;
                }
            }
        }
        long[] state = new long[size];
        for (int i = 0; i < m; i++) {
            state[i] = i;
            state[m + i] = m - 1 - i;
        }
        long[][] power = matrixPower(transition, n - 2);
        long[] result = multiplyMatrixVector(power, state);
        long answer = 0;
        for (long x : result) {
            answer = (answer + x) % MOD;
        }
        return (int) answer;
    }
    private long[][] matrixPower(long[][] base, long exp) {
        int size = base.length;
        long[][] result = new long[size][size];
        for (int i = 0; i < size; i++) {
            result[i][i] = 1;
        }
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, base);
            }
            base = multiply(base, base);
            exp >>= 1;
        }
        return result;
    }
    private long[][] multiply(long[][] a, long[][] b) {
        int n = a.length;
        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (a[i][k] == 0) continue;
                for (int j = 0; j < n; j++) {
                    result[i][j] = (result[i][j] + a[i][k] * b[k][j]) % MOD;
                }
            }
        }
        return result;
    }
    private long[] multiplyMatrixVector(long[][] matrix, long[] vector) {
        int n = matrix.length;
        long[] result = new long[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] = (result[i] + matrix[i][j] * vector[j]) % MOD;
            }
        }
        return result;
    }
}