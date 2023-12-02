import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static int N;
	public static int M;
	public static int answer;
	public static int[][] map;
	public static boolean[] visited;
	public static List<Integer[]> chickenPlace;
	public static List<Integer[]> housePlace;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] tmp = br.readLine().split(" ");
		N = Integer.parseInt(tmp[0]);
		M = Integer.parseInt(tmp[1]);

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			tmp = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(tmp[j]);
			}
		}

		chickenPlace = new ArrayList<>();
		housePlace = new ArrayList<>();

		findPlace();

		visited = new boolean[chickenPlace.size()];
		answer = 100000;
		getChicken(0, M);
		System.out.println(answer);

	}

	public static void findPlace() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2)
					chickenPlace.add(new Integer[] { j, i });
				else if (map[i][j] == 1)
					housePlace.add(new Integer[] { j, i });
			}
		}
	}

	public static void getChicken(int start, int r) {
		if (r == 0) {
			getDistance();
			return;
		}

		for (int i = start; i < chickenPlace.size(); i++) {
			visited[i] = true;
			getChicken(i + 1, r - 1);
			visited[i] = false;
		}
	}

	public static void getDistance() {
		int[] distance = new int[housePlace.size()];

		for (int i = 0; i < housePlace.size(); i++) {
			int min_dis = 1000;
			for (int j = 0; j < chickenPlace.size(); j++) {
				if (visited[j] == true) {
					int d = Math.abs(housePlace.get(i)[0] - chickenPlace.get(j)[0])
							+ Math.abs(housePlace.get(i)[1] - chickenPlace.get(j)[1]);
					min_dis = Math.min(min_dis, d);
				}
			}
			distance[i] = min_dis;
		}
		
		int totalD = 0;
		for(int i=0; i<distance.length; i++) {
			totalD += distance[i];
		}
		answer = Math.min(answer, totalD);
	}
}