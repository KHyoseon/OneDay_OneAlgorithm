import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int r, c;
		int key;
		public Node(int r, int c, int key) {
			this.r = r;
			this.c = c;
			this.key = key;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int time = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
			
		char maze[][] = new char[N][M];
		
		int start[] = new int[2];
		for(int i=0; i<N; i++) {
			String line = br.readLine();
			maze[i] = line.toCharArray();
			if(line.contains("0")) {
				start[0] = i;
				start[1] = line.indexOf('0');
			}
		}
		
		time = findWayExit(maze, start);
		
		System.out.println(time);
	}
	
	static int dr[] = {-1, 0, 1, 0};
	static int dc[] = {0, 1, 0 ,-1};

	private static int findWayExit(char[][] maze, int[] start) {
		int r = maze.length;
		int c = maze[0].length;
		
		boolean[][][] visited = new boolean[r][c][1<<6];
		
		Queue<Node> current = new LinkedList<>();
		current.add(new Node(start[0], start[1], 0));
		
		int dist = 0;
		
		while(!current.isEmpty()) {
			++dist;
			Queue<Node> next = new LinkedList<>();
			while(!current.isEmpty()) {
				Node cur = current.poll();
				
				for(int m=0; m<4; m++) {
					int nr = cur.r+dr[m];
					int nc = cur.c+dc[m];
					int nkey = cur.key;
					if(0>nr || nr>=r || 0>nc || nc>=c)	continue;
				
					char pos = maze[nr][nc];
					
					if(pos == '1')		return dist;
					if(pos == '#')		continue;
					
					if(visited[nr][nc][nkey])		continue;
					visited[nr][nc][nkey] = true;

					if('a'<=pos && pos<='z') {
						nkey |= 1<<(pos-'a');
						next.add(new Node(nr, nc, nkey));
					}
					else if('A'<=pos && pos<='Z') {
						if((nkey & 1<<(pos-'a')) != 0){
							next.add(new Node(nr, nc, nkey));
						}
					} else if(pos == '.' || pos == '0') {
						next.add(new Node(nr, nc, nkey));
					}
						
				}
			}
			current = next;
		}
		return -1;
	}
}