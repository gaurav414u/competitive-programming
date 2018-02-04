import android.os.SystemClock;

public class Test {
    String str1 = "1";
    String str2 = "2";

    public void do1() {
        while (true) {
            synchronized (str2) {
                synchronized (str1) {
                    System.out.println(str1 + str2);
                }


            }
        }
    }

    public void do2() {
        while(true) {
            synchronized (str1) {
                synchronized (str2) {
                    System.out.println(str1 + str2);
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("hello");
        final Test t = new Test();
        final Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.do2();
            }
        });
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                t.do1();
                try {
                    Thread.sleep(1);
                } catch() {
                }
                t2.start();
            }
        });

        t1.start();
    }
}


