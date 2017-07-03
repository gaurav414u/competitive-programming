import java.util.Scanner;

public class TreeOfManyLeaves {

    public static void main(String[] args) {
	// write your code here

        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        while (t > 0) {
            int n = s.nextInt();

            int ans = 0;
            while(n > 0) {
                int a = s.nextInt();
                ans = ans^a;
                n--;
            }
            System.out.println(ans);

            t--;
        }

    }
}
