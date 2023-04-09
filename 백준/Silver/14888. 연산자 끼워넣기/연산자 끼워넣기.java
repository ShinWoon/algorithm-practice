import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
	public static int N;
	public static int max_num;
	public static int min_num;
	public static int[] num;
	public static String[] op;
	public static boolean[] visited;
	public static String[] pArr;
	public static int[] opType;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		num = new int[N];
		op = new String[N - 1];

		String[] tmp = br.readLine().split(" ");
		for (int i = 0; i < N; i++)
			num[i] = Integer.parseInt(tmp[i]);

		opType = new int[4];
		tmp = br.readLine().split(" ");
		for (int i = 0; i < 4; i++)
			opType[i] = Integer.parseInt(tmp[i]);

		max_num = -100000000;
		min_num = 100000000;
		pArr = new String[N - 1];
		visited = new boolean[N - 1];

		getPermutation(0);
		System.out.println(max_num);
		System.out.println(min_num);

	}

	public static String putOperation(int idx) {
		if (idx == 0)
			return "+";
		else if (idx == 1)
			return "-";
		else if (idx == 2)
			return "*";
		else
			return "/";
	}

	public static void getPermutation(int r) {
		if (r == N - 1) {
			doCalc();
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (opType[i] == 0)
				continue;
			opType[i] -= 1;
			pArr[r] = putOperation(i);
			getPermutation(r + 1);
			opType[i] += 1;

		}

	}

	public static void doCalc() {
		int[] tmp = num.clone();

		for (int i = 0; i < N - 1; i++) {
			tmp[i + 1] = doOper(tmp[i], tmp[i + 1], pArr[i]);
		}
		max_num = Math.max(max_num, tmp[N - 1]);
		min_num = Math.min(min_num, tmp[N - 1]);
	}

	public static int doOper(int num1, int num2, String operation) {
//		System.out.println(num1 + " " + num2 + " " + operation);
		int result = 0;
		if (operation.equals("+"))
			result = num1 + num2;
		else if (operation.equals("-"))
			result = num1 - num2;
		else if (operation.equals("*"))
			result = num1 * num2;
		else
			result = num1 / num2;
		return result;
	}
}