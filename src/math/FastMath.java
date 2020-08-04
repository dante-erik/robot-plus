package math;

public class FastMath {
	/**
     * Calculates the Math.sqrt(n) very quickly.
     * Runs in O(n^(1/2)) and is resource light. Faster than Math.sqrt().
     *
     * Less accurate than Math.sqrt(), but surprisingly accurate given its
     * speed.
     *
     * Uses getLowerSqrt.
     *
     * @param n Value to find square root of
     * @return square root of n (within small error)
     * @author Dante Barbieri <pulchroxloom>
     */
    public static double fastSqrt(final int n)
    {
        final var i = getLowerSqrt(n);
        return i + ((n - (i * i)) / ((i << 1) + 1d));
    }

		/**
     * Calculates the Math.floor(Math.sqrt(n)) very quickly.
     * Runs in O(n^(1/2)) and is resource light. Far faster than Math.sqrt().
     *
     * @param n Value to find floor integer square root
     * @return floor integer square root of n
     * @author Dante Barbieri <pulchroxloom>
     */
    public static int getLowerSqrt(final int n)
    {
        for (var i = 0; i < n; ++i) if ((i * i) > n) return i - 1;
        return n;
    }

		/**
     * Calculates the distance between 2 Cartesian points using above methods.
     *
     * @param x1 x-coordinate of point 1
     * @param y1 y-coordinate of point 1
     * @param x2 x-coordinate of point 2
     * @param y2 y-coordinate of point 2
     * @return cartesian distance between points 1 and 2 to within small error
     * @author Dante Barbieri <pulchroxloom>
     */
    public static double fastDistance(final int x1, final int y1, final int x2, final int y2)
    {
        final var deltaX = x2 - x1;
        final var deltaY = y2 - y1;
        return fastSqrt((deltaX) * (deltaX) + (deltaY) * (deltaY));
    }

		/**
     * Bit shifting System.nanoTime() for efficient random number generation
     *
     * XOR-Shift Algorithm
     *
     * @param min Minimum value function can return (inclusive)
     * @param max Maximum value function can return (inclusive)
     * @return random number between min and max using System.nanoTime() as seed
     * @author Erik Barbieri <Dohinkus>
     */
    public static int getRandInt(final int min, final int max)
    {
        var x = System.nanoTime();

        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);

        return (int) (Math.abs(x) % (max + 1 - min) + min);
    }
}