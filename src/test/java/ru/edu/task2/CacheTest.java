package ru.edu.task2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CacheTest {
    @Test
    public void execTest() {
        TestFraction fr = new TestFraction(1,2);
        Fractionable num = CustomInvocationHandler.createProxy(fr);
        double d = num.doubleValue();
        assertEquals(fr.count,1);
    }

    @Test
    public void cacheTest() {
        TestFraction fr = new TestFraction(1,2);
        Fractionable num = CustomInvocationHandler.createProxy(fr);
        double d = num.doubleValue();
        d = num.doubleValue();
        d = num.doubleValue();
        assertEquals(fr.count,1);
    }

    @Test
    public void mutatorTest() {
        TestFraction fr = new TestFraction(1,2);
        Fractionable num = CustomInvocationHandler.createProxy(fr);
        double d = num.doubleValue();
        d = num.doubleValue();
        num.setDenum(3);
        d = num.doubleValue();
        assertEquals(fr.count,2);
    }
}
