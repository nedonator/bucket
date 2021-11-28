import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Tests {
    static final long DELAY = 2000;
    static final int CAPACITY = 10;
    static final int RATE = 10;

    @Test
    public void test1(){
        assertTrue(TestExecutor.execute(new int[]{10},10,10));
    }
    @Test
    public void test2(){
        assertTrue(TestExecutor.execute(new int[]{100},10,11));
    }
    @Test
    public void test3(){
        assertTrue(TestExecutor.execute(new int[]{100, 5},15,16));
    }
    @Test
    public void test4(){
        assertTrue(TestExecutor.execute(new int[]{100, 5, 100},25,27));
    }
}
