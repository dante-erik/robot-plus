package test;

import math.FastMath;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FastMathTest {
    private final static float delta = 0.5f;

    @Test
    public void fastSqrt() {
        final int n1 = 64;
        Assertions.assertEquals(Math.sqrt(n1), FastMath.fastSqrt(n1), delta);
        final int n2 = 50;
        assertEquals(Math.sqrt(n2), FastMath.fastSqrt(n2), delta);
        final int n3 = 2;
        assertEquals(Math.sqrt(n3), FastMath.fastSqrt(n3), delta);
    }

    @Test
    public void getLowerSqrt() {
        final int n1 = 64;
        assertEquals(Math.floor(Math.sqrt(n1)), FastMath.getLowerSqrt(n1));
        final int n2 = 50;
        assertEquals(Math.floor(Math.sqrt(n2)), FastMath.getLowerSqrt(n2));
        final int n3 = 3;
        assertEquals(Math.floor(Math.sqrt(n3)), FastMath.getLowerSqrt(n3));
    }

    @Test
    public void fastDistance() {
        final int x21 = 10, y21 = 10;
        assertEquals(Math.hypot(10, 10), FastMath.fastDistance(0, 0, x21, y21), delta);
        final int x12 = 10, y12 = 0, x22 = 10, y22 = 10;
        assertEquals(Math.sqrt(Math.pow(x22 - x12, 2) + Math.pow(y22 - y12, 2)), FastMath.fastDistance(x12, y12, x22, y22), delta);
        final int x13 = 10, y13 = 10, x23 = 10, y23 = 10;
        assertEquals(Math.sqrt(Math.pow(x23 - x13, 2) + Math.pow(y23 - y13, 2)), FastMath.fastDistance(x13, y13, x23, y23), delta);
    }

    @Test
    public void getRandInt() {
        byte i = (byte)0;
        final byte attempts = (byte)10;
        final var firstNum = FastMath.getRandInt(0, 10000);
        // Automatic Type Assignment
        var secondNum = FastMath.getRandInt(0, 0);
        // Odds this test fails:
        // 1/10000^10
        do {
            secondNum = FastMath.getRandInt(0, 10000);
        } while(++i < attempts && firstNum == secondNum);
        assertNotEquals(firstNum, secondNum);
    }
}