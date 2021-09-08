package aurora.com.ze.strategy.impl;


import aurora.com.ze.strategy.IStrategy;
import org.junit.jupiter.api.Test;

/**
 * @author Aurora
 * @date 2021/9/6 15:45
 */
public class StrategyImpl implements IStrategy {
    int mode = 1000000007;
    public int cuttingRope(int n) {
        if (n <= 3) return n-1;
        long result = 1;

        int count = n / 3;
        // 最后一个3先不要乘，避免之后除以3除不尽
        while (count > 1) {
            result = (result*3) % mode;
            count --;
        }

        int remain = n % 3;
        if (remain == 1) {
            result = (result*4) % mode;
        } else if (remain == 2) {
            result = (result*6) % mode;
        } else {
            result = result * 3 % mode;
        }
        return (int)result;
    }

    public static int fastPower(int base, int exp, int mode) {
        int result = 1;
        base = base % mode;

        while(exp > 0) {
            if ((exp & 1) == 1) {
                result = result * base % mode;
            }
            base = base * base % mode;
            exp = exp >> 1;
        }
        return result;
    }

    @Test
    public void test() {
        int res = fastPower(3, 23, 17);
        System.out.println(res);
    }

    public int test1(int a, int b, int c) {
        int res = 1;
        a = a % c;
        for (int i = 0; i < b; i++) {
            res = res * a % c;
        }
        return res % c;
    }
}
