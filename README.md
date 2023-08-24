"# algorithm" 

// 출력
static void print(int i, String cmd, int ans, int uans, Object... obj) {
	if (ans != uans) System.out.println("---------- fail ----------");
	System.out.println("[" + i + "] " + cmd + " / " + ans + " / " + uans + " / " + Arrays.deepToString(obj));
}