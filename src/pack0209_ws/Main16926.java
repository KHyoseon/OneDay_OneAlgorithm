package pack0209_ws;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

// 백준 1874. 배열 돌리기 1

public class Main16926 {

	static int map[][];
	static int N, M, R;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		map = rotate(map);
		
		for(int j=0; j<N; j++) {
			for(int n: map[j]) {
				bw.write(n+" ");
			}
			bw.write("\n");
		}
		bw.flush();
	}
	
	private static int[][] rotate(int[][] map2) {
		int tmp[][] = new int[N][M];
		
		for(int i=0; i<R; i++) {
			for(int k=0; k<N/2+1; k++) {
				int u=k, r=M-1-k, d=N-1-k, l=k;
				if(d>u && r>l) {
					// 윗줄
					for(int j=l; j<r; j++) {
						tmp[u][j]=map2[u][j+1];
					}
					// 오른줄
					for(int j=u; j<d; j++) {
						tmp[j][r]=map2[j+1][r];
					}
					// 아랫줄
					for(int j=l; j<r; j++) {
						tmp[d][j+1]=map2[d][j];
					}
					// 왼쪽줄
					for(int j=u; j<d; j++) {
						tmp[j+1][l]=map2[j][l];
					}
				} else {
					for(int t=u; t<d+1; t++) {
						for(int j=l; j<r+1; j++) {
							tmp[t][j]=map2[t][j];
						}
					}
				}
			}
			map2 = tmp;
			tmp = new int[N][M];
		}
		
		return map2;
	}
}
