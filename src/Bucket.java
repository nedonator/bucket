import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

public class Bucket<T> {
    BlockingQueue<T> queue;

    public Bucket(int capacity, double rate, Consumer<T> consumer) {
        this.queue = new ArrayBlockingQueue<>(capacity);
        Thread t = new BucketConsumer<>(rate, consumer, queue);
        t.setDaemon(true);
        t.start();
    }

    public boolean put(T e){
        return queue.offer(e);
    }
}
