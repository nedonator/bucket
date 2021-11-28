import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class BucketConsumer<T> extends Thread {
    double rate;
    double balance;
    long lastUpdateTime;
    Consumer<T> consumer;
    BlockingQueue<T> queue;
    static final double correction = 1.01;

    public BucketConsumer(double rate, Consumer<T> consumer, BlockingQueue<T> queue) {
        this.rate = rate;
        this.consumer = consumer;
        balance = 0;
        lastUpdateTime = System.nanoTime();
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                while (balance < 0) {
                    sleep((long) (-balance * 1000 * correction / rate));
                    long t = System.nanoTime();
                    balance += (t - lastUpdateTime) * rate / 1e+9;
                    lastUpdateTime = t;
                }
                consumer.accept(queue.take());
                balance -= 1;
            }
        } catch (InterruptedException ignore) {
        }
    }
}
