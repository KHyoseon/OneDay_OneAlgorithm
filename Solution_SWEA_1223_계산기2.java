import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

// SWEA 1223. [S/W 문제해결 기본] 6일차 - 계산기2

public class Solution_SWEA_1223_계산기2 {
	/*
	[입력]
	9
	3+4+5*6+7

	[출력]
	#1 44
	*/
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int t = 1;

		for (int tNum = 1; tNum <= t; tNum++) {
			sb.append("#").append(tNum).append(" ");
			
			// n: 문자열 길이
			int n = Integer.parseInt(br.readLine());
			String input = br.readLine();
			
			
			/* 문자열을 후위 표기식으로 바꾸기 */
			
			String postfix="";
			Stack<Character> stk = new Stack<>();
			
			for(int i=0; i<n; i++) {
				char cur = input.charAt(i);
				/*
				 * 현재 문자(cur)가
				 * + 라면 stk에 있는 모든 연산을 먼저 후위 표기식에 갖다 붙이고 자기 자신은 다시 stk에 넣음
				 * * 라면 stk에 넣음
				 * 숫자라면 바로 후위식에 갖다 붙임
				 */
				switch(cur) {
					case '+':
						while(!stk.isEmpty()) {
							postfix+=stk.pop();
						}
						stk.add(cur);			break;
					case '*':
						stk.add(cur);			break;
					default :
						postfix+=cur;
				}
			}

			// 모든 문자를 탐색한 뒤에는 stk에 남아있는 문자를 후위식에 붙임
			while(!stk.isEmpty())	postfix+=stk.pop();

			
			/* 후위 표기식을 계산하기 */
			Stack<Integer> ops = new Stack<>();
			for(int i=0; i<n; i++) {
				switch(postfix.charAt(i)) {
				case '+':
					int op1 = ops.pop();
					int op2 = ops.pop();
					ops.add(op1+op2);
					break;
				case '*':
					op1 = ops.pop();
					op2 = ops.pop();
					ops.add(op1*op2);
					break;
				default :
					ops.add(postfix.charAt(i)-'0');
				}
			}
			
			sb.append(ops.pop()+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
}
