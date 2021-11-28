import static java.lang.Thread.sleep;

public class TestExecutor {

    public static boolean execute(int[] inputPlan, int minimalOutput, int maximalOutput) {
        var ref = new Object() {
            int output = 0;
        };
        Bucket<Object> b = new Bucket<>(Tests.CAPACITY, Tests.RATE, x -> ref.output++);
        Object e = new Object();
        try {
            for (int i : inputPlan) {
                for (int j = 0; j < i; j++) {
                    b.put(e);
                }
                sleep(Tests.DELAY);
            }
        } catch (InterruptedException ignore) {
        }
        return minimalOutput <= ref.output && ref.output <= maximalOutput;
    }
}
