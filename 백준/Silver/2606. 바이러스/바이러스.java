import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		HashMap<Integer, ArrayList<Integer>> paths = new HashMap<>();
		for (int i = 0; i < N + 1; i++) {
			paths.put(i, new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			String[] tmp = br.readLine().split(" ");
			int from = Integer.parseInt(tmp[0]);
			int to = Integer.parseInt(tmp[1]);
			paths.get(from).add(to);
			paths.get(to).add(from);
		}

		boolean[] visited = new boolean[N + 1];

		int answer = 0;
		Queue<Integer> que = new LinkedList<>();

		visited[1] = true;
		que.offer(1);

		while (!que.isEmpty()) {
			int num = que.poll();

			if (paths.containsKey(num)) {
				for (int j = 0; j < paths.get(num).size(); j++) {
					if (visited[paths.get(num).get(j)] == false) {
						que.offer(paths.get(num).get(j));
						visited[paths.get(num).get(j)] = true;
						answer++;
					}
				}
			}
		}
		System.out.println(answer);

	}

}