import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class Main {
	public static int[][] map;
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, -1, 0, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] info = new int[N][4];
		map = new int[101][101];

		for (int i = 0; i < N; i++) {
			String[] tmp = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				info[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			getPoints(info[i][0], info[i][1], info[i][2], info[i][3]);
		}

		int result = getSquares();
		System.out.println(result);
	}

	public static void getPoints(int x, int y, int dir, int generation) {
		Stack<Integer> dirStack = getDir(dir, generation);
		map[y][x] = 1;
		for (int i=0; i<dirStack.size(); i++) {
			int d = dirStack.get(i);
			x += dx[d];
			y += dy[d];
			map[y][x] = 1;
		}
	}

	public static Stack<Integer> getDir(int dir, int generation) {
		Stack<Integer> stack = new Stack<>();

		stack.push(dir);
		for (int i = 0; i < generation; i++) {
			Stack<Integer> tmpStack = new Stack<>();
			Queue<Integer> tmpQue = new LinkedList<>();

			while (!stack.isEmpty()) {
				int num = stack.pop();
				tmpStack.push(num);
				tmpQue.offer((num + 1) % 4);
			}
			stack = getherNum(tmpStack, tmpQue);
		}
		return stack;
	}

	public static Stack<Integer> getherNum(Stack<Integer> s, Queue<Integer> q) {
		Stack<Integer> result = new Stack<>();
		while (!s.isEmpty())
			result.push(s.pop());
		while (!q.isEmpty())
			result.push(q.poll());
		return result;
	}

	public static int getSquares() {
		int squareCnt = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1)
					squareCnt++;
			}
		}
		return squareCnt;
	}
}