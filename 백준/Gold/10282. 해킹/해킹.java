import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {
	static class Computer {
		int number;
		int hackedTime;
		public Computer(int number, int hackedTime) {
			super();
			this.number = number;
			this.hackedTime = hackedTime;
		}
	}
	static int N, D, C, totalTime, hackedComps;
	static int[] time;
	static Map<Integer, List<Computer>> relation;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testcase=1; testcase<=T; testcase++) {
			String[] tmp = br.readLine().split(" ");
			N = Integer.parseInt(tmp[0]);
			D = Integer.parseInt(tmp[1]);
			C = Integer.parseInt(tmp[2]);
			
			relation = new HashMap<>();
			time = new int[N+1];
			for(int i=1; i<=N; i++) {
				relation.put(i, new ArrayList<>());
				time[i] = (int)1e9;
			}
			for(int i=0; i<D; i++) {
				tmp = br.readLine().split(" ");
				int a = Integer.parseInt(tmp[0]);
				int b = Integer.parseInt(tmp[1]);
				int s = Integer.parseInt(tmp[2]);
				
				relation.get(b).add(new Computer(a, s));
			}
			solve();
			
			for(int i=1; i<=N; i++) {
				if(time[i] == (int)1e9) continue;
				hackedComps++;
				totalTime = Math.max(totalTime, time[i]);
			}
			System.out.println(hackedComps + " " + totalTime);
		}
	}
	
	public static void solve() {
		hackedComps = 0;
		totalTime = 0;
		
		Queue<Computer> q = new LinkedList<>();
		time[C] = 0;
		q.add(new Computer(C, time[C]));
		
		while(!q.isEmpty()) {
			Computer currentComp = q.poll();
			
			if(time[currentComp.number] < currentComp.hackedTime) continue;
			
			if(relation.get(currentComp.number).size() == 0) continue;
			for(int i=0; i<relation.get(currentComp.number).size(); i++) {
				int nextComp = relation.get(currentComp.number).get(i).number;
				if(time[nextComp] > currentComp.hackedTime +relation.get(currentComp.number).get(i).hackedTime) {
					time[nextComp] = currentComp.hackedTime+relation.get(currentComp.number).get(i).hackedTime;
					q.add(new Computer(nextComp, time[nextComp]));
				}
			}
		}
	}
}