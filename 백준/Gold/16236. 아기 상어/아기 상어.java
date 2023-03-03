import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 백준 16236. 아기상어

public class Main {
	
	static int move[][] = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	static int N, size = 2, eaten = 0;
	static int map[][], baby[];

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		baby = new int[2];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					baby[0] = i;	baby[1] = j;
				}
			}
		}
		
		int t=0;
		while(true) {
			// 제일 가까운 곳으로 이동
			int dist = getClosestFish();
			if(dist<0)	break;
			t += dist;
		}

		bw.write(t+"");
		bw.flush();
	}

	static Comparator<int[]> compare = new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[0]==o2[0]?o1[1]-o2[1]:o1[0]-o2[0];
		}
	};
	private static int getClosestFish() {
		Queue<int[]> queue = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		queue.offer(baby);
		visited[baby[0]][baby[1]] = true;
		map[baby[0]][baby[1]] = 0;
		
		int dist=-1;
		while(!queue.isEmpty()) {
			++dist;
			LinkedList<int[]> child = new LinkedList<>();
			while(!queue.isEmpty()) {
				int tmp[] = queue.poll();
				if(map[tmp[0]][tmp[1]]<9 && map[tmp[0]][tmp[1]]>size)	continue;
				if(map[tmp[0]][tmp[1]]>0 && map[tmp[0]][tmp[1]]<size) {
					map[tmp[0]][tmp[1]] = 0;
					baby = tmp;
					++eaten;
					if(eaten >= size) {
						++size;
						eaten = 0;
					}
					return dist;
				}
				for(int m=0; m<4; m++) {
					int nx = tmp[0]+move[m][0];
					int ny = tmp[1]+move[m][1];
					if((0<=nx && nx<N && 0<=ny && ny<N) && !visited[nx][ny]) {
						visited[nx][ny] = true;
						int nPos[] = {nx, ny};
						child.offer(nPos);
					}
				}
				Collections.sort(child, compare);
			}
			queue = child;
		}
		return -1;
	}

}