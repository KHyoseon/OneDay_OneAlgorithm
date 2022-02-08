package pack0207;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;
import java.util.Stack;

// 백준 1874.

public class Main1874 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Integer> stack = new Stack<>();
		
		int N = sc.nextInt();
		int list[] = new int[N+1];
		for (int i = 1; i < N+1; i++) {
			list[i] = sc.nextInt();
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1, j=1; i<N+1; i++) {
			stack.add(i);
			sb.append("+\n");
			while((!stack.empty())&&(j<=N)&&(stack.peek() == list[j])) {
				stack.pop();
				sb.append("-\n");
				j++;
			}
		}
		if(stack.empty())
			bw.write(sb.toString());
		else
			bw.write("NO");
		bw.flush();
	}

}
