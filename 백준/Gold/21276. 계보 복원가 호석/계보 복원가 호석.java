import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {
	public static int N;
	public static List<String> root;
	public static Map<String, Integer> name;
	public static Map<String, List<String>> parent, children;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		name = new HashMap<>();
		parent = new HashMap<>();
		children = new HashMap<>();
		N = Integer.parseInt(br.readLine());
		int idx = 1;
		String[] tmp = br.readLine().split(" ");
		for(int i=0; i<N; i++) {
			name.put(tmp[i], idx);
			parent.put(tmp[i], new ArrayList<>());
			children.put(tmp[i], new ArrayList<>());
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i=0; i<M; i++) {
			tmp = br.readLine().split(" ");
			parent.get(tmp[0]).add(tmp[1]);
		}
		
		solve();
		printResult();
		
	}
	
	public static void solve() {
		root = new ArrayList<>();
		Set<String> key = name.keySet();
		Iterator iter = key.iterator();
		while(iter.hasNext()) {
			String currentNode = (String) iter.next();
			if(parent.get(currentNode).size() == 0) root.add(currentNode);
			else {
				getParent(currentNode);
			}
		}
		
	}
	
	public static void getParent(String node) {
		for(int i=0; i<parent.get(node).size(); i++) {
			String parentN = parent.get(node).get(i);
			if(parent.get(parentN).size() == parent.get(node).size()-1) {
				children.get(parentN).add(node);
			}
		}
	}
	
	public static void printResult() {
		// 시조 이름 사전순 정렬 및 출력
		Collections.sort(root);
		System.out.println(root.size());
		for(int i=0; i<root.size(); i++) {
			System.out.print(root.get(i) + " ");
		}
		System.out.println("");
		
		// 이름을 사전순대로 사람 이름, 자식 수 , 사전순으로 자식들의 이름 출력
		Map<String, Integer> newName = new TreeMap<>(name);
		Set<String> key = newName.keySet();
		Iterator iter = key.iterator();
		while(iter.hasNext()) { 
			String currentName = (String) iter.next();
			int cSize = children.get(currentName).size();
			System.out.print(currentName + " " + cSize + " ");
			if(cSize != 0) {
				List<String> childrenNode = children.get(currentName);
				Collections.sort(childrenNode);
				for(int i=0; i<childrenNode.size(); i++) System.out.print(childrenNode.get(i) + " ");
			}
			System.out.println("");
		}

	}

}