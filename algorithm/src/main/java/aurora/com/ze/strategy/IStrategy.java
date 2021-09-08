package aurora.com.ze.strategy;


import aurora.com.ze.annotation.LeetCode;

/**
 * @author Aurora
 * @date 2021/9/6 15:43
 */
public interface IStrategy {
    @LeetCode(name = "剪绳子2", desc = "把绳子剪成整数段，每段长度也是整数，求每段相乘的最大和（取模）")
    int cuttingRope(int n);
}
