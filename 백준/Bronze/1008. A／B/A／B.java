import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        double A, B;

        A = scanner.nextDouble();
        B = scanner.nextDouble();
        System.out.println((A/B));
    }
}