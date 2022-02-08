package pack0208;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// 백준 2504. 괄호의 값

public class Main2504 {

	/* stack : 괄호와 *로 이루어진 문자열을 저장하는데 사용
	 * nums : 숫자를 저장하는데 사용
	 * dic :  괄호를 짝짓는데 사용 */
	
	static Stack<Character> stack;
	static Stack<Integer> nums;
	static Map<Character, Character> dic;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// stack, nums, dic 객체 할당
		stack = new Stack<>();
		nums = new Stack<>();
		dic = new HashMap<>();
		dic.put(')', '(');
		dic.put(']', '[');
		
		// result = 중간에 짝이 안맞는 오류가 발생하는지 판단 (true: 오류 없음, false: 오류 있음)
		boolean result = true;
		String input = br.readLine();
		
		/* ** 중심 알고리즘 **
		 * 1. 열린 괄호('(', '[')는 바로 stack에 넣음
		 * 2. 닫힌 괄호(')', ']')는 앞에 반드시 숫자(혹은 열린 괄호)가 있을 것. 그것과 현재의 닫힌 괄호를 사용해 포인트를 계산한다.
		 * 		계산된 포인트(숫자)는 stack에 '*' 기호로 대체하여 넣는다.
		 * 3. 그 외의 문자열은 취급하지 않는다. 만약 들어오면 result=false로 loop를 종료한다.
		 */
		int j=0;
		int point=0;
		do {
			switch(input.charAt(j)) {
			case '(':
			case '[':
				stack.add(input.charAt(j));
				break;
			case ')':
			case ']':
				point = calcPoint(input.charAt(j), 0);
				if(point<0) result = false;
				stack.add('*');
				break;
			default:
				result=false;
			}
		} while((j++<input.length()-1) && result);
		
		
		/* stack에 남아있는 문자열의 개수와 nums에 남아있는 문자열의 개수가 다른지 확인
		 * 	-> 같다: stack에는 숫자를 나타내는 '*'만 존재 (성공)
		 * 	-> 다르다: stack에는 '*'와 괄호 문자가 존재 (실패)
		 */
		if(stack.size()!=nums.size()) {
			bw.write("0\n");
			result = false;
		} else {
			int sum=0;
			for(int n: nums) {
				sum+=n;
			}
			bw.write(sum+"\n");
		}
		bw.flush();
	}
	
	
	/* **중심 알고리즘**
	 * 0. 닫힌 괄호(key)가 들어왔는데 stack이 비어있는 경우 -> 실패
	 * 1. stack의 마지막 요소가 괄호이고 key와 짝인 경우 -> point 계산
	 * 2. stack의 마지막 요소가 괄호인데 key와 짝이 아닌 경우 -> 실패
	 * 3. stack의 마지막 요소가 괄호가 아닌 경우(숫자인 경우)
	 * 		-> point에 더해서 다시 계산해 줘야함(재귀) -> calcPoint(key, point+p)
	 */
	/**
	 * @param key 현재 닫힌 괄호
	 * @param point key로 계산해야하는 point 값
     * @return key로 계산된 point 값 (재귀)
	 */
	private static int calcPoint(char key, int point) {
		if(stack.empty())	return Integer.MIN_VALUE;
		char tmp = stack.pop();
		if(tmp==dic.get(key)) {
			int flag=3;
			if(tmp=='(')	flag=2;
			if(point==0)	point++;
			nums.add(point*flag);
			return point*flag;
		}
		if(tmp=='(' || tmp=='[') {
			return Integer.MIN_VALUE;
		} else {
			int p = nums.pop();
			return calcPoint(key, point+p);
		}
	}

}
