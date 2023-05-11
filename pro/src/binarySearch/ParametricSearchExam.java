package binarySearch;

/* Parametric Search
 * 구간을 좁히면서 정답인지 물어본다.
 * 가장 마지막에 말했던것이 정답
 * 
 * */
public class ParametricSearchExam {
	public static void main(String[] args) {
		ParametricSearchExam.run();
	}

	private static void run() {
		String gage = "#######___";
		
		int ret = parametricSearch(gage);
		
		System.out.println((ret + 1) * 10 + "%");
	}

	private static int parametricSearch(String gage) {
		int start  = 0;
		int end = gage.length();
		int result = -1;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			int midValue = gage.charAt(mid);
			
			if (midValue == '#') {
				result = mid;
				start = mid + 1;
			}
			
			if (midValue == '_') {
				end = mid - 1;
			}
		}
		
		return result;
	}
}
