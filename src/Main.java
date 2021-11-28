import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Main {
    public static final long start = System.nanoTime();
    public static AtomicInteger i = new AtomicInteger();
    public static String out(int x){
        return String.format("element %d is %d. time passed: %f",
                i.incrementAndGet(), x, (System.nanoTime() - start) / 1e+9);
    }
    public static void main(String[] args) throws Exception {
        Bucket<Integer> bucket = new Bucket<>(10, 2, x-> System.out.println(out(x)));
        int e = 0;
        for(int j = 0; j < 3000; j++){
            sleep(10);
            bucket.put(e++);
        }
        sleep(10000);
        for(int j = 0; j < 20; j++){
            sleep(10);
            bucket.put(e++);
        }
        sleep(10000);
    }
}
