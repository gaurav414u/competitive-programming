import java.util.*;

public class CHEFSUBA {
    static int N;
    static int K;
    static int P;
    static int[] a;
    static int[] result;
    static int[] onesInWindow;

    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        N = s.nextInt();
        K = s.nextInt();
        P = s.nextInt();
        if (K > N) {
            K = N;
        }

        a = new int[N];
        result = new int[N];
        onesInWindow = new int[N];

        for (int i = 0; i < N; i++) {
            a[i] = s.nextInt();
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (i == 0) {
                for (int j = 0; j < K; j++) {
                    if (a[j] == 1) {
                        count++;
                    }
                }
            } else {
                //i > 0
                int rightEnd = i + K - 1;
                if (rightEnd >= N) {
                    rightEnd = rightEnd - N;
                }
                if (a[rightEnd] == 1) {
                    count++;
                }
            }

            onesInWindow[i] = count;

            if (a[i] == 1) {
                count -= 1;
            }

        }

        onesInWindow = reverseAndShiftArray(onesInWindow, K-1);
        TreeMap<Integer, Integer> t= new TreeMap<>();

        //calculate results now
        for (int i = 0, w = 0; w < onesInWindow.length ; i++, w++) {
            while (i < N-K+1 && w == 0) {
                int el = onesInWindow[i];
                if (t.containsKey(el)) {
                    int amt = t.get(el);
                    t.put(el, amt + 1);
                } else {
                    t.put(el, 1);
                }
                i++;
            }
            if (i == onesInWindow.length) {
                i = 0;
            }

            //store the answer
            result[w] = t.lastKey();
            if (w == onesInWindow.length - 1) {
                break;
            }

            int el = onesInWindow[i];
            if (t.containsKey(el)) {
                int amt = t.get(el);
                t.put(el, amt + 1);
            } else {
                t.put(el, 1);
            }

            int elementRemoved = onesInWindow[w];
            if (t.containsKey(elementRemoved)) {
                int amt = t.get(elementRemoved);
                if (amt == 1) {
                    t.remove(elementRemoved);
                } else {
                    t.put(elementRemoved, amt - 1);
                }
            }
        }

        char[] str = s.next().toCharArray();
        int sh = 0;

        for (int i = 0; i < P; i++) {
            if (str[i] == '!') {
                sh++;
                if (sh == N) {
                    sh = 0;
                }
            } else {
                System.out.println(result[sh]);
            }

        }
    }

    public static int[] reverseAndShiftArray(int[] a, int shift) {
        int[] t = new int[a.length];
        for (int i =0, j = a.length-1; i < j ; i++, j--) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        for (int i=0; i < a.length ; i++) {
            int pos = i - shift;
            if (pos < 0) {
                pos += a.length;
            }
            t[pos] = a[i];
        }
        return t;
    }
}
