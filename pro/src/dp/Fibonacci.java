package dp;

public class Fibonacci {
	static int[] topDown_memoization;
	static int[] BottomUp_tabulation;

	public static void main(String[] args) throws Exception {

		int N = 30;

		topDown_memoization = new int[N + 1];
		BottomUp_tabulation = new int[N + 1];

		long startTime = System.currentTimeMillis();
		System.out.println(recursion(N));
		long endTime = System.currentTimeMillis();
		System.out.println("營敝 衛除(ms) : " + (endTime - startTime));
		
		startTime = System.currentTimeMillis();
		System.out.println(topDown(N));
		endTime = System.currentTimeMillis();
		System.out.println("營敝 衛除(ms) : " + (endTime - startTime));
		
		startTime = System.currentTimeMillis();
		System.out.println(bottomUp(N));
		endTime = System.currentTimeMillis();
		System.out.println("營敝 衛除(ms) : " + (endTime - startTime));
		
	}

	private static int bottomUp(int n) {
		BottomUp_tabulation[0] = 0;
		BottomUp_tabulation[1] = 1;
        
        for(int i=2; i<=n; i++){
        	BottomUp_tabulation[i] = BottomUp_tabulation[i-1] + BottomUp_tabulation[i-2];
        }
        return BottomUp_tabulation[n];
	}

	private static int topDown(int n) {
		if (n < 2)
			return topDown_memoization[n] = n;
		
		if (topDown_memoization[n] > 0)
			return topDown_memoization[n];
		
		topDown_memoization[n] = topDown(n-1) + topDown(n-2);
		
		return topDown_memoization[n];
	}

	private static int recursion(int n) {
		if (n <= 1)
			return n;

		return recursion(n - 1) + recursion(n - 2);
	}
}
